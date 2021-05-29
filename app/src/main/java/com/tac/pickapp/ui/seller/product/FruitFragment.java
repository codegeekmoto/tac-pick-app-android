package com.tac.pickapp.ui.seller.product;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tac.pickapp.data.remote.dto.Product;
import com.tac.pickapp.databinding.FragmentFruitBinding;

public class FruitFragment extends ProductUi {

    private FragmentFruitBinding binding;

    public FruitFragment() {
        // Required empty public constructor
    }

    public static FruitFragment newInstance() {
        return new FruitFragment();
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

        productVM.fetchProduct(String.valueOf(productVM.getUser().getStore().getId()),
                Product.FRUIT);

        return binding.getRoot();
    }
}