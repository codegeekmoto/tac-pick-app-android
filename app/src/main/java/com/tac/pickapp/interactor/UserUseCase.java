package com.tac.pickapp.interactor;

import com.tac.pickapp.data.Repository;
import com.tac.pickapp.data.remote.dto.Data;
import com.tac.pickapp.data.remote.dto.User;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class UserUseCase extends BaseUseCase {

    @Inject
    Repository repository;

    @Inject
    public UserUseCase() {
    }

    public Register register(User user) {
        Register register = new Register(user);
        threads.add(register);
        return register;
    }

    public Login login(User user) {
        Login login = new Login(user);
        threads.add(login);
        return login;
    }

    // Register
    public class Register extends Thread<Boolean> {

        private User user;

        public Register(User user) {
            this.user = user;
        }

        @Override
        protected Observable<Boolean> buildUseCaseObservable() {
            return repository.user().register(user);
        }
    }

    // Login
    public class Login extends  Thread<Boolean> {

        private User user;

        public Login(User user) {
            this.user = user;
        }

        @Override
        protected Observable<Boolean> buildUseCaseObservable() {
            return repository.user().login(user);
        }
    }

}
