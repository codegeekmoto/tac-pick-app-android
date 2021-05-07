package com.tac.pickapp.app.dagger;

import com.tac.pickapp.ui.account.AccountFragment;
import com.tac.pickapp.ui.auth.LoginFragment;
import com.tac.pickapp.ui.auth.RegisterFragment;
import com.tac.pickapp.ui.seller.SellerActivity;
import com.tac.pickapp.ui.seller.store.AddProductFragment;
import com.tac.pickapp.ui.seller.store.MyProductFragment;
import com.tac.pickapp.ui.seller.store.SetupStoreFragment;
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
    void inject(SellerActivity sellerActivity);

    void inject(RegisterFragment registerFragment);
    void inject(LoginFragment loginFragment);
    void inject(SetupStoreFragment setupStoreFragment);
    void inject(AddProductFragment addProductFragment);
    void inject(AccountFragment accountFragment);
    void inject(MyProductFragment myProductFragment);
    void inject(StoreFragment storeFragment);
}
