package com.tac.pickapp.ui.seller.store;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tac.pickapp.R;
import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.data.remote.dto.Store;
import com.tac.pickapp.data.remote.dto.User;
import com.tac.pickapp.databinding.FragmentCreateStoreBinding;
import com.tac.pickapp.ui.util.Helper;
import com.tac.pickapp.ui.util.ResultObserver;
import com.tac.pickapp.ui.viewmodel.CreateStoreVMFactory;
import com.tac.pickapp.util.Constants;

import javax.inject.Inject;

public class CreateStoreFragment extends Fragment {

    @Inject
    CreateStoreVMFactory vmFactory;
    private CreateStoreVM createStoreVM;

    private FragmentCreateStoreBinding binding;

    public CreateStoreFragment() {
        // Required empty public constructor
    }

    public static CreateStoreFragment newInstance() {
        return new CreateStoreFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PickApp.getComponent().inject(this);
        createStoreVM = new ViewModelProvider(this, vmFactory).get(CreateStoreVM.class);
        binding = FragmentCreateStoreBinding.inflate(inflater, container, false);

        initForm();
        setVMObserver();

        return binding.getRoot();
    }

    private void setVMObserver() {
        createStoreVM.getCreateStoreResult().observe(getViewLifecycleOwner(), new ResultObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                clearForm();
                Helper.dialogAlert(getContext(), "", "New store successfully save.", (dialog, which) -> {
                    getActivity().onBackPressed();
                });
            }

            @Override
            public void onError(Throwable e) {
                String errMsg = "";
                if (e.getMessage().contains("Unable to resolve host")) {
                    errMsg = Constants.Message.CANNOT_RESOLVE_HOST_ERROR;
                } else {
                    errMsg = Constants.Message.SOMETHING_WENT_WRONG;
                }

                Helper.dialogAlert(getContext(), "Failed save store", errMsg);
            }

            @Override
            public void onComplete() {
                enabledForm(true);
            }
        });
    }

    private void initForm() {
        Helper.addTextWatcher(binding.storeName, binding.storeNameLayout);
        Helper.addTextWatcher(binding.description, binding.descriptionLayout);
        //Helper.addTextWatcher(binding.dti, binding.dtiLayout);
        Helper.addTextWatcher(binding.business, binding.businessLayout);
        Helper.addTextWatcher(binding.address, binding.addressLayout);

        binding.btnSave.setOnClickListener(v -> {
            if (validateForm()) {
                Store store = new Store();
                store.setName(binding.storeName.getText().toString());
                store.setDescription(binding.description.getText().toString());
                store.setDti("test");
                //store.setDti(binding.dti.getText().toString());
                store.setBusinessPermit(binding.business.getText().toString());
                store.setAddress(binding.address.getText().toString());

                enabledForm(false);

                createStoreVM.saveStore(store);
            }
        });
    }

    private void enabledForm(boolean enabled) {
        Helper.enableView(binding.form, enabled);
        binding.btnSave.setEnabled(enabled);
        binding.progress.setVisibility(enabled ? View.GONE : View.VISIBLE);
    }

    private void clearForm() {
        binding.storeName.getText().clear();
        binding.description.getText().clear();
        //binding.dti.getText().clear();
        binding.business.getText().clear();
        binding.address.getText().clear();
    }

    private boolean validateForm() {
        boolean noError = true;

        if (TextUtils.isEmpty(binding.storeName.getText())) {
            binding.storeNameLayout.setError("Invalid store name");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.description.getText())) {
            binding.descriptionLayout.setError("Invalid description.");
            noError = false;
        }

//        if (TextUtils.isEmpty(binding.dti.getText())) {
//            binding.dtiLayout.setError("Invalid DTI number");
//            noError = false;
//        }

        if (TextUtils.isEmpty(binding.business.getText())) {
            binding.businessLayout.setError("Invalid description.");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.address.getText())) {
            binding.addressLayout.setError("Invalid description.");
            noError = false;
        }

        return noError;
    }
}