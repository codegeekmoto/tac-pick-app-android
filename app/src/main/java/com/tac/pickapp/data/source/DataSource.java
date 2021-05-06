package com.tac.pickapp.data.source;

import com.tac.pickapp.data.local.Preferences;
import com.tac.pickapp.data.remote.RemoteApi;

public abstract class DataSource {

    protected RemoteApi remoteApi;
    protected Preferences prefsApi;

    protected DataSource(RemoteApi remoteApi, Preferences prefsApi) {
        this.remoteApi = remoteApi;
        this.prefsApi = prefsApi;
    }
}
