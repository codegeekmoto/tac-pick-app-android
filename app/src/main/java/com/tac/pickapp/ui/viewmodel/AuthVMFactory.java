package com.tac.pickapp.ui.viewmodel;

import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.auth.AuthVM;

import javax.inject.Inject;

public class AuthVMFactory extends VMFactory<AuthVM> {

    @Inject
    UseCase useCase;

    @Inject
    public AuthVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return AuthVM.class;
    }

    @Override
    protected AuthVM resolveVM() {
        return new AuthVM(useCase);
    }
}
