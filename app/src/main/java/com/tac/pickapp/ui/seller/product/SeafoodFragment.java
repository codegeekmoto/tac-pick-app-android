package com.tac.pickapp.ui.seller.product;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.tac.pickapp.data.remote.dto.Product;
import com.tac.pickapp.databinding.FragmentSeafoodBinding;

public class SeafoodFragment extends ProductUi {

    private FragmentSeafoodBinding binding;

    public SeafoodFragment() {
        // Required empty public constructor
    }

    public static SeafoodFragment newInstance() {
        return new SeafoodFragment();
    }

    @Override
    protected String getCategory() {
        return Product.SEA_FOOD;
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
        binding = FragmentSeafoodBinding.inflate(inflater, container, false);
        init();

        productVM.fetchProduct(String.valueOf(productVM.getUser().getStore().getId()),
                Product.SEA_FOOD);

        return binding.getRoot();
    }
}