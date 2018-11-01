package com.finance.ccl.p2pfinance.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.ui.FlowLayout;
import com.finance.ccl.p2pfinance.utils.DrawableUtils;
import com.finance.ccl.p2pfinance.utils.UIutils;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductHotFragment extends Fragment {
    private String[] datas = new String[]{
            "新手计划", "乐享活系列90天计划", "钱包", "30天理财计划(加息2%)",
            "林业局投资商业经营与大捞一笔", "中学老师购买车辆",
            "屌丝下海经商计划", "新西游影视拍", "Java培训老师自己周转", "HelloWorld",
            "C++-C-ObjectC-java", "Android vs ios", "算法与数据结构", "JNI与NDK",
            "team working"};
    @BindView(R.id.flow)
    FlowLayout flow;
    Unbinder unbinder;
    private Random random;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIutils.getXmlView(R.layout.fragment_producr_hot);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        random = new Random();
        for (final String data : datas) {
            TextView textView = new TextView(getActivity());
//            textView.setTextSize();
            ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mp.leftMargin = UIutils.dp2px(10);
            mp.topMargin = UIutils.dp2px(10);
            mp.leftMargin = UIutils.dp2px(10);
            mp.bottomMargin = UIutils.dp2px(10);
            int padding = UIutils.dp2px(5);
            textView.setPadding(padding,padding,padding,padding);

            int r = random.nextInt(210);
            int g = random.nextInt(210);
            int b = random.nextInt(210);

            textView.setLayoutParams(mp);
            textView.setText(data);
            textView.setBackground(DrawableUtils.getSelect(DrawableUtils.getDrawable(Color.rgb(r,g,b),
                    UIutils.dp2px(5)),DrawableUtils.getDrawable(Color.WHITE,UIutils.dp2px(5))));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),data,Toast.LENGTH_LONG).show();
                }
            });
            flow.addView(textView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
