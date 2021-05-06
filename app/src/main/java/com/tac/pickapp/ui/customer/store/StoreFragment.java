package com.tac.pickapp.ui.customer.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tac.pickapp.databinding.FragmentStoreLocationBinding;

public class StoreFragment extends Fragment {

    private FragmentStoreLocationBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStoreLocationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
