package com.tac.pickapp.data.source;

import com.tac.pickapp.data.local.Preferences;
import com.tac.pickapp.data.remote.RemoteApi;
import com.tac.pickapp.data.remote.dto.Response;
import com.tac.pickapp.data.remote.dto.User;
import com.tac.pickapp.util.Prefs;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserSource extends DataSource {

    public UserSource(RemoteApi remoteApi, Preferences prefApi) {
        super(remoteApi, prefApi);
    }

    public boolean isLoggedIn() {
        return prefsApi.getObject(Prefs.USER, User.class) != null;
    }

    public Observable<Boolean> register(User user) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("f_name", user.getFirstName())
                .addFormDataPart("l_name", user.getLastName())
                .addFormDataPart("email", user.getEmail())
                .addFormDataPart("password", user.getPassword())
                .addFormDataPart("phone", user.getPhone())
                .addFormDataPart("address", user.getAddress())
                .addFormDataPart("type", user.getType())
                .addFormDataPart("picture", "")
                .build();

        return remoteApi.auth().register(body)
                .map(new Function<Response, Boolean>() {
                    @Override
                    public Boolean apply(Response response) throws Throwable {
                        return response.getStatus() == null ? true : false;
                    }
                });
    }

    public Observable<Boolean> login(User user) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", user.getEmail())
                .addFormDataPart("password", user.getPassword())
                .addFormDataPart("type", user.getType())
                .build();

        return remoteApi.auth().login(body).map(new Function<Response, Boolean>() {
            @Override
            public Boolean apply(Response response) throws Throwable {
                prefsApi.setJson(Prefs.USER, response.getData().getUser());
                return response.getStatus() == null ? true : false;
            }
        });
    }

}
