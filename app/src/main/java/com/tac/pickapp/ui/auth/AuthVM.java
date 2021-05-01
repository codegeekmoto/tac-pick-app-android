package com.tac.pickapp.ui.auth;

import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.viewmodel.BaseVM;

public class AuthVM extends BaseVM {

    public AuthVM(UseCase useCase) {
        super(useCase);
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return AuthVM.class;
    }
}
