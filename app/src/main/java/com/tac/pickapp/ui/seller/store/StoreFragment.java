package com.tac.pickapp.ui.seller.store;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tac.pickapp.R;
import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.databinding.FragmentStoreBinding;
import com.tac.pickapp.ui.seller.product.MyProductFragment;
import com.tac.pickapp.ui.util.StoreListener;
import com.tac.pickapp.ui.viewmodel.StoreVMFactory;

import javax.inject.Inject;

public class StoreFragment extends Fragment implements StoreListener.OnSetupStore {

    @Inject
    StoreVMFactory vmFactory;
    private StoreVM storeVM;

    private FragmentStoreBinding binding;

    public StoreFragment() {
        // Required empty public constructor
    }

    public static StoreFragment newInstance() {
        return new StoreFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PickApp.getComponent().inject(this);
        storeVM = new ViewModelProvider(this, vmFactory).get(StoreVM.class);
        binding = FragmentStoreBinding.inflate(inflater, container, false);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();



        if (storeVM.getUser().getStore() != null) {
            ft.replace(R.id.store_container, MyProductFragment.newInstance());
        } else {
            ft.replace(R.id.store_container, SetupStoreFragment.newInstance(this));
        }

        ft.commit();

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onStoreSetupDone() {
        // Replace setup store fragment with my product fragment
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.store_container, MyProductFragment.newInstance());
        ft.commit();
    }
}