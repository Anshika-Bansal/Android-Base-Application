package com.base.app.baseComponent;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import javax.inject.Inject;

/*Shared preference.. */
public class AppPreferences {

    private SharedPreferences mSharedPreferences;
    private Editor mEditor;

    private static final String USER_TOKEN = "userToken";



    @Inject
    public AppPreferences(Context context) {
        mSharedPreferences = context.getSharedPreferences("APP_PREFE",
                Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    /**
     * Method used to set Api AccessToken
     *
     */
    public void setAccessToken( String userToken) {
        mEditor.putString(USER_TOKEN, userToken);
        mEditor.commit();
    }

    /**
     * Method used to get Api AccessToken
     *
     * @return accessToken
     */
    public String getAccessToken() {
        return mSharedPreferences.getString(USER_TOKEN, "");
    }



}
