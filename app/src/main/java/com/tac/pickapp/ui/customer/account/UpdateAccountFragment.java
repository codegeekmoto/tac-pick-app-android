package com.tac.pickapp.ui.customer.account;

import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tac.pickapp.databinding.FragmentUpdateAccountBinding;
import com.tac.pickapp.ui.util.Helper;

import java.util.HashMap;
import java.util.Map;

public class UpdateAccountFragment extends Fragment {

    private FragmentUpdateAccountBinding binding;
    private String updateType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //PickApp.getAppComponent().inject(this);
        // vm = new ViewModelProvider(this, vmFactory).get(AccountViewModel.class);

        binding = FragmentUpdateAccountBinding.inflate(inflater, container, false);
        updateType = getArguments().getString(AccountFragment.UPDATE_ACCOUNT);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(updateType);

        setVmObserver();
        setUi();

        return binding.getRoot();
    }

    private void setUi() {
        switch (updateType.toLowerCase()) {
            case "update email":
                Helper.addTextWatcher(binding.email,binding.emailLayout);
                binding.emailParent.setVisibility(View.VISIBLE);
                break;
            case "update phone":
                Helper.addTextWatcher(binding.phone,binding.phoneLayout);
                binding.phoneParent.setVisibility(View.VISIBLE);
                break;
            case "update address":
                Helper.addTextWatcher(binding.address,binding.addressLayout);
                binding.addressParent.setVisibility(View.VISIBLE);
                break;
            case "change password":
                Helper.addTextWatcher(binding.currentPassword,binding.currentPasswordLayout);
                Helper.addTextWatcher(binding.newPassword,binding.newPasswordLayout);
                Helper.addTextWatcher(binding.confirmPassword,binding.confirmPasswordLayout);
                binding.changePasswordParent.setVisibility(View.VISIBLE);
                break;
        }

        binding.btnSave.setOnClickListener(v -> {
            switch (updateType.toLowerCase()) {
                case "update email":
                    saveEmail();
                    break;
                case "update phone":
                    savePhone();
                    break;
                case "update address":
                    saveAddress();
                    break;
                case "change password":
                    changePassword();
                    break;
            }
        });

    }

    private void changePassword() {
        boolean noError = true;

        if (TextUtils.isEmpty(binding.currentPassword.getText())) {
            binding.currentPasswordLayout.setError("Current password is required.");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.newPassword.getText()) || binding.newPassword.length() < 6) {
            binding.newPasswordLayout.setError("Password must be at least 6 characters.");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.confirmPassword.getText()) || !binding.newPassword.getText().toString().equals(binding.confirmPassword.getText().toString())) {
            binding.confirmPasswordLayout.setError("Please confirm password.");
            noError = false;
        }

        if (noError) {
            Map<String, String> data = new HashMap<>();
            data.put("current_password", binding.currentPassword.getText().toString());
            data.put("new_password", binding.newPassword.getText().toString());
            enabledForm(false);
            //vm.changePassword(data);
        }
    }

    private void savePhone() {
        if (!PhoneNumberUtils.isGlobalPhoneNumber(binding.phone.getText().toString()) || binding.phone.getText().toString().trim().equals("")) {
            binding.phoneLayout.setError("Invalid phone number.");
        } else {
            enabledForm(false);
            //vm.updatePhone(binding.phone.getText().toString());
        }
    }

    private void saveEmail() {
        if (TextUtils.isEmpty(binding.email.getText()) || !Patterns.EMAIL_ADDRESS.matcher(binding.email.getText()).matches()) {
            binding.emailLayout.setError("Invalid email address.");
        } else {
            enabledForm(false);
            //vm.updateEmail(binding.email.getText().toString());
        }
    }

    private void saveAddress() {
        if (TextUtils.isEmpty(binding.address.getText())) {
            binding.addressLayout.setError("Invalid address.");
        } else {
            enabledForm(false);
            //vm.updateAddress(binding.address.getText().toString());
        }
    }

    private void setVmObserver() {
//        vm.getUpdateEmailResult().observe(getViewLifecycleOwner(), new ResultObserver<Boolean>() {
//            @Override
//            public void onSuccess(Boolean aBoolean) {
//                binding.email.setText("");
//                binding.phone.setText("");
//                binding.address.setText("");
//
//                String message = "Account successfully updated.";
//
//                if (updateType.toLowerCase().equals("change password")) {
//                    if (!aBoolean) {
//                        binding.currentPasswordLayout.setError("Wrong current password");
//                        return;
//                    }
//
//                    message = "Change password success.";
//                    binding.currentPassword.setText("");
//                    binding.newPassword.setText("");
//                    binding.confirmPassword.setText("");
//                }
//
//                Helper.dialogAlert(getContext(), "", message);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Logger.E("Update email error", e);
//                Helper.resolveError(getActivity(), e);
//            }
//
//            @Override
//            public void onComplete() { enabledForm(true); }
//        });
    }

    private void enabledForm(boolean enabled) {
        Helper.enableView(binding.parent, enabled);
        binding.btnSave.setEnabled(enabled);
        binding.progress.setVisibility(enabled ? View.GONE : View.VISIBLE);
    }
}
