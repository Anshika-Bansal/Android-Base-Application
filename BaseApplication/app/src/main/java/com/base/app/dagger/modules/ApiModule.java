package com.base.app.dagger.modules;

import android.content.Context;

import com.base.app.BuildConfig;
import com.base.app.api.ApiService;
import com.base.app.api.AuthenticationInterceptor;
import com.base.app.api.RxErrorHandlingCallAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    OkHttpClient provideOkHttpClientInstance(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(45, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS);
        builder.addInterceptor(new AuthenticationInterceptor(context));
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder.build();
    }


    @Provides
    Retrofit provideRetrofitInstance(OkHttpClient okHttpClient) {

        GsonBuilder gsonBuilder = new GsonBuilder();

        CustomizedObjectTypeAdapter adapter = new CustomizedObjectTypeAdapter();


        Gson gson = gsonBuilder.registerTypeAdapter(Map.class, adapter)
                .registerTypeAdapter(List.class, adapter).setLenient().create();

        Retrofit.Builder builder = new Retrofit.Builder().
                client(okHttpClient).
                addConverterFactory(GsonConverterFactory.create(gson)).
                addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .baseUrl(BuildConfig.BaseUrl);

        return builder.build();
    }

    @Provides
    ApiService provideApiServiceInstance(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
