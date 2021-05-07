package com.tac.pickapp.data.local;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

public class Preferences {

    @Inject
    SharedPreferences preferences;

    @Inject
    public Preferences() {}

    public void setString(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public void setJson(String key, Object object) {
        preferences.edit()
                .putString(key, new Gson().toJson(object))
                .apply();
    }

    public <T> T getObject(String key, Class<T> objClass) {
        return new Gson().fromJson(preferences.getString(key, ""), objClass);
    }

    public void clearAll() {
        preferences.edit().clear().apply();
    }

}
