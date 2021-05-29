package com.tac.pickapp.ui.customer.freshdaily;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tac.pickapp.databinding.FragmentFreshDailyBinding;

import java.util.ArrayList;
import java.util.List;

public class FreshDailyFragment extends Fragment {

    private FragmentFreshDailyBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFreshDailyBinding.inflate(inflater, container, false);
        setTabPager();
        return binding.getRoot();
    }

    private void setTabPager() {
        PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager(), 0);
        pagerAdapter.addFragment(SeafoodCusFragment.newInstance(), "Sea Food");
        pagerAdapter.addFragment(VegetableCusFragment.newInstance(), "Vegetable");
        pagerAdapter.addFragment(FruitCusFragment.newInstance(), "Fruit");
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
