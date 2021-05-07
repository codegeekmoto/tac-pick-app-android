package com.tac.pickapp.ui.seller.store;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tac.pickapp.data.remote.dto.Store;
import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.util.Result;
import com.tac.pickapp.ui.viewmodel.BaseVM;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class StoreVM extends BaseVM {

    private MutableLiveData<Result> createStoreResult = new MutableLiveData<>();

    public StoreVM(UseCase useCase) {
        super(useCase);
    }

    public LiveData<Result> getCreateStoreResult() {
        return createStoreResult;
    }

    public void saveStore(Store store) {
        useCase.store().createStore(store).execute(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean aBoolean) {
                createStoreResult.setValue(new Result.Success<>(aBoolean));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                printError(e);
                createStoreResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {}
        });
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return StoreVM.class;
    }
}
