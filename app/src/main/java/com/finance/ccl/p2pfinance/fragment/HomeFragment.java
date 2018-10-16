package com.finance.ccl.p2pfinance.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.bean.Image;
import com.finance.ccl.p2pfinance.bean.Index;
import com.finance.ccl.p2pfinance.bean.Product;
import com.finance.ccl.p2pfinance.common.AppNetConfig;
import com.finance.ccl.p2pfinance.ui.MyScrollView;
import com.finance.ccl.p2pfinance.utils.UIutils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ccl on 18-4-9.
 */

public class HomeFragment extends Fragment {
    AsyncHttpClient client = new AsyncHttpClient();
    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_right)
    ImageView titleRight;
    Unbinder unbinder;
    @BindView(R.id.vp_barner)
    ViewPager vpBarner;
    @BindView(R.id.circle_barner)
    CirclePageIndicator circleBarner;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.p_yearlv)
    TextView pYearlv;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.myscrollview)
    MyScrollView myscrollview;
    private Index mIndex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIutils.getXmlView(R.layout.fragment_home);
        unbinder = ButterKnife.bind(this, view);
        initTitle();
        initData();
        return view;
    }

    private void initData() {
        mIndex = new Index();

        client.post(AppNetConfig.INDEX,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                //super.onSuccess(content);
                JSONObject jsonObject = JSON.parseObject(content);
                String proInfo = jsonObject.getString("proInfo");
                Product product = JSON.parseObject(proInfo, Product.class);

                String imageArr = jsonObject.getString("imageArr");
                List<Image> imageList = JSON.parseArray(imageArr, Image.class);
                mIndex.imageList =imageList;
                mIndex.product = product;
                //适配数据
                vpBarner.setAdapter(new MyAdapter());

                //ViewPager 交给指示器

                circleBarner.setViewPager(vpBarner);

            }

            @Override
            public void onFailure(Throwable error, String content) {
//                super.onFailure(error, content);
                Toast.makeText(getActivity(),"请求服务器失败",Toast.LENGTH_LONG).show();
            }

        });
    }



    private void initTitle() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
        titleTv.setText((R.string.tv_main_main));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mIndex.imageList == null ? 0: mIndex.imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String imageUrl = mIndex.imageList.get(position).IMAURL;
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.get().load(imageUrl).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
