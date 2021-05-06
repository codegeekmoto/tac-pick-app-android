package com.tac.pickapp.interactor;

import com.tac.pickapp.data.Repository;
import com.tac.pickapp.data.remote.dto.Store;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class StoreUseCase extends BaseUseCase {

    @Inject
    Repository repository;

    @Inject
    public StoreUseCase() {
    }

    public Store getStore() {
        return repository.store().getStore();
    }

    public CreateStore createStore(Store store) {
        CreateStore createStore = new CreateStore(store);
        threads.add(createStore);
        return createStore;
    }

    public class CreateStore extends Thread<Boolean> {

        private Store store;

        public CreateStore(Store store) {
            this.store = store;
        }

        @Override
        protected Observable<Boolean> buildUseCaseObservable() {
            return repository.store().create(store);
        }
    }
}
