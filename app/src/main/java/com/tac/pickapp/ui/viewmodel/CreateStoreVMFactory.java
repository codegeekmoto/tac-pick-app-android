package com.tac.pickapp.ui.viewmodel;

import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.seller.store.CreateStoreVM;

import javax.inject.Inject;

public class CreateStoreVMFactory extends VMFactory<CreateStoreVM> {

    @Inject
    UseCase useCase;

    @Inject
    public CreateStoreVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return CreateStoreVM.class;
    }

    @Override
    protected CreateStoreVM resolveVM() {
        return new CreateStoreVM(useCase);
    }
}
