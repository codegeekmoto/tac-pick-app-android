package com.tac.pickapp.data;

import com.tac.pickapp.data.source.UserSource;

import javax.inject.Inject;

public class Repository {

    @Inject
    UserSource user;

    @Inject
    public Repository() {}

    public UserSource user() {
        return user;
    }
}
