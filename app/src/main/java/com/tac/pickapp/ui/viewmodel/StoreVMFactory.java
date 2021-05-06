package com.tac.pickapp.ui.viewmodel;

import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.seller.store.StoreVM;

import javax.inject.Inject;

public class StoreVMFactory extends VMFactory<StoreVM> {

    @Inject
    UseCase useCase;

    @Inject
    public StoreVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return StoreVM.class;
    }

    @Override
    protected StoreVM resolveVM() {
        return new StoreVM(useCase);
    }
}
