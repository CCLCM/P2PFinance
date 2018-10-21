package com.finance.ccl.p2pfinance.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.bean.Product;
import com.finance.ccl.p2pfinance.common.AppNetConfig;
import com.finance.ccl.p2pfinance.common.MySimpleBaseAdapter;
import com.finance.ccl.p2pfinance.ui.RoundProgress;
import com.finance.ccl.p2pfinance.utils.LogUtils;
import com.finance.ccl.p2pfinance.utils.UIutils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductListFragment extends Fragment {
    AsyncHttpClient client = new AsyncHttpClient();

    @BindView(R.id.lv)
    ListView lv;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIutils.getXmlView(R.layout.fragment_producr_list);
        unbinder = ButterKnife.bind(this, view);
        initTilte();
        initDate();

        return view;
    }

    private void initDate() {
        client.post(AppNetConfig.PRODUCT, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                JSONObject jsonObject = JSON.parseObject(content);
                if (jsonObject.getBoolean("success")) {
                    String data = jsonObject.getString("data");
                    List<Product> productList = JSON.parseArray(data, Product.class);
                    LogUtils.logshow("chencl_" +productList.toString(),getClass());
                    lv.setAdapter(new MyAdapter(productList));
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });


    }

    private void initTilte() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class MyAdapter extends MySimpleBaseAdapter {

        public MyAdapter(List products) {
            super(products);
        }

        @Override
        public View getYouView(int position, View convertView, ViewGroup parent) {
            Product product = (Product) products.get(position);
            ViewHolder viewHolder = null;

            if (convertView == null) {
                convertView = View.inflate(parent.getContext(), R.layout.item_product_list, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //设置数据
            viewHolder.pMinzouzi.setText(product.minTouMoney);
            viewHolder.pMoney.setText(product.money);
            viewHolder.pName.setText(product.name);
            viewHolder.pSuodingdays.setText(product.suodingDays);
            viewHolder.pYearlv.setText(product.yearLv);
            viewHolder.pProgresss.setProgress(Integer.parseInt(product.progress));
            return convertView;
        }

    }


    static class ViewHolder {
        @BindView(R.id.p_name)
        TextView pName;
        @BindView(R.id.p_money)
        TextView pMoney;
        @BindView(R.id.p_yearlv)
        TextView pYearlv;
        @BindView(R.id.p_suodingdays)
        TextView pSuodingdays;
        @BindView(R.id.p_minzouzi)
        TextView pMinzouzi;
        @BindView(R.id.p_progresss)
        RoundProgress pProgresss;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
