package com.finance.ccl.p2pfinance.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.common.BaseActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BarChartActivity extends BaseActivity {
    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.chart)
    BarChart chart;
    private Typeface mTf;
    @Override
    protected void initData() {
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
// apply styling
        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);

        XAxis xAxis =  chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        YAxis leftAxis =  chart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(5, false);
        leftAxis.setSpaceTop(20f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis =  chart.getAxisRight();
        rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(5, false);
        rightAxis.setSpaceTop(20f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        generateDataBar().setValueTypeface(mTf);

        // set data
        chart.setData(generateDataBar());
        chart.setFitBars(true);

        // do not forget to refresh the chart
//        holder.chart.invalidate();
        chart.animateY(700);

    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return Bar data
     */
    private BarData generateDataBar() {

        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry(i, (int) (Math.random() * 70) + 30));
        }

        BarDataSet d = new BarDataSet(entries, "New DataSet ");
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setHighLightAlpha(255);

        BarData cd = new BarData(d);
        cd.setBarWidth(0.9f);
        return cd;
    }

    @Override
    protected void initTitle() {
        titleTv.setText("柱状图");
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }
    @OnClick(R.id.title_left)
    public void  back(View view){
        closeCurrent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_barchart;
    }

}
