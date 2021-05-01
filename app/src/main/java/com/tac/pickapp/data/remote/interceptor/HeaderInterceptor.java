package com.tac.pickapp.data.remote.interceptor;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements okhttp3.Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Accept", "application/json");
        return chain.proceed(builder.build());
    }
}
