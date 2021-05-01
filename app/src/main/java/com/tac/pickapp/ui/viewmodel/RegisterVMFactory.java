package com.tac.pickapp.ui.viewmodel;

import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.auth.RegisterVM;

import javax.inject.Inject;

public class RegisterVMFactory extends VMFactory<RegisterVM> {

    @Inject
    UseCase useCase;

    @Inject
    public RegisterVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return RegisterVM.class;
    }

    @Override
    protected RegisterVM resolveVM() {
        return new RegisterVM(useCase);
    }
}
