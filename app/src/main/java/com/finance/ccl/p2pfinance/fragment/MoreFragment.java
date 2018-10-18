package com.finance.ccl.p2pfinance.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.common.BaseFragment;
import com.finance.ccl.p2pfinance.utils.UIutils;
import com.loopj.android.http.RequestParams;

/**
 * Created by ccl on 18-4-9.
 */

public class MoreFragment extends BaseFragment {


    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public void initData(String resultState) {

    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_more;
    }
}
