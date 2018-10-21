package com.finance.ccl.p2pfinance.common;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.finance.ccl.p2pfinance.bean.Product;

import java.util.List;

public abstract class MySimpleBaseAdapter<T> extends BaseAdapter {
    protected final List<T> products;

    public MySimpleBaseAdapter(List<T> products) {
        this.products = products;
    }

    @Override
    public int getCount() {
        return products == null ? 0 : products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getYouView(position,convertView,parent);
    }

    public abstract View getYouView(int position, View convertView, ViewGroup parent);
}
