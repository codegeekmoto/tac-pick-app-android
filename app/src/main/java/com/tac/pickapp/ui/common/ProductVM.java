package com.tac.pickapp.ui.common;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tac.pickapp.data.remote.dto.Product;
import com.tac.pickapp.interactor.UseCase;
import com.tac.pickapp.ui.util.Result;
import com.tac.pickapp.ui.viewmodel.BaseVM;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class ProductVM extends BaseVM {

    private MutableLiveData<Result> uploadResult = new MutableLiveData<>();
    private MutableLiveData<Result> saveResult = new MutableLiveData<>();
    private MutableLiveData<Result> productResult = new MutableLiveData<>();
    private MutableLiveData<Result> allProductResult = new MutableLiveData<>();

    public ProductVM(UseCase useCase) {
        super(useCase);
    }

    public LiveData<Result> getUploadResult() {
        return uploadResult;
    }

    public LiveData<Result> getSaveResult() {
        return saveResult;
    }

    public LiveData<Result> getProductResult() {
        return productResult;
    }

    public LiveData<Result> getAllProductResult() {
        return allProductResult;
    }

    public void fetchProductByCategory(String category) {
        useCase.product().fetchAllProductTask(category).execute(new DisposableObserver<List<Product>>() {
            @Override
            public void onNext(@NonNull List<Product> products) {
                allProductResult.setValue(new Result.Success<>(products));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                printError(e);
                allProductResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void fetchProduct(String storeId, String category) {
        useCase.product().fetchProduct(storeId, category).execute(new DisposableObserver<List<Product>>() {
            @Override
            public void onNext(@NonNull List<Product> products) {
                productResult.setValue(new Result.Success<>(products));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                printError(e);
                productResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void uploadProductCover(Uri uri) {
        useCase.product().uploadImage(uri, "product").execute(new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String imageUrl) {
                uploadResult.setValue(new Result.Success<>(imageUrl));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                printError(e);
                uploadResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {}
        });
    }

    public void saveProduct(Product product) {
        useCase.product().saveProduct(product).execute(new DisposableObserver<Product>() {
            @Override
            public void onNext(@NonNull Product product) {
                saveResult.setValue(new Result.Success<>(product));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                printError(e);
                saveResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {}
        });
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return ProductVM.class;
    }
}
