package com.tac.pickapp.app.dagger;

import com.tac.pickapp.ui.auth.LoginFragment;
import com.tac.pickapp.ui.auth.RegisterFragment;
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
}
