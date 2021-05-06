package com.tac.pickapp.data.remote.api;

import com.tac.pickapp.data.remote.dto.Response;
import com.tac.pickapp.util.Constants;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface StoreApi {

    @POST(Constants.BASE_URI + "/api/store/create")
    Observable<Response> create(@Body RequestBody body);
}
