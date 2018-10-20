package com.finance.ccl.p2pfinance.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.utils.UIutils;

public class ProductHotFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIutils.getXmlView(R.layout.fragment_producr_hot);
        return view;
    }
}
