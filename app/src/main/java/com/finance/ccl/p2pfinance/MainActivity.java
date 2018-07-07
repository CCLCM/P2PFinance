package com.finance.ccl.p2pfinance;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.ccl.p2pfinance.fragment.HomeFragment;
import com.finance.ccl.p2pfinance.fragment.MeFragment;
import com.finance.ccl.p2pfinance.fragment.MoreFragment;
import com.finance.ccl.p2pfinance.fragment.TouZiFragment;
import com.finance.ccl.p2pfinance.utils.LogUtils;
import com.finance.ccl.p2pfinance.utils.UIutils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends FragmentActivity {
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.iv_touzi)
    ImageView ivTouzi;
    @BindView(R.id.tv_touzi)
    TextView tvTouzi;
    @BindView(R.id.ll_touzi)
    LinearLayout llTouzi;
    @BindView(R.id.iv_me)
    ImageView ivMe;
    @BindView(R.id.tv_me)
    TextView tvMe;
    @BindView(R.id.ll_me)
    LinearLayout llMe;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.ll_more)
    LinearLayout llMore;
    private HomeFragment homeFragment;
    private TouZiFragment touziFragment;
    private MeFragment meFragment;
    private MoreFragment moreFragment;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        LogUtils.logshow("onCreate", getClass());
    }

    private void initData() {
        String str = null;
        if (str.equals("")){

        }

        setSelect(0);
    }

    @OnClick({R.id.ll_home, R.id.ll_touzi, R.id.ll_me, R.id.ll_more})
    public void changeTab(View view) {
        LogUtils.logshow("changeTab", getClass());
        switch (view.getId()) {
            case R.id.ll_home:
                setSelect(0);
                break;
            case R.id.ll_touzi:
                setSelect(1);
                break;
            case R.id.ll_me:
                setSelect(2);
                break;
            case R.id.ll_more:
                setSelect(3);
                break;
            default:
        }

    }

    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        hideFragment();
        reSetTab();
        switch (i) {
            case 0:
                //首页
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.content, homeFragment);
                }
                ivHome.setImageResource(R.drawable.bid01);
                tvHome.setTextColor(UIutils.getColor(R.color.home_back_selected));
                ft.show(homeFragment);
                break;
            case 1:
                //投资
                if (touziFragment == null) {
                    touziFragment = new TouZiFragment();
                    ft.add(R.id.content, touziFragment);
                }
                ivTouzi.setImageResource(R.drawable.bid03);
                tvTouzi.setTextColor(UIutils.getColor(R.color.home_back_selected));
                ft.show(touziFragment);
                break;
            case 2:
                //我的
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    ft.add(R.id.content, meFragment);
                }
                ivMe.setImageResource(R.drawable.bid05);
                tvMe.setTextColor(UIutils.getColor(R.color.home_back_selected));
                ft.show(meFragment);
                break;
            case 3:
                //更多
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                    ft.add(R.id.content, moreFragment);
                }
                ivMore.setImageResource(R.drawable.bid07);
                tvMore.setTextColor(UIutils.getColor(R.color.home_back_selected));
                ft.show(moreFragment);
                break;
            default:
        }
        ft.commit();
    }

    private void reSetTab() {
        ivHome.setImageResource(R.drawable.bid02);
        ivTouzi.setImageResource(R.drawable.bid04);
        ivMe.setImageResource(R.drawable.bid06);
        ivMore.setImageResource(R.drawable.bid08);
        tvHome.setTextColor(UIutils.getColor(R.color.home_back_unselected));
        tvTouzi.setTextColor(UIutils.getColor(R.color.home_back_unselected));
        tvMe.setTextColor(UIutils.getColor(R.color.home_back_unselected));
        tvMore.setTextColor(UIutils.getColor(R.color.home_back_unselected));
    }

    private void hideFragment() {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }

        if (touziFragment != null) {
            ft.hide(touziFragment);
        }

        if (meFragment != null) {
            ft.hide(meFragment);
        }

        if (moreFragment != null) {
            ft.hide(moreFragment);
        }
    }


}
