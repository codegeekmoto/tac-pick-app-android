package com.tac.pickapp.ui.welcome;

import androidx.lifecycle.ViewModel;

import com.tac.pickapp.data.Repository;

public class SplashViewModel extends ViewModel {

    private Repository repo;

    public SplashViewModel(Repository repo) {
        this.repo = repo;
    }

    public boolean isLoggedIn() {
        return repo.user().isLoggedIn();
    }
}
