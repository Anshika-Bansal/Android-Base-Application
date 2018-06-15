package com.base.app.dagger.modules;

import android.content.Context;

import com.base.app.baseComponent.BaseApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private BaseApplication mApplication;

    public ApplicationModule(BaseApplication application) {
        mApplication = application;
    }

    @Provides
    public BaseApplication getApplication() {
        return mApplication;
    }

    @Provides
    Context getApplicationContext() {
        return mApplication;
    }

}
