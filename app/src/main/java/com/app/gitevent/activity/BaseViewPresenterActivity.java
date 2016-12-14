package com.app.gitevent.activity;

import android.content.Intent;

import com.app.gitevent.presenter.BasePresenterImpl;
import com.app.gitevent.presenter.BaseView;

public abstract class BaseViewPresenterActivity<P extends BasePresenterImpl> extends BaseActivity implements BaseView {

    private P presenter;

    protected void initializePresenter(P presenter, BaseView baseView) {
        this.presenter = presenter;

        presenter.setView(baseView);
        presenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void moveToNextScreen(Intent intent) {
        startActivity(intent);
        finish();
    }
}
