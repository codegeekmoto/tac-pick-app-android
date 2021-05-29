package com.tac.pickapp.ui.seller.product;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tac.pickapp.R;
import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.data.remote.dto.Product;
import com.tac.pickapp.databinding.FragmentProductCoverBinding;
import com.tac.pickapp.ui.common.ProductVM;
import com.tac.pickapp.ui.util.Helper;
import com.tac.pickapp.ui.util.ResultObserver;
import com.tac.pickapp.ui.viewmodel.ProductVMFactory;
import com.tac.pickapp.util.Constants;
import com.tac.pickapp.util.Util;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;

public class ProductCoverFragment extends Fragment {

    @Inject
    ProductVMFactory vmFactory;
    private ProductVM productVM;
    private FragmentProductCoverBinding binding;

    private Product product = new Product();
    private Uri coverUri;

    public ProductCoverFragment() {
        // Required empty public constructor
    }

    public static ProductCoverFragment newInstance() {
        return new ProductCoverFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PickApp.getComponent().inject(this);
        productVM = new ViewModelProvider(this, vmFactory).get(ProductVM.class);
        binding = FragmentProductCoverBinding.inflate(inflater, container, false);

        product = new Gson().fromJson(getArguments().getString(ProductFormFragment.PRODUCT), Product.class);

        if (product.getMode().equals(Product.UPDATE)) {
            ImageLoader.getInstance().displayImage(Constants.HOST+product.getCoverUrl(), binding.cover);
            binding.btnUpload.setText("Update");
        } else {
            binding.btnUpload.setText("Save");
        }

        binding.cover.setOnClickListener(v -> {
            Util.pickImageWithGallery(this);
        });

        binding.btnUpload.setOnClickListener(v -> {
            if (coverUri != null) {
                enableForm(false);
                productVM.uploadProductCover(coverUri);
            } else {
                Helper.dialogAlert(getContext(), "", "Please select product cover.");
            }
        });

        productVM.getUploadResult().observe(getViewLifecycleOwner(), new ResultObserver<String>() {
            @Override
            public void onSuccess(String imageUrl) {
                product.setCoverUrl(imageUrl);
                productVM.saveProduct(product);
            }

            @Override
            public void onError(Throwable e) {
                enableForm(true);
                Helper.resolveError(getActivity(), e);
            }
        });

        productVM.getSaveResult().observe(getViewLifecycleOwner(), new ResultObserver<Product>() {
            @Override
            public void onSuccess(Product product) {
                Helper.dialogAlert(getContext(), "", "Product successfully added!", (dialog, which) -> {
                    Navigation.findNavController(getActivity(), R.id.seller_nav_host_fragment)
                            .navigate(R.id.action_nav_product_cover_to_nav_store);
                });
            }

            @Override
            public void onError(Throwable e) {
                Helper.resolveError(getActivity(), e);
            }

            @Override
            public void onComplete() {
                enableForm(true);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            coverUri = data.getData();
            binding.cover.setImageURI(coverUri);
        }
    }

    private void enableForm(boolean enable) {
        binding.btnUpload.setEnabled(enable);
        binding.cover.setEnabled(enable);
        binding.progress.setVisibility(enable ? View.GONE : View.VISIBLE);
    }
}