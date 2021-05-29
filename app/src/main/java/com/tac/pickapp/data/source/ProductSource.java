package com.tac.pickapp.data.source;

import android.net.Uri;

import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.data.local.Preferences;
import com.tac.pickapp.data.remote.RemoteApi;
import com.tac.pickapp.data.remote.dto.Product;
import com.tac.pickapp.data.remote.dto.Response;
import com.tac.pickapp.data.remote.dto.Response2;
import com.tac.pickapp.util.Util;

import java.io.File;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProductSource extends DataSource {

    public ProductSource(RemoteApi remoteApi, Preferences prefsApi) {
        super(remoteApi, prefsApi);
    }

    public Observable<String> uploadProductCover(Uri uri, String type) {
        MultipartBody.Part imageType = MultipartBody.Part.createFormData("type", type);

        File file = new File(Util.getRealPathFromUri(PickApp.getInstance(), uri));
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part partBody = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        return remoteApi.product().uploadCover(partBody, imageType).map(new Function<Response, String>() {
            @Override
            public String apply(Response response) throws Throwable {
                return response.getImageUrl();
            }
        });
    }

    public Observable<Product> saveProduct(Product product) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("store_id", String.valueOf(product.getStoreId()))
                .addFormDataPart("category", product.getCategory())
                .addFormDataPart("name", product.getName())
                .addFormDataPart("description", product.getDescription())
                .addFormDataPart("price", product.getPrice())
                .addFormDataPart("quantity", product.getQuantity())
                .addFormDataPart("num_of_stock", String.valueOf(product.getNumOfStock()))
                .addFormDataPart("cover_url", product.getCoverUrl())
                .build();

        return remoteApi.product().save(body).map(new Function<Response2, Product>() {
            @Override
            public Product apply(Response2 response2) throws Throwable {
                return response2.getProducts().get(0);
            }
        });
    }

    public Observable<List<Product>> get(String storeId, String category) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("store_id", storeId)
                .addFormDataPart("category", category)
                .build();

        return remoteApi.product().fetchProduct(body).map(new Function<Response2, List<Product>>() {
            @Override
            public List<Product> apply(Response2 response2) throws Throwable {
                return response2.getProducts();
            }
        });
    }

    public Observable<List<Product>> getAll(String category) {

        return remoteApi.product().fetchAllProduct(category).map(new Function<Response2, List<Product>>() {
            @Override
            public List<Product> apply(Response2 response2) throws Throwable {
                return response2.getProducts();
            }
        });
    }
}
