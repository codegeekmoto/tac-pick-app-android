package com.tac.pickapp.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.tac.pickapp.data.remote.dto.User;
import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.util.logging.Logger;
import com.tac.pickapp.util.logging.LoggerFactory;

public abstract class BaseVM extends ViewModel {

    protected Logger LOG;

    protected UseCase useCase;

    protected BaseVM(UseCase useCase) {
        this.useCase = useCase;
        LOG = LoggerFactory.getLogger(resolveLoggerName());
    }

    public User getUser() {
        return useCase.user().get();
    }

    public void logout() {
        useCase.user().logout();
    }

    protected abstract Class<?> resolveLoggerName();

    @Override
    protected void onCleared() {
        super.onCleared();
        useCase.dispose();
    }

    protected void printError(Throwable e) {
        LOG.error(e.getMessage());
        e.printStackTrace();
    }
}
