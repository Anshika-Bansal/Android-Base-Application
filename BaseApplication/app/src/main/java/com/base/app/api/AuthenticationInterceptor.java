package com.base.app.api;


import android.content.Context;
import android.text.TextUtils;

import com.base.app.baseComponent.AppPreferences;
import com.base.app.baseComponent.BaseApplication;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {


    private static final String KEY_AUTHORIZATION = "Authorization";


    @Inject
    AppPreferences mSharedPreferences;

    public AuthenticationInterceptor(Context context) {
        BaseApplication.getApp().getDaggerAppComponent().inject(this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        // Nothing to add to intercepted request if:
        // a) Authorization value is empty because user is not logged in yet
        // b) There is already a header with updated Authorization value
        Request.Builder builder = originalRequest.newBuilder();
        if (authorizationTokenIsEmpty() || alreadyHasAuthorizationHeader(originalRequest)) {
            Request modifiedRequest = builder
                    // .header(API_ACCESS_KEY, API_ACCESS_KEY_VALUE)
                    .build();
            return chain.proceed(modifiedRequest);
        }

        // Add authorization header with updated authorization value to intercepted request
        Request modifiedRequest = builder
                .header(KEY_AUTHORIZATION, mSharedPreferences.getAccessToken())
                .build();
        return chain.proceed(modifiedRequest);
    }

    private boolean authorizationTokenIsEmpty() {
        return TextUtils.isEmpty(mSharedPreferences.getAccessToken());
    }

    private boolean alreadyHasAuthorizationHeader(Request request) {
        for (String header : request.headers().names()) {
            if (header.equals(KEY_AUTHORIZATION)) {
                return true;
            }
        }
        return false;
    }
}
