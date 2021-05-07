package com.tac.pickapp.ui.viewmodel;

import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.account.AccountVM;

import javax.inject.Inject;

public class AccountVMFactory extends VMFactory<AccountVM> {

    @Inject
    UseCase useCase;

    @Inject
    public AccountVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return AccountVM.class;
    }

    @Override
    protected AccountVM resolveVM() {
        return new AccountVM(useCase);
    }
}
