package com.tac.pickapp.ui.seller.store;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tac.pickapp.R;
import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.databinding.FragmentStoreBinding;
import com.tac.pickapp.ui.viewmodel.StoreVMFactory;

import javax.inject.Inject;

public class StoreFragment extends Fragment implements StoreListener.OnAddStoreSuccess {

    @Inject
    StoreVMFactory vmFactory;
    private StoreVM storeVM;

    private FragmentStoreBinding binding;

    public StoreFragment() {
        // Required empty public constructor
    }

    public static StoreFragment newInstance() {
        StoreFragment fragment = new StoreFragment();
        return new StoreFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PickApp.getComponent().inject(this);
        binding = FragmentStoreBinding.inflate(inflater, container, false);


        binding.btnSetup.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.seller_nav_host_fragment)
                    .navigate(R.id.action_nav_store_to_nav_create_store);
        });

        return binding.getRoot();
    }

    @Override
    public void onSuccess() {

    }
}