package com.finance.ccl.p2pfinance;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity {

    @InjectView(R.id.content)
    FrameLayout content;
    @InjectView(R.id.iv_home)
    ImageView ivHome;
    @InjectView(R.id.tv_home)
    TextView tvHome;
    @InjectView(R.id.ll_home)
    LinearLayout llHome;
    @InjectView(R.id.iv_touzi)
    ImageView ivTouzi;
    @InjectView(R.id.tv_touzi)
    TextView tvTouzi;
    @InjectView(R.id.ll_touzi)
    LinearLayout llTouzi;
    @InjectView(R.id.iv_me)
    ImageView ivMe;
    @InjectView(R.id.tv_me)
    TextView tvMe;
    @InjectView(R.id.ll_me)
    LinearLayout llMe;
    @InjectView(R.id.iv_more)
    ImageView ivMore;
    @InjectView(R.id.tv_more)
    TextView tvMore;
    @InjectView(R.id.ll_more)
    LinearLayout llMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }
}
