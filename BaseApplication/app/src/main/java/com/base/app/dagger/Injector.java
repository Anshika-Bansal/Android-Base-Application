package com.base.app.dagger;


import com.base.app.api.AuthenticationInterceptor;
import com.base.app.features.HomeScreen.HomeActivity;

public interface Injector {
    void inject(HomeActivity homeActivity);
    void inject(AuthenticationInterceptor authenticationInterceptor);


}
