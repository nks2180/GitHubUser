package com.app.gitevent.component;

import com.app.gitevent.GitApplication;
import com.app.gitevent.activity.GitEventsActivity;
import com.app.gitevent.module.ApplicationModule;
import com.app.gitevent.module.DatabaseModule;
import com.app.gitevent.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by niranjan on 03/11/16.
 */
@Singleton
@Component(modules={ApplicationModule.class, NetModule.class, DatabaseModule.class})
public interface ApplicationComponent {

    void inject(GitApplication materialApplication);

    void inject(GitEventsActivity gitEventsActivity);
}
