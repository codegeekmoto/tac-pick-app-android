package com.tac.pickapp.ui.seller.store;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tac.pickapp.R;
import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.databinding.FragmentMyProductBinding;
import com.tac.pickapp.ui.viewmodel.StoreVMFactory;

import javax.inject.Inject;


public class MyProductFragment extends Fragment {

    @Inject
    StoreVMFactory vmFactory;
    private StoreVM storeVM;

    private FragmentMyProductBinding binding;

    public MyProductFragment() {
        // Required empty public constructor
    }


    public static MyProductFragment newInstance() {
        return new MyProductFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PickApp.getComponent().inject(this);
        storeVM = new ViewModelProvider(this, vmFactory).get(StoreVM.class);
        binding = FragmentMyProductBinding.inflate(inflater, container, false);

        binding.btnAdd.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.seller_nav_host_fragment)
                    .navigate(R.id.nav_add_product);
        });

        return binding.getRoot();
    }
}