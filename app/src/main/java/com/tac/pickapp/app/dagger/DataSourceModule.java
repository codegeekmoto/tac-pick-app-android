package com.tac.pickapp.app.dagger;

import com.tac.pickapp.data.local.Preferences;
import com.tac.pickapp.data.remote.RemoteApi;
import com.tac.pickapp.data.source.StoreSource;
import com.tac.pickapp.data.source.UserSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataSourceModule {

    @Provides
    @Singleton
    UserSource providesUserSource(RemoteApi remoteApi, Preferences preferences) {
        return new UserSource(remoteApi, preferences);
    }

    @Provides
    @Singleton
    StoreSource providesStoreSource(RemoteApi remoteApi, Preferences preferences) {
        return new StoreSource(remoteApi, preferences);
    }
}
