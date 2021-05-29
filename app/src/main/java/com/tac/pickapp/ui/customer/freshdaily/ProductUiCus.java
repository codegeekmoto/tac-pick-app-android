package com.tac.pickapp.ui.customer.freshdaily;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.tac.pickapp.R;
import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.data.remote.dto.Product;
import com.tac.pickapp.ui.common.ProductVM;
import com.tac.pickapp.ui.util.Helper;
import com.tac.pickapp.ui.util.ResultObserver;
import com.tac.pickapp.ui.viewmodel.ProductVMFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public abstract class ProductUiCus extends Fragment implements ProductAdapter.OnItemClickListener {

    protected String category;

    @Inject
    ProductVMFactory vmFactory;
    protected ProductVM productVM;

    private List<Product> productList = new ArrayList<>();
    private ProductAdapter productAdapter;

    protected abstract String getCategory();
    protected abstract RecyclerView getProductView();
    protected abstract RelativeLayout getLoading();
    protected abstract RelativeLayout getNoDataLabel();
    protected abstract SearchView getSearchView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PickApp.getComponent().inject(this);
        productVM = new ViewModelProvider(this, vmFactory).get(ProductVM.class);
    }

    protected void init() {
        productVM.getAllProductResult().observe(getViewLifecycleOwner(), new ResultObserver<List<Product>>() {

            @Override
            public void onSuccess(List<Product> products) {
                productList = new ArrayList<>(products);

                if (products.size() < 1) {
                    getNoDataLabel().setVisibility(View.VISIBLE);
                    getProductView().setVisibility(View.GONE);
                    getSearchView().setVisibility(View.GONE);
                } else {
                    RecyclerView recyclerView = getProductView();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    productAdapter = new ProductAdapter(getContext(), products, ProductUiCus.this);
                    recyclerView.setAdapter(productAdapter);
                }
            }

            @Override
            public void onError(Throwable e) {
                Helper.resolveError(getActivity(), e);
            }

            @Override
            public void onComplete() {
                getLoading().setVisibility(View.GONE);
            }
        });

        getSearchView().setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // searchProduct(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void searchProduct(String name) {
        List<Product> searchRes = new ArrayList<>();

        if (!name.trim().equals("")) {
            for (Product product: productAdapter.getProductList()) {
                if (product.getName().contains(name)) {
                    searchRes.add(product);
                }
            }

            productAdapter.setProductList(searchRes);
        } else {
            productAdapter.setProductList(new ArrayList<>(productList));
        }
    }

    @Override
    public void onClick(Product product) {
        Bundle bundle = new Bundle();
        bundle.putString(Product.BUNDLE_KEY, new Gson().toJson(product));
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                .navigate(R.id.nav_place_order, bundle);
    }
}
