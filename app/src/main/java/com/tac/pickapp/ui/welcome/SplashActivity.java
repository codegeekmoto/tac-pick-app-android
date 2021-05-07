package com.tac.pickapp.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.tac.pickapp.R;
import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.data.remote.dto.User;
import com.tac.pickapp.data.source.UserSource;
import com.tac.pickapp.ui.auth.AuthActivity;
import com.tac.pickapp.ui.customer.CustomerActivity;
import com.tac.pickapp.ui.rider.RiderActivity;
import com.tac.pickapp.ui.seller.SellerActivity;
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

        User user = vModel.getUser();
        Class<?> nextAct;

        if (user != null) {
            if (user.getType().toLowerCase().equals("customer")) {
                nextAct = CustomerActivity.class;
            } else if (user.getType().toLowerCase().equals("seller")) {
                nextAct = SellerActivity.class;
            } else {
                nextAct = RiderActivity.class;
            }
        } else {
            nextAct = AuthActivity.class;
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, nextAct));
            }
        }, 1000);
    }
}
