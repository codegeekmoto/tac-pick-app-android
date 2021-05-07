package com.tac.pickapp.ui.account;

import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.viewmodel.BaseVM;

public class AccountVM extends BaseVM {

    public AccountVM(UseCase useCase) {
        super(useCase);
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return AccountVM.class;
    }
}
