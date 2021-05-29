package com.tac.pickapp.interactor;

import android.net.Uri;

import com.tac.pickapp.data.Repository;
import com.tac.pickapp.data.remote.dto.Product;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class ProductUseCase extends BaseUseCase {

    @Inject
    Repository repository;

    @Inject
    public ProductUseCase() {
    }

    public UploadImage uploadImage(Uri uri, String type) {
        UploadImage uploadImage = new UploadImage(uri, type);
        threads.add(uploadImage);
        return uploadImage;
    }

    public SaveProduct saveProduct(Product product) {
        SaveProduct saveProduct = new SaveProduct(product);
        threads.add(saveProduct);
        return saveProduct;
    }

    public FetchProductTask fetchProduct(String storeId, String category) {
        FetchProductTask fetchProductTask = new FetchProductTask(storeId, category);
        threads.add(fetchProductTask);
        return fetchProductTask;
    }

    public FetchAllProductTask fetchAllProductTask(String category) {
        FetchAllProductTask fetchAllProductTask = new FetchAllProductTask(category);
        threads.add(fetchAllProductTask);
        return fetchAllProductTask;
    }

    public class FetchAllProductTask extends Thread<List<Product>> {

        private String category;

        public FetchAllProductTask(String category) {
            this.category = category;
        }

        @Override
        protected Observable<List<Product>> buildUseCaseObservable() {
            return repository.product().getAll(category);
        }
    }

    public class FetchProductTask extends Thread<List<Product>> {

        private String storeId;
        private String category;

        public FetchProductTask(String storeId, String category) {
            this.storeId = storeId;
            this.category = category;
        }

        @Override
        protected Observable<List<Product>> buildUseCaseObservable() {
            return repository.product().get(storeId, category);
        }
    }

    public class UploadImage extends Thread<String> {

        private Uri uri;
        private String type;

        public UploadImage(Uri uri, String type) {
            this.uri = uri;
            this.type = type;
        }

        @Override
        protected Observable<String> buildUseCaseObservable() {
            return repository.product().uploadProductCover(uri, type);
        }
    }

    public class SaveProduct extends Thread<Product> {

        private Product product;

        public SaveProduct(Product product) {
            this.product = product;
        }

        @Override
        protected Observable<Product> buildUseCaseObservable() {
            return repository.product().saveProduct(product);
        }
    }

}
