package com.app.gitevent.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.app.gitevent.executor.ParsingExecutor;
import com.app.gitevent.model.LoginResponse;
import com.app.gitevent.retrofit.ApiController;
import com.app.gitevent.retrofit.ApiDataReceiveCallback;
import com.app.gitevent.retrofit.NetworkConstants;
import com.app.gitevent.retrofit.RequestBuilder;
import com.app.gitevent.utils.GitUtils;
import com.bluelinelabs.logansquare.LoganSquare;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by niranjan on 12/13/16.
 */
public class LoginPresenter extends BasePresenterImpl<LoginView> implements ApiDataReceiveCallback {

    @Inject
    ApiController apiController;

    @Inject
    ParsingExecutor parsingExecutor;

    @Inject
    LoginPresenter(Context baseContext) {
        super(baseContext);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDataReceived(String response, int type) {
        switch (type) {
            case NetworkConstants.API_VALIDATE_USER:
                if (!TextUtils.isEmpty(response)) {
                    view.onLoginResponseCame(parseResponse(response));
                }
                else
                    view.onLoginResponseCame(null);
        }
    }

    private LoginResponse parseResponse(String response) {
        try {
            return LoganSquare.parse(response, LoginResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onError(int type) {
        view.onLoginResponseCame(null);
    }

    public void validateUser(String userName) {
        if (!GitUtils.isConnected(baseContext))
            return;

        RequestBuilder requestBuilder = new RequestBuilder(NetworkConstants.API_VALIDATE_USER);
        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put(GitUtils.STR_USER_NAME, userName);
        requestBuilder.setExtraParameters(requestParams);
        apiController.hitApi(requestBuilder, this);
    }
}


