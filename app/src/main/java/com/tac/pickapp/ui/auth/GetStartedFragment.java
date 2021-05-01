package com.tac.pickapp.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.tac.pickapp.R;
import com.tac.pickapp.databinding.FragmentAuthGetStartedBinding;

public class GetStartedFragment extends Fragment {

    private FragmentAuthGetStartedBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAuthGetStartedBinding.inflate(inflater, container, false);

        Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
                .getGraph().setStartDestination(R.id.auth_get_started);

        binding.btnJoin.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
                    .navigate(R.id.auth_register);
        });

        binding.btnCustomer.setOnClickListener(v -> {
            navigateToLogin("Customer");
        });

        binding.btnSeller.setOnClickListener(v -> {
            navigateToLogin("Seller");
        });

        binding.btnRider.setOnClickListener(v -> {
            navigateToLogin("Delivery Rider");
        });

        return binding.getRoot();
    }

    private void navigateToLogin(String loginType) {
        Bundle bundle = new Bundle();
        bundle.putString("LOGIN", loginType);

        Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
                .navigate(R.id.auth_login, bundle);
    }
}
