package com.tac.pickapp.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tac.pickapp.data.remote.dto.User;
import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.util.Result;
import com.tac.pickapp.ui.viewmodel.BaseVM;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class RegisterVM extends BaseVM {

    private MutableLiveData<Result> registerResult = new MutableLiveData<>();

    public RegisterVM(UseCase useCase) {
        super(useCase);
    }

    public LiveData<Result> getRegisterResult() {
        return registerResult;
    }

    public void register(User user) {
        useCase.user().register(user).execute(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean bool) {
                registerResult.setValue(new Result.Success<>(bool));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                printError(e);
                registerResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {}
        });
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return RegisterVM.class;
    }
}
