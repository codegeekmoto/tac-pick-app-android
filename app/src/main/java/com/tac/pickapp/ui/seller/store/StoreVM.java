package com.tac.pickapp.ui.seller.store;

import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.viewmodel.BaseVM;

public class StoreVM extends BaseVM {

    public StoreVM(UseCase useCase) {
        super(useCase);
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return StoreVM.class;
    }
}
