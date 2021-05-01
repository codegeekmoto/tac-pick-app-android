package com.tac.pickapp.ui.viewmodel;

import com.tac.pickapp.data.Repository;
import com.tac.pickapp.ui.welcome.SplashViewModel;

import javax.inject.Inject;

public class SplashVMFactory extends VMFactory<SplashViewModel> {

    @Inject
    Repository repo;

    @Inject
    public SplashVMFactory() {}

    @Override
    protected Class<?> getVmClass() {
        return SplashViewModel.class;
    }

    @Override
    protected SplashViewModel resolveVM() {
        return new SplashViewModel(repo);
    }
}
