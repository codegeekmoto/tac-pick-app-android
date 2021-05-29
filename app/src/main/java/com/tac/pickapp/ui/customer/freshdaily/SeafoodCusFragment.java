package com.tac.pickapp.ui.customer.freshdaily;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.tac.pickapp.data.remote.dto.Product;
import com.tac.pickapp.databinding.FragmentSeafoodBinding;

public class SeafoodCusFragment extends ProductUiCus {

    private FragmentSeafoodBinding binding;

    public SeafoodCusFragment() {
        // Required empty public constructor
    }

    public static SeafoodCusFragment newInstance() {
        return new SeafoodCusFragment();
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

        productVM.fetchProductByCategory(Product.SEA_FOOD);

        return binding.getRoot();
    }
}