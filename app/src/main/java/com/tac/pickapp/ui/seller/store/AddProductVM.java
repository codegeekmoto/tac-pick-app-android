package com.tac.pickapp.ui.seller.store;

import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.viewmodel.BaseVM;

public class AddProductVM extends BaseVM {

    public AddProductVM(UseCase useCase) {
        super(useCase);
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return AddProductVM.class;
    }
}
