package com.tac.pickapp.ui.seller.store;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.databinding.FragmentAddProductBinding;
import com.tac.pickapp.ui.viewmodel.AddProductVMFactory;

import javax.inject.Inject;

public class AddProductFragment extends Fragment {

    @Inject
    AddProductVMFactory vmFactory;
    private AddProductVM addProductVM;

    private FragmentAddProductBinding binding;

    public AddProductFragment() {
        // Required empty public constructor
    }


    public static AddProductFragment newInstance() {
        return new AddProductFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PickApp.getComponent().inject(this);
        addProductVM = new ViewModelProvider(this, vmFactory).get(AddProductVM.class);

        binding = FragmentAddProductBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}