package com.finance.ccl.p2pfinance.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.common.BaseActivity;
import com.github.mikephil.charting.charts.PieChart;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PieChartActivity extends BaseActivity {
    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.chart)
    PieChart chart;

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleTv.setText("柱状图");
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.title_right)
    public void  back(View view){
        closeCurrent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_piechart;
    }

}
