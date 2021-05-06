package com.tac.pickapp.app.dagger;

import com.tac.pickapp.ui.auth.LoginFragment;
import com.tac.pickapp.ui.auth.RegisterFragment;
import com.tac.pickapp.ui.seller.store.AddProductFragment;
import com.tac.pickapp.ui.seller.store.CreateStoreFragment;
import com.tac.pickapp.ui.seller.store.StoreFragment;
import com.tac.pickapp.ui.welcome.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ContextModule.class,
        DataSourceModule.class,
        ServiceModule.class
})
public interface AppComponent {

    void inject(SplashActivity splashActivity);
    void inject(RegisterFragment registerFragment);
    void inject(LoginFragment loginFragment);
    void inject(StoreFragment storeFragment);
    void inject(CreateStoreFragment createStoreFragment);
    void inject(AddProductFragment addProductFragment);
}
