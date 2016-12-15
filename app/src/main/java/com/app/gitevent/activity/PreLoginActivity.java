package com.app.gitevent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.app.gitevent.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by niranjan on 12/14/16.
 */

public class PreLoginActivity extends AppCompatActivity {

    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.imgVw_icon)
    ImageView imgVwIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void onLoginButtonTapped() {
        Intent intent = new Intent(this, LoginActivity.class);
        Pair<View, String> pairOne = Pair.create(imgVwIcon,  getResources().getString(R.string.transition_login_image));
        Pair<View, String> pairTwo = Pair.create(btnLogin, getResources().getString(R.string.transition_login_btn));
        ActivityOptionsCompat options =  ActivityOptionsCompat.makeSceneTransitionAnimation(this,pairOne, pairTwo);
        startActivity(intent, options.toBundle());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
