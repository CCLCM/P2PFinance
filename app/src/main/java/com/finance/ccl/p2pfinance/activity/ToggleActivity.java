package com.finance.ccl.p2pfinance.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToggleActivity extends BaseActivity {
    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.tg)
    ToggleButton tg;

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleTv.setText("自定义toggle");
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);

    }
    @OnClick(R.id.title_left)
    public void  back(View view){
        closeCurrent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_toggle;
    }


}
