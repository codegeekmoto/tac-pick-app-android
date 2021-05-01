package com.tac.pickapp.app;

import android.app.Application;

import com.tac.pickapp.app.dagger.AppComponent;
import com.tac.pickapp.app.dagger.ContextModule;
import com.tac.pickapp.app.dagger.DaggerAppComponent;

public class PickApp extends Application {

    private static PickApp INSTANCE;
    private static AppComponent APP_COMPONENT;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = PickApp.this;

        APP_COMPONENT = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static PickApp getInstance() {
        return INSTANCE;
    }

    public static AppComponent getComponent() {
        return APP_COMPONENT;
    }
}
