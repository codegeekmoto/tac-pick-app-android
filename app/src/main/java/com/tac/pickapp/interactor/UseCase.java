package com.tac.pickapp.interactor;

import javax.inject.Inject;

public class UseCase {

    @Inject
    UserUseCase user;

    @Inject
    public UseCase() {
    }

    public UserUseCase user() {
        return user;
    }

    public void dispose() {
        user.dispose();
    }

}
