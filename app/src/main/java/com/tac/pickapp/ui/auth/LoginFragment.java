package com.tac.pickapp.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.data.remote.dto.User;
import com.tac.pickapp.databinding.FragmentAuthLoginBinding;
import com.tac.pickapp.ui.customer.CustomerActivity;
import com.tac.pickapp.ui.rider.RiderActivity;
import com.tac.pickapp.ui.seller.SellerActivity;
import com.tac.pickapp.ui.util.Helper;
import com.tac.pickapp.ui.util.ResultObserver;
import com.tac.pickapp.ui.viewmodel.LoginVMFactory;
import com.tac.pickapp.util.Constants;

import javax.inject.Inject;

public class LoginFragment extends Fragment {

    @Inject
    LoginVMFactory vmFactory;
    private LoginVM vModel;

    private  FragmentAuthLoginBinding binding;

    private String mUserType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        PickApp.getComponent().inject(LoginFragment.this);
        vModel = new ViewModelProvider(this, vmFactory).get(LoginVM.class);

        binding = FragmentAuthLoginBinding.inflate(inflater, container, false);
        mUserType = getArguments().getString("LOGIN");
        binding.toolbar.title.setText(mUserType+ " Login");
        binding.toolbar.btnBack.setOnClickListener(v -> getActivity().onBackPressed());

        binding.email.setText("tac.pickapp@gmail.com");
        binding.password.setText("123123");

        initForm();
        setVmObserver();

        return binding.getRoot();
    }

    private void setVmObserver() {
        vModel.getLoginResult().observe(getViewLifecycleOwner(), new ResultObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                if (mUserType.equals("Customer")) {
                    startActivity(new Intent(getActivity(), CustomerActivity.class));
                } else if (mUserType.equals("Seller")) {
                    startActivity(new Intent(getActivity(), SellerActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), RiderActivity.class));
                }
            }

            @Override
            public void onError(Throwable e) {

                String errMsg = "";
                if (e.getMessage().contains("Unable to resolve host")) {
                    errMsg = Constants.Message.LOGIN_INTERNET_ERROR;
                } else if (e.getMessage().contains("401 Unauthorized")) {
                    errMsg = "Incorrect email or password. Try again.";
                } else {
                    errMsg = Constants.Message.SOMETHING_WENT_WRONG;
                }

                Helper.dialogAlert(getContext(), "Login Failed", errMsg);
                enabledLoginForm(true);
            }
        });
    }

    private void enabledLoginForm(boolean enabled) {
        Helper.enableView(binding.parent, enabled);
        binding.btnLogin.setEnabled(enabled);
        binding.progress.setVisibility(enabled ? View.GONE : View.VISIBLE);
    }

    private void initForm() {
        Helper.addTextWatcher(binding.email, binding.emailLayout);
        Helper.addTextWatcher(binding.password, binding.passwordLayout);

        binding.btnLogin.setOnClickListener(v -> {
            if (TextUtils.isEmpty(binding.email.getText()) || !Patterns.EMAIL_ADDRESS.matcher(binding.email.getText()).matches()) {
                binding.emailLayout.setError("Invalid email address.");
            } else {
                enabledLoginForm(false);

                User user = new User();
                user.setEmail(binding.email.getText().toString());
                user.setPassword(binding.password.getText().toString());

                if (mUserType.equals("Delivery Rider")) {
                    user.setType("rider");
                } else {
                    user.setType(mUserType.toLowerCase());
                }

                vModel.login(user);
            }
        });

        binding.resetPassword.setOnClickListener(v -> {
//            Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
//                    .navigate(R.id.action_auth_login_to_auth_reset_password);
        });
    }
}
