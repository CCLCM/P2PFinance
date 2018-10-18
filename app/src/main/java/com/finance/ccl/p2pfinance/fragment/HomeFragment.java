package com.finance.ccl.p2pfinance.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.bean.Image;
import com.finance.ccl.p2pfinance.bean.Index;
import com.finance.ccl.p2pfinance.bean.Product;
import com.finance.ccl.p2pfinance.common.AppNetConfig;
import com.finance.ccl.p2pfinance.common.BaseFragment;
import com.finance.ccl.p2pfinance.ui.MyScrollView;
import com.finance.ccl.p2pfinance.ui.RoundProgress;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ccl on 18-4-9.
 */

public class HomeFragment extends BaseFragment {


    AsyncHttpClient client = new AsyncHttpClient();

    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.vp_barner)
    ViewPager vpBarner;
    @BindView(R.id.circle_barner)
    CirclePageIndicator circleBarner;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.p_progresss)
    RoundProgress pProgresss;
    @BindView(R.id.p_yearlv)
    TextView pYearlv;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.myscrollview)
    MyScrollView myscrollview;
    private Index mIndex;


    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    public String getUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    public void initData(String resultState) {
        if (TextUtils.isEmpty(resultState)) {
            return;
        }
        mIndex = new Index();
        JSONObject jsonObject = JSON.parseObject(resultState);
        String proInfo = jsonObject.getString("proInfo");
        Product product = JSON.parseObject(proInfo, Product.class);
        String imageArr = jsonObject.getString("imageArr");
        List<Image> imageList = JSON.parseArray(imageArr, Image.class);mIndex.imageList = imageList;
        mIndex.product = product;
        //适配数据
        vpBarner.setAdapter(new MyAdapter());
        circleBarner.setViewPager(vpBarner);
        totalProgress = Integer.parseInt(mIndex.product.progress);
        new Thread(runnable).start();

    }

    private int totalProgress;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int tempProgress = 0;
            try {
                while (tempProgress <= totalProgress) {
                    pProgresss.setProgress(tempProgress);
                    tempProgress++;
                    Thread.sleep(15);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void initTitle() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
        titleTv.setText((R.string.tv_main_main));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mIndex.imageList == null ? 0 : mIndex.imageList.size();
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
