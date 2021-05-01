package com.tac.pickapp.data.remote;

import com.tac.pickapp.data.remote.interceptor.ApiTokenInterceptor;
import com.tac.pickapp.data.remote.interceptor.HeaderInterceptor;
import com.tac.pickapp.util.Constants;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit.Builder retrofit;
    private ApiTokenInterceptor apiTokenInterceptor;

    public RetrofitService(ApiTokenInterceptor apiTokenInterceptor) {
        this.apiTokenInterceptor = apiTokenInterceptor;

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.HOST)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }

    public Retrofit getDefault() {
        OkHttpClient.Builder client = createClient();
        client.interceptors().add(new HeaderInterceptor());
        return retrofit.client(client.build()).build();
    }

    public Retrofit getAuthenticated() {
        OkHttpClient.Builder client =createClient();
        client.interceptors().add(apiTokenInterceptor);

        return retrofit.client(client.build()).build();
    }

    public static OkHttpClient.Builder createClient() {
        OkHttpClient.Builder client =  new OkHttpClient.Builder();
        client.interceptors().add(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return client;
    }

}
