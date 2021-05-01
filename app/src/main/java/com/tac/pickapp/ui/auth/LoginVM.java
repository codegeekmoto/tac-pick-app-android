package com.tac.pickapp.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tac.pickapp.data.remote.dto.User;
import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.util.Result;
import com.tac.pickapp.ui.viewmodel.BaseVM;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class LoginVM extends BaseVM {

    private MutableLiveData<Result> loginResult = new MutableLiveData<>();

    public LoginVM(UseCase useCase) {
        super(useCase);
    }

    public LiveData<Result> getLoginResult() {
        return loginResult;
    }

    public void login(User user) {
        useCase.user().login(user).execute(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean aBoolean) {
                loginResult.setValue(new Result.Success<>(aBoolean));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                printError(e);
                loginResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {}
        });
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return LoginVM.class;
    }
}
