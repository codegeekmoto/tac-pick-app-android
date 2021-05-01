package com.tac.pickapp.app.dagger;

import android.content.Context;
import android.content.SharedPreferences;

import com.tac.pickapp.data.remote.RetrofitService;
import com.tac.pickapp.data.remote.interceptor.ApiTokenInterceptor;
import com.tac.pickapp.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    RetrofitService provideRetrofitService(ApiTokenInterceptor apiTokenInterceptor) {
        return new RetrofitService(apiTokenInterceptor);
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(Constants.APP_NAME, 0);
    }
}
