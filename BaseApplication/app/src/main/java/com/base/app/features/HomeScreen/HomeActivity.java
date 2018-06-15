package com.base.app.features.HomeScreen;

import android.os.Bundle;

import com.base.app.R;
import com.base.app.baseComponent.BaseActivity;
import com.base.app.baseComponent.BaseApplication;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        BaseApplication.getApp().getDaggerAppComponent().inject(this);
    }
}
