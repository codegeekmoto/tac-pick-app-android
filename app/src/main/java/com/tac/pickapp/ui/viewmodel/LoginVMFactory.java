package com.tac.pickapp.ui.viewmodel;

import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.auth.LoginVM;

import javax.inject.Inject;

public class LoginVMFactory extends VMFactory<LoginVM> {

    @Inject
    UseCase useCase;

    @Inject
    public LoginVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return LoginVM.class;
    }

    @Override
    protected LoginVM resolveVM() {
        return new LoginVM(useCase);
    }
}
