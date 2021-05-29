package com.tac.pickapp.ui.seller.product;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.tac.pickapp.R;
import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.data.remote.dto.Product;
import com.tac.pickapp.databinding.FragmentAddProductBinding;
import com.tac.pickapp.ui.common.ProductVM;
import com.tac.pickapp.ui.util.Helper;
import com.tac.pickapp.ui.viewmodel.ProductVMFactory;

import javax.inject.Inject;

public class ProductFormFragment extends Fragment {

    public static final String PRODUCT = "PRODUCT";

    @Inject
    ProductVMFactory vmFactory;
    private ProductVM productVM;

    private FragmentAddProductBinding binding;
    private Product product = new Product();

    public ProductFormFragment() {
        // Required empty public constructor
    }


    public static ProductFormFragment newInstance() {
        return new ProductFormFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PickApp.getComponent().inject(this);
        productVM = new ViewModelProvider(this, vmFactory).get(ProductVM.class);
        binding = FragmentAddProductBinding.inflate(inflater, container, false);

        initForm();
        setSpinners();

        return binding.getRoot();
    }

    private void setSpinners() {
        String[] categories = {"Sea Food", "Fruit", "Vegetable"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories);
        binding.category.setAdapter(adapter);

        binding.category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                product.setCategory(categories[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] quantities = {"Piece", "Kilogram", "Gram"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, quantities);
        binding.quantity.setAdapter(adapter2);
        binding.quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                product.setQuantity(quantities[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String args = null;

        if (getArguments() != null) {
            args = getArguments().getString(Product.BUNDLE_KEY);
        }

        if (args != null) {
            product = new Gson().fromJson(args, Product.class);
            binding.name.setText(product.getName());
            binding.description.setText(product.getDescription());
            binding.price.setText(product.getPrice());
            binding.numOfStock.setText(String.valueOf(product.getNumOfStock()));

            int catPos = adapter.getPosition(product.getCategory());
            binding.category.setSelection(catPos);
            int quantityPos = adapter.getPosition(product.getQuantity());
            binding.quantity.setSelection(quantityPos);
            product.setMode(Product.UPDATE);
        }
    }

    private void initForm() {
        Helper.addTextWatcher(binding.name, binding.nameLayout);
        Helper.addTextWatcher(binding.description, binding.descriptionLayout);
        Helper.addTextWatcher(binding.price, binding.priceLayout);
        Helper.addTextWatcher(binding.numOfStock, binding.numOfStockLayout);

        binding.btnNext.setOnClickListener(v -> {
            if (validateForm()) {
                product.setStoreId(productVM.getUser().getStore().getId());
                product.setName(binding.name.getText().toString());
                product.setDescription(binding.description.getText().toString());
                product.setPrice(binding.price.getText().toString());
                product.setNumOfStock(Integer.valueOf(binding.numOfStock.getText().toString()));

                if (product.getMode().equals("update")) {

                } else {

                }

                Bundle bundle = new Bundle();
                bundle.putString(PRODUCT, new Gson().toJson(product));

                Navigation.findNavController(getActivity(), R.id.seller_nav_host_fragment)
                        .navigate(R.id.action_nav_add_product_to_nav_product_cover, bundle);
            }
        });
    }

    private boolean validateForm() {
        boolean noError = true;

        if (TextUtils.isEmpty(binding.name.getText())) {
            binding.nameLayout.setError("Name is required");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.description.getText())) {
            binding.descriptionLayout.setError("Description is required");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.price.getText())) {
            binding.priceLayout.setError("Price is required");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.numOfStock.getText())) {
            binding.numOfStockLayout.setError("Number of stock is required");
            noError = false;
        }


        return noError;
    }
}