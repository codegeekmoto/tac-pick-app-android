package com.tac.pickapp.data.remote;

import com.tac.pickapp.data.remote.api.AuthApi;
import com.tac.pickapp.data.remote.api.UserApi;

import javax.inject.Inject;

public class RemoteApi {

    @Inject
    RetrofitService retrofitService;

    @Inject
    public RemoteApi() {}

    public AuthApi auth() {
        return retrofitService.getDefault().create(AuthApi.class);
    }

    public UserApi user() {
        return  retrofitService.getAuthenticated().create(UserApi.class);
    }
}