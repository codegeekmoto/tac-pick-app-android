package com.tac.pickapp.ui.seller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.tac.pickapp.R;
import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.ui.viewmodel.SellerVMFactory;

import javax.inject.Inject;

public class SellerActivity extends AppCompatActivity {

    @Inject
    SellerVMFactory vmFactory;
    private SellerVM sellerVM;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PickApp.getComponent().inject(this);
        sellerVM = new ViewModelProvider(this, vmFactory).get(SellerVM.class);

        setContentView(R.layout.activity_sell);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.seller_drawer_layout);
        NavigationView navigationView = findViewById(R.id.seller_nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_store, R.id.nav_account)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.seller_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        if (sellerVM.getUser().getStore() != null) {
//            navController.getGraph().setStartDestination(R.id.nav_my_product);
//        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.seller_nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}