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
import com.finance.ccl.p2pfinance.ui.randomLayout.StellarMap;
import com.finance.ccl.p2pfinance.utils.UIutils;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductRecommendFragment extends Fragment {
    @BindView(R.id.stellarMap)
    StellarMap stellarMap;
    Unbinder unbinder;

    private String[] datas = new String[]{"超级新手计划", "乐享活系列90天计划", "钱包计划", "30天理财计划(加息2%)", "90天理财计划(加息5%)", "180天理财计划(加息10%)",
            "林业局投资商业经营", "中学老师购买车辆", "屌丝下海经商计划", "新西游影视拍摄投资", "Java培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "阿里巴巴洗钱计划", "铁路局回款计划", "高级白领赢取白富美投资计划"
    };
    private Random random;

    private String[] one = new String[8];
    private String[] two = new String[8];
    private int padding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIutils.getXmlView(R.layout.fragment_producr_recommend);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        for (int i =0; i< 8; i++) {
            one[i] = datas[i];
        }
        for (int i =0; i< 8; i++) {
            two[i] = datas[i+8];
        }
        padding = UIutils.dp2px(5);
        random = new Random();
        stellarMap.setInnerPadding(padding,padding,padding,padding);
        stellarMap.setAdapter(new MyAdapter());
        stellarMap.setRegularity(1,15);
        stellarMap.setGroup(0,true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class MyAdapter implements StellarMap.Adapter {
        @Override
        public int getGroupCount() {
            return 2;
        }

        @Override
        public int getCount(int group) {
            return 8;
        }

        @Override
        public View getView(int group, final int position, View convertView) {


            TextView tv = new TextView(getActivity());
            int r = random.nextInt(210);
            final int g =random.nextInt(210);
            int b = random.nextInt(210);
            tv.setTextColor(Color.rgb(r,g,b));
            tv.setTextSize(UIutils.dp2px(8)+random.nextInt(8));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),datas[position],Toast.LENGTH_LONG).show();
                }
            });
            if (group ==0 ){
                tv.setText(one[position]);
            } else {
                tv.setText(two[position]);
            }
            return tv;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {

            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return 1;
        }
    }
}
