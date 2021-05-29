package com.tac.pickapp.ui.viewmodel;

import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.common.ProductVM;

import javax.inject.Inject;

public class ProductVMFactory extends VMFactory<ProductVM> {

    @Inject
    UseCase useCase;

    @Inject
    public ProductVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return ProductVM.class;
    }

    @Override
    protected ProductVM resolveVM() {
        return new ProductVM(useCase);
    }
}
