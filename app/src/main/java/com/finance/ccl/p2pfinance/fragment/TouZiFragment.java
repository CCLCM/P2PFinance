package com.finance.ccl.p2pfinance.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.common.BaseFragment;
import com.finance.ccl.p2pfinance.utils.UIutils;
import com.loopj.android.http.RequestParams;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ccl on 18-4-9.
 */

public class TouZiFragment extends BaseFragment {
    private List<Fragment> fragmentList = new ArrayList<>();
    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.tab_indictor)
    TabPageIndicator tabIndictor;
    @BindView(R.id.pager)
    ViewPager pager;

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    public String getUrl() {
        return "";
    }

    @Override
    public void initData(String resultState) {

        initFragment();
        pager.setAdapter(new MyAdapter(getFragmentManager()));

        tabIndictor.setViewPager(pager);

    }

    private void initFragment() {
        ProductListFragment productListFragment = new ProductListFragment();
        ProductHotFragment productHotFragment = new ProductHotFragment();
        ProductRecommendFragment productRecommendFragment = new ProductRecommendFragment();
        fragmentList.add(productListFragment);
        fragmentList.add(productHotFragment);
        fragmentList.add(productRecommendFragment);
    }

    @Override
    public void initTitle() {
        titleTv.setText("我要投资");
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_touzi;
    }

    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return UIutils.getStringArray(R.array.touzi_tab)[position];
        }
    }
}
