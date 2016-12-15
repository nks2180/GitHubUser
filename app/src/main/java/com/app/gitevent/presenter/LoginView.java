package com.app.gitevent.presenter;

import com.app.gitevent.model.LoginResponse;

/**
 * Created by niranjan on 12/13/16.
 */

public interface LoginView extends BaseView{

    void onLoginResponseCame(LoginResponse loginResponse);
}
