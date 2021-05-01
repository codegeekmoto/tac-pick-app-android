package com.tac.pickapp.ui.customer.freshdaily;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tac.pickapp.databinding.FragmentFreshDailyBinding;

public class FreshDailyFragment extends Fragment {

    private FragmentFreshDailyBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFreshDailyBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}
