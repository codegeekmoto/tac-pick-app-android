package com.tac.pickapp.data.remote.interceptor;

import com.tac.pickapp.data.local.Preferences;
import com.tac.pickapp.data.remote.dto.User;
import com.tac.pickapp.util.Prefs;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiTokenInterceptor implements Interceptor {

    @Inject
    Preferences pref;

    @Inject
    public ApiTokenInterceptor() {}

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Authorization", "Bearer "+ pref.getObject(Prefs.USER, User.class).getApiToken());
        builder.addHeader("Accept", "application/json");

        return chain.proceed(builder.build());
    }
}
