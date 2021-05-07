package com.tac.pickapp.ui.viewmodel;

import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.seller.SellerVM;

import javax.inject.Inject;

public class SellerVMFactory extends VMFactory<SellerVM> {

    @Inject
    UseCase useCase;

    @Inject
    public SellerVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return SellerVM.class;
    }

    @Override
    protected SellerVM resolveVM() {
        return new SellerVM(useCase);
    }
}
