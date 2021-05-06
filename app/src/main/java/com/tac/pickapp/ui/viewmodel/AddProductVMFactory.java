package com.tac.pickapp.ui.viewmodel;

import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.seller.store.AddProductVM;

import javax.inject.Inject;

public class AddProductVMFactory extends VMFactory<AddProductVM> {

    @Inject
    UseCase useCase;

    @Inject
    public AddProductVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return AddProductVM.class;
    }

    @Override
    protected AddProductVM resolveVM() {
        return new AddProductVM(useCase);
    }
}
