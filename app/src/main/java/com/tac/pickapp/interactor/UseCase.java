package com.tac.pickapp.interactor;

import com.tac.pickapp.data.remote.dto.Store;

import javax.inject.Inject;

public class UseCase {

    @Inject
    UserUseCase user;

    @Inject
    StoreUseCase store;

    @Inject
    ProductUseCase product;

    @Inject
    public UseCase() {
    }

    public UserUseCase user() {
        return user;
    }

    public StoreUseCase store() { return store; }

    public ProductUseCase product() {
        return product;
    }

    public void dispose() {
        user.dispose();
        store.dispose();
    }

}
