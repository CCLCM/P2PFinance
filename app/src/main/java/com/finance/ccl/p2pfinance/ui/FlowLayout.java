package com.finance.ccl.p2pfinance.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        this(context,null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int width = getWidth();
        int cCount = getChildCount();
        for (int i =0; i <cCount; i++){
            View child = getChildAt(i);
        }


    }

    private List<List<View>> allViews = new ArrayList<>();
    private List<Integer> allHeights = new ArrayList<>();



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        int height = 0;
        int linWidth = 0;
        int linHeight = 0;
        
        //求取at_most模式下的宽高值

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            measureChild(childAt,widthMeasureSpec,heightMeasureSpec);
            int childWidth = childAt.getMeasuredWidth();
            int childHeight = childAt.getMeasuredHeight();
            MarginLayoutParams mp = (MarginLayoutParams) childAt.getLayoutParams();
            if (childWidth + mp.leftMargin + mp.rightMargin > widthSize) {
                //换行 宽度就需要对比获得
                width = Math.max(width,linWidth);
                height += linHeight;
                linWidth = childWidth + mp.leftMargin + mp.rightMargin;
                linHeight = childHeight + mp.topMargin + mp.bottomMargin;
            } else {
                //不换行 高度对比获得
                linWidth =+ childWidth + mp.leftMargin + mp.rightMargin;
                linHeight = Math.max(linHeight,childWidth+mp.topMargin+mp.bottomMargin);
            }
            //最后一个需要再计算一次
            if (i == childCount-1){
                width = Math.max(width,linWidth);
                height += linHeight;
            }
        }
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY?widthSize:width,heightMode == MeasureSpec.EXACTLY?heightSize:height);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        MarginLayoutParams mp = new MarginLayoutParams(getContext(), attrs);
        return mp;
    }
}
