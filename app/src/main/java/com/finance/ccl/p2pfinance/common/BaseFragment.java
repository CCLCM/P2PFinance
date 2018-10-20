package com.finance.ccl.p2pfinance.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finance.ccl.p2pfinance.ui.LoadingPage;
import com.finance.ccl.p2pfinance.utils.UIutils;
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    Unbinder unbinder;
    private LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPage = new LoadingPage(container.getContext()){
            @Override
            public int layoutId() {
                return getLayoutId();
            }

            @Override
            protected void OnSuccess(ResultState resultState,View view) {
               unbinder = ButterKnife.bind(BaseFragment.this, view);
                     initTitle();
                     initData(resultState.getContent());
            }

            @Override
            protected RequestParams params() {
                return getParams();
            }

            @Override
            protected String url() {
                return getUrl();
            }

        };
        return loadingPage;

    }

    protected abstract RequestParams getParams();

    public abstract String getUrl();

    public abstract void initData(String resultState);

    public abstract void initTitle();

    public abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    public void show(){
        loadingPage.show();
    }

}
