package com.tac.pickapp.ui.seller.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.tac.pickapp.R;
import com.tac.pickapp.app.PickApp;
import com.tac.pickapp.databinding.FragmentMyProductBinding;
import com.tac.pickapp.ui.seller.store.StoreVM;
import com.tac.pickapp.ui.viewmodel.StoreVMFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MyProductFragment extends Fragment {

    @Inject
    StoreVMFactory vmFactory;
    private StoreVM storeVM;

    private FragmentMyProductBinding binding;

    public MyProductFragment() {
        // Required empty public constructor
    }


    public static MyProductFragment newInstance() {
        return new MyProductFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PickApp.getComponent().inject(this);
        storeVM = new ViewModelProvider(this, vmFactory).get(StoreVM.class);
        binding = FragmentMyProductBinding.inflate(inflater, container, false);

        binding.btnAdd.setOnClickListener(view -> {
            Navigation.findNavController(getActivity(), R.id.seller_nav_host_fragment)
                    .navigate(R.id.action_nav_store_to_nav_add_product);
        });

        setTabPager();

        return binding.getRoot();
    }

    private void setTabPager() {
        PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager(), 0);
        pagerAdapter.addFragment(SeafoodFragment.newInstance(), "Sea Food");
        pagerAdapter.addFragment(VegetableFragment.newInstance(), "Vegetable");
        pagerAdapter.addFragment(FruitFragment.newInstance(), "Fruit");
        binding.tab.setupWithViewPager(binding.pager);
        binding.pager.setAdapter(pagerAdapter);
    }

    public class PagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList = new ArrayList<>();
        private List<String> titles = new ArrayList<>();


        public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            titles.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
           return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}