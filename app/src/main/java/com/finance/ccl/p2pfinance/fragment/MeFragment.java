package com.finance.ccl.p2pfinance.fragment;

import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.common.AppNetConfig;
import com.finance.ccl.p2pfinance.common.BaseFragment;
import com.loopj.android.http.RequestParams;

/**
 * Created by ccl on 18-4-9.
 */

public class MeFragment  extends BaseFragment {

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    public String getUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    public void initData(String resultState) {

    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }
}
