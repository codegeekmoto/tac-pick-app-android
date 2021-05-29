package com.tac.pickapp.ui.customer.freshdaily;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.tac.pickapp.data.remote.dto.Product;
import com.tac.pickapp.databinding.FragmentVegetableBinding;


public class VegetableCusFragment extends ProductUiCus {

    private FragmentVegetableBinding binding;


    public VegetableCusFragment() {
        // Required empty public constructor
    }

    public static VegetableCusFragment newInstance() {
        return new VegetableCusFragment();
    }

    @Override
    protected String getCategory() {
        return Product.VEGETABLE;
    }

    @Override
    protected RecyclerView getProductView() {
        return binding.productList;
    }

    @Override
    protected RelativeLayout getLoading() {
        return binding.loading;
    }

    @Override
    protected SearchView getSearchView() {
        return binding.search;
    }

    @Override
    protected RelativeLayout getNoDataLabel() {
        return binding.noProduct;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentVegetableBinding.inflate(inflater, container, false);
        init();

        productVM.fetchProductByCategory(Product.VEGETABLE);

        return binding.getRoot();
    }
}