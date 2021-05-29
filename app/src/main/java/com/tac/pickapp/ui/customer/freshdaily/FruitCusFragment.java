package com.tac.pickapp.ui.customer.freshdaily;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.tac.pickapp.data.remote.dto.Product;
import com.tac.pickapp.databinding.FragmentFruitBinding;

public class FruitCusFragment extends ProductUiCus {

    private FragmentFruitBinding binding;

    public FruitCusFragment() {
        // Required empty public constructor
    }

    public static FruitCusFragment newInstance() {
        return new FruitCusFragment();
    }

    @Override
    protected String getCategory() {
        return Product.FRUIT;
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
    protected RelativeLayout getNoDataLabel() {
        return binding.noProduct;
    }

    @Override
    protected SearchView getSearchView() {
        return binding.search;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFruitBinding.inflate(inflater, container, false);
        init();
        productVM.fetchProductByCategory(Product.FRUIT);

        return binding.getRoot();
    }
}