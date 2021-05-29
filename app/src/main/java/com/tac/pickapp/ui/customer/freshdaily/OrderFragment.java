package com.tac.pickapp.ui.customer.freshdaily;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tac.pickapp.R;
import com.tac.pickapp.databinding.FragmentOrder2Binding;


public class OrderFragment extends Fragment {

    private FragmentOrder2Binding binding;

    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance(String param1, String param2) {
        return new OrderFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order2, container, false);
    }
}