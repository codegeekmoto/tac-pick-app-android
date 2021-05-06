package com.tac.pickapp.data.source;

import com.tac.pickapp.data.local.Preferences;
import com.tac.pickapp.data.remote.RemoteApi;
import com.tac.pickapp.data.remote.dto.Response;
import com.tac.pickapp.data.remote.dto.Store;
import com.tac.pickapp.util.Prefs;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class StoreSource extends DataSource {

    public StoreSource(RemoteApi remoteApi, Preferences prefApi) {
        super(remoteApi, prefApi);
    }

    public Observable<Boolean> create(Store store) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", store.getName())
                .addFormDataPart("description", store.getDescription())
                .addFormDataPart("dti", store.getDti())
                .addFormDataPart("business_permit", store.getBusinessPermit())
                .addFormDataPart("address", store.getAddress())
                .build();

        return remoteApi.store().create(body).map(new Function<Response, Boolean>() {
            @Override
            public Boolean apply(Response response) throws Throwable {
                prefsApi.setJson(Prefs.STORE, response.getData().getStore());
                return response.getStatus() == null ? true : false;
            }
        });
    }

    public Store getStore() {
        return prefsApi.getObject(Prefs.STORE, Store.class);
    }
}
