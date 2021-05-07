package com.tac.pickapp.ui.seller;

import com.google.gson.Gson;
import com.tac.pickapp.data.remote.dto.Store;
import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.viewmodel.BaseVM;

public class SellerVM extends BaseVM {

    public SellerVM(UseCase useCase) {
        super(useCase);

        LOG.error(new Gson().toJson(useCase.user().get()));
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return SellerVM.class;
    }
}
