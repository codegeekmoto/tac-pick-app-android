package com.tac.pickapp.ui.account;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.tac.pickapp.R;
import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.databinding.FragmentAccountBinding;
import com.tac.pickapp.ui.auth.AuthActivity;
import com.tac.pickapp.ui.viewmodel.AccountVMFactory;

import javax.inject.Inject;

public class AccountFragment extends Fragment {

    public final static String UPDATE_ACCOUNT = "UPDATE_ACCOUNT";

    @Inject
    AccountVMFactory vmFactory;
    private AccountVM accountVM;

    private FragmentAccountBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PickApp.getComponent().inject(this);
        accountVM = new ViewModelProvider(this, vmFactory).get(AccountVM.class);
        binding = FragmentAccountBinding.inflate(inflater, container, false);

        binding.btnEmail.setOnClickListener(v -> {
            updateAccount("Update Email");
        });

        binding.btnPhone.setOnClickListener(v -> {
            updateAccount("Update Phone");
        });

        binding.btnAddress.setOnClickListener(v -> {
            updateAccount("Update Address");
        });

        binding.btnPassword.setOnClickListener(v -> {
            updateAccount("Change Password");
        });

        binding.btnLogout.setOnClickListener(v -> {
            new AlertDialog.Builder(getContext())
                    .setMessage("Are you sure you want to logout your account?")
                    .setPositiveButton("YES", (dialog, which) -> {
                        accountVM.logout();
                        startActivity(new Intent(getActivity(), AuthActivity.class));
                        getActivity().finish();
                    })
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show();
        });

        return binding.getRoot();
    }

    private void updateAccount(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(UPDATE_ACCOUNT, type);

        Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                .navigate(R.id.nav_update_account, bundle);
    }

    @Override
    public void onResume() {
        super.onResume();
//        User user = PickApp.getInstance().getUser();
//        binding.email.setText(user.getEmail());
//        binding.phone.setText(user.getPhone());
//        binding.address.setText(user.getAddress());
        //  binding.nbi.setText(user.());
    }
}
