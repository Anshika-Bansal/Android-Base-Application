package com.base.app.dagger;


import com.base.app.dagger.modules.ApiModule;
import com.base.app.dagger.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, ApplicationModule.class})
public interface AppComponent extends Injector {

}
