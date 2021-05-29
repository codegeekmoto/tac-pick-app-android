package com.tac.pickapp.data;

import com.tac.pickapp.data.source.ProductSource;
import com.tac.pickapp.data.source.StoreSource;
import com.tac.pickapp.data.source.UserSource;

import javax.inject.Inject;

public class Repository {

    @Inject
    UserSource user;

    @Inject
    StoreSource store;

    @Inject
    ProductSource product;

    @Inject
    public Repository() {}

    public UserSource user() {
        return user;
    }

    public StoreSource store() { return  store; }

    public ProductSource product() {
        return product;
    }
}
