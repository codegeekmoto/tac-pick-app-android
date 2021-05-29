package com.tac.pickapp.data.remote.api;

import com.tac.pickapp.data.remote.dto.Response;
import com.tac.pickapp.data.remote.dto.Response2;
import com.tac.pickapp.util.Constants;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ProductApi {

    @Multipart
    @POST(Constants.BASE_URI + "/api/upload/image")
    Observable<Response> uploadCover(@Part MultipartBody.Part cover, @Part MultipartBody.Part body);

    @POST(Constants.BASE_URI + "/api/product/add")
    Observable<Response2> save(@Body RequestBody body);

    @POST(Constants.BASE_URI + "/api/product")
    Observable<Response2> fetchProduct(@Body RequestBody body);

    @GET(Constants.BASE_URI + "/api/product/all/{category}")
    Observable<Response2> fetchAllProduct(@Path(value = "category") String category);

}
