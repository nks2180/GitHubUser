package com.app.gitevent;

import android.app.Application;

import com.app.gitevent.component.ApplicationComponent;
import com.app.gitevent.component.DaggerApplicationComponent;
import com.app.gitevent.module.ApplicationModule;
import com.app.gitevent.module.DatabaseModule;
import com.app.gitevent.module.NetModule;


/**
 * Created by niranjan on 12/13/16.
 */
public class GitApplication extends Application {

    private static final String BASE_URL = "https://api.github.com/";

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        setApplicationComponent();
    }

    private void setApplicationComponent() {
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .netModule(new NetModule(BASE_URL))
                .databaseModule(new DatabaseModule(this))
                .build();

        mComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
