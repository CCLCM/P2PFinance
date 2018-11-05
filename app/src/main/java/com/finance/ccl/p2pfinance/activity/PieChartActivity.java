package com.finance.ccl.p2pfinance.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.common.BaseActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

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
    private Typeface mTf;
    private SpannableString mCenterText;
    @Override
    protected void initData() {
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        // apply styling
        chart.getDescription().setEnabled(false);chart.setHoleRadius(52f);
        chart.setTransparentCircleRadius(57f);
        chart.setCenterText(mCenterText);
        chart.setCenterTextTypeface(mTf);
        chart.setCenterTextSize(9f);
        chart.setUsePercentValues(true);
        chart.setExtraOffsets(5, 10, 50, 10);

        generateDataPie().setValueFormatter(new PercentFormatter());
        generateDataPie().setValueTypeface(mTf);
        generateDataPie().setValueTextSize(11f);
        generateDataPie().setValueTextColor(Color.WHITE);
        // set data
         chart.setData((PieData) generateDataPie());

        Legend l =  chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // do not forget to refresh the chart
        // holder.chart.invalidate();
         chart.animateY(900);
    }


    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return Pie data
     */
    private PieData generateDataPie() {

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            entries.add(new PieEntry((float) ((Math.random() * 70) + 30), "Quarter " + (i+1)));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        return new PieData(d);
    }



    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("by\nChencl");
        s.setSpan(new RelativeSizeSpan(1.6f), 0, 14, 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.VORDIPLOM_COLORS[0]), 0, 14, 0);
        s.setSpan(new RelativeSizeSpan(.9f), 14, 25, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, 25, 0);
        s.setSpan(new RelativeSizeSpan(1.4f), 25, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), 25, s.length(), 0);
        return s;
    }

    @Override
    protected void initTitle() {
        titleTv.setText("饼状图");
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.title_left)
    public void  back(View view){
        closeCurrent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_piechart;
    }

}
