package com.app.gitevent.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.gitevent.R;
import com.app.gitevent.component.ApplicationComponent;
import com.app.gitevent.model.LoginResponse;
import com.app.gitevent.presenter.LoginPresenter;
import com.app.gitevent.presenter.LoginView;
import com.app.gitevent.utils.GitUtils;
import com.app.gitevent.utils.RequestProgressDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by niranjan on 12/13/16.
 */

public class LoginActivity extends BaseViewPresenterActivity<LoginPresenter> implements LoginView {

    @Inject
    LoginPresenter loginPresenter;
    @BindView(R.id.imgVw_icon)
    ImageView imgVwIcon;
    @BindView(R.id.edtTxt_userName)
    EditText edtTxtUserName;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.lnrLyt_login)
    LinearLayout lnrLytLogin;

    Context mContext;
    RequestProgressDialog progressDialog;

    @Override
    protected int getMainLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void initializePresenter() {
        super.initializePresenter(loginPresenter, this);
    }

    @Override
    public void onLoginResponseCame(LoginResponse loginResponse) {
        if (null != progressDialog && progressDialog.isShowing())
            progressDialog.dismiss();
        if (null != loginResponse && !TextUtils.isEmpty(loginResponse.getName())) {
            startGitEventsActivity(loginResponse);
        }
        else{
            Toast.makeText(mContext, "Wrong username", Toast.LENGTH_SHORT).show();
        }
    }

    private void startGitEventsActivity(LoginResponse loginResponse){
        Intent intent = new Intent(this, GitEventsActivity.class);
        intent.putExtra(GitUtils.EXTRA_ACOOUNT_OBJ, loginResponse);

        Pair<View, String> pairOne = Pair.create(btnSubmit, getResources().getString(R.string.transition_fab_account));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairOne);
        startActivity(intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mContext = this;
        edtTxtUserName
                .setOnEditorActionListener((v, actionId, event) -> {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_GO) {
                        onSubmitBtnTapped();
                        handled = true;
                    }
                    return handled;
                });

        ScaleAnimation fade_in =  new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        fade_in.setDuration(600);
        fade_in.setFillAfter(true);
        btnSubmit.startAnimation(fade_in);
    }

    @OnClick(R.id.btn_submit)
    public void onSubmitBtnTapped() {
        String userName = edtTxtUserName.getText().toString().trim();
        if (!TextUtils.isEmpty(userName)) {
            GitUtils.hideKeyboard(edtTxtUserName, mContext);
            progressDialog = GitUtils.getRequestProgressDialog(mContext, getString(R.string.please_wait));
            progressDialog.show();
            loginPresenter.validateUser(userName);

        } else
            Toast.makeText(mContext, "Please enter valid username", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        edtTxtUserName.setAlpha(0);
        btnSubmit.setAlpha(0);
    }
}
