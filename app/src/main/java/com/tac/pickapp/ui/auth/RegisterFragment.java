package com.tac.pickapp.ui.auth;

import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.data.remote.dto.User;
import com.tac.pickapp.databinding.FragmentAuthRegisterBinding;
import com.tac.pickapp.ui.util.Helper;
import com.tac.pickapp.ui.util.ResultObserver;
import com.tac.pickapp.ui.viewmodel.RegisterVMFactory;
import com.tac.pickapp.util.Constants;

import javax.inject.Inject;

public class RegisterFragment extends Fragment {

    @Inject
    RegisterVMFactory vmFactory;
    private RegisterVM vModel;

    private FragmentAuthRegisterBinding binding;

    private String mUserType = "customer";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PickApp.getComponent().inject(this);
        vModel = new ViewModelProvider(this, vmFactory).get(RegisterVM.class);

        binding = FragmentAuthRegisterBinding.inflate(inflater, container, false);
        binding.toolbar.title.setText("Join Pick App");

        initForm();
        setVmObserver();

        binding.customer.setOnClickListener(v -> {
            setUserType("customer");
        });

        binding.seller.setOnClickListener(v -> {
            setUserType("seller");
        });


        binding.rider.setOnClickListener(v -> {
            setUserType("rider");
        });


        return binding.getRoot();
    }

    private void setVmObserver() {
        vModel.getRegisterResult().observe(getViewLifecycleOwner(), new ResultObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                clearForm();
                Helper.dialogAlert(getContext(), "Registration Success", Constants.Message.REGISTER_SUCCESS);
            }

            @Override
            public void onError(Throwable e) {

                String errMsg = "";
                if (e.getMessage().contains("Unable to resolve host")) {
                    errMsg = Constants.Message.REGISTER_INTERNET_ERROR;
                } else {
                    errMsg = Constants.Message.SOMETHING_WENT_WRONG;
                }

                Helper.dialogAlert(getContext(), "Registration Failed", errMsg);
            }

            @Override
            public void onComplete() {
                enabledRegistrationForm(true);
            }
        });
    }

    private void setUserType(String userType) {
        binding.customer.setChecked(userType.equals("customer"));
        binding.seller.setChecked(userType.equals("seller"));
        binding.rider.setChecked(userType.equals("rider"));
        mUserType = userType;
    }

    private void initForm() {
        Helper.addTextWatcher(binding.firstName, binding.firstNameLayout);
        Helper.addTextWatcher(binding.lastName, binding.lastNameLayout);
        Helper.addTextWatcher(binding.phone, binding.phoneLayout);
        Helper.addTextWatcher(binding.email, binding.emailLayout);
        Helper.addTextWatcher(binding.password, binding.passwordLayout);
        Helper.addTextWatcher(binding.confirmPassword, binding.confirmPasswordLayout);

        binding.btnRegister.setOnClickListener(v -> {
            if (validateForm()) {
                User user = new User();
                user.setFirstName(binding.firstName.getText().toString());
                user.setLastName(binding.firstName.getText().toString());
                user.setPhone(binding.phone.getText().toString());
                user.setEmail(binding.email.getText().toString());
                user.setPassword(binding.password.getText().toString());
                user.setAddress(binding.address.getText().toString());
                user.setType(mUserType);
                enabledRegistrationForm(false);
                vModel.register(user);
            }
        });

        binding.toolbar.btnBack.setOnClickListener(v -> getActivity().onBackPressed());
    }

    private void enabledRegistrationForm(boolean enabled) {
        Helper.enableView(binding.parent, enabled);
        binding.btnRegister.setEnabled(enabled);
        binding.progress.setVisibility(enabled ? View.GONE : View.VISIBLE);
    }

    private void clearForm() {
        binding.firstName.getText().clear();
        binding.lastName.getText().clear();
        binding.phone.getText().clear();
        binding.email.getText().clear();
        binding.password.getText().clear();
        binding.confirmPassword.getText().clear();
    }

    private boolean validateForm() {
        boolean noError = true;

        if (TextUtils.isEmpty(binding.firstName.getText())) {
            binding.firstNameLayout.setError("Invalid first name");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.lastName.getText())) {
            binding.lastNameLayout.setError("Invalid last name.");
            noError = false;
        }

        if (!PhoneNumberUtils.isGlobalPhoneNumber(binding.phone.getText().toString()) || binding.phone.getText().toString().trim().equals("")) {
            binding.phoneLayout.setError("Invalid phone number.");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.email.getText()) || !Patterns.EMAIL_ADDRESS.matcher(binding.email.getText()).matches()) {
            binding.emailLayout.setError("Invalid email address.");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.password.getText()) || binding.password.length() < 6) {
            binding.passwordLayout.setError("Password must be at least 6 characters.");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.confirmPassword.getText()) || !binding.password.getText().toString().equals(binding.confirmPassword.getText().toString())) {
            binding.confirmPasswordLayout.setError("Please confirm password.");
            noError = false;
        }

        return noError;
    }
}
