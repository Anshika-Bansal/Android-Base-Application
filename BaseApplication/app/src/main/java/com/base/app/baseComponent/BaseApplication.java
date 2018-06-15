package com.base.app.baseComponent;

import android.app.Application;

import com.base.app.dagger.AppComponent;
import com.base.app.dagger.DaggerAppComponent;
import com.base.app.dagger.modules.ApplicationModule;

/*Application class. Assets resources are initialized here..*/
public class BaseApplication extends Application {

    private static BaseApplication mainApplication;

    private AppComponent mAppComponent;

    public static BaseApplication getApp() {
        return mainApplication;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication = this;
        System.setProperty("http.keepAlive", "false");
        setUpDagger();


    }

    /**
     * Method used to setUp dagger
     */
    private void setUpDagger() {
        mAppComponent = DaggerAppComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    /**
     * Method used to get DaggerAppComponent instance to get required injection
     *
     * @return AppComponent
     */
    public AppComponent getDaggerAppComponent() {
        return mAppComponent;
    }


}
