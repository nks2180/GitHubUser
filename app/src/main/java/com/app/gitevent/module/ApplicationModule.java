package com.app.gitevent.module;

import android.content.Context;

import com.app.gitevent.GitApplication;
import com.app.gitevent.executor.ParsingExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by niranjan on 03/11/16.
 */
@Module
public class ApplicationModule {

    private final GitApplication lybrate;

    public ApplicationModule(GitApplication lybrate) {
        this.lybrate = lybrate;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return lybrate;
    }

    @Provides
    @Singleton
    ParsingExecutor providesParsingExecutor() {
        return new ParsingExecutor();
    }

}
