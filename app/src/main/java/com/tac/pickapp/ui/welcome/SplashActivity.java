package com.tac.pickapp.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.tac.pickapp.R;
import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.data.source.UserSource;
import com.tac.pickapp.ui.auth.AuthActivity;
import com.tac.pickapp.ui.viewmodel.SplashVMFactory;
import com.tac.pickapp.util.logging.Logger;
import com.tac.pickapp.util.logging.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity {

    private Logger LOG = LoggerFactory.getLogger(SplashActivity.class);

    @Inject
    SplashVMFactory vmFactory;
    private SplashViewModel vModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PickApp.getComponent().inject(this);
        vModel = new ViewModelProvider(this, vmFactory).get(SplashViewModel.class);

        setContentView(R.layout.activity_splash);

        if (vModel.isLoggedIn()) {
            LOG.debug("Logged In");
        } else {
            LOG.debug("Not logged In");
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, AuthActivity.class));
                        //vModel.isLoggedIn() ? AuthActivity.class : CustomerActivity.class));
            }
        }, 1000);
    }
}
