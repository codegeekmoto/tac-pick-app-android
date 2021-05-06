package com.tac.pickapp.data.remote.api;

import com.tac.pickapp.data.remote.dto.Response;
import com.tac.pickapp.util.Constants;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST(Constants.BASE_URI + "/api/user/create")
    Observable<Response>  register(@Body RequestBody body);

    @POST(Constants.BASE_URI + "/api/auth/login")
    Observable<Response>  login(@Body RequestBody body);

    @POST(Constants.BASE_URI + "/api/auth/reset")
    Observable<Response> requestResetPassword(@Body RequestBody body);

    @POST(Constants.BASE_URI + "/api/auth/resend-otp")
    Observable<Response> resendOTP(@Body RequestBody body);

    @POST(Constants.BASE_URI + "/api/auth/change-password")
    Observable<Response> changePassword(@Body RequestBody body);
}
