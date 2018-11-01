package com.finance.ccl.p2pfinance.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.finance.ccl.p2pfinance.utils.LogUtils;

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


    private List<List<View>> allViews = new ArrayList<>();
    private List<Integer> allHeights = new ArrayList<>();


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int width = getWidth();
        int cCount = getChildCount();

        int lineWidth = 0;
        int lineHeight = 0;

        List<View> linviews = new ArrayList<>();

        for (int i =0; i <cCount; i++){
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            MarginLayoutParams mp = (MarginLayoutParams) child.getLayoutParams();
            if (lineWidth + childWidth + mp.leftMargin+mp.rightMargin > width) {
                allViews.add(linviews);
                allHeights.add(lineHeight);
                linviews = new ArrayList<>();
                lineWidth = 0;
                lineHeight = childHeight + mp.topMargin + mp.bottomMargin;
            }
            lineWidth += childWidth + mp.leftMargin + mp.rightMargin;
            lineHeight = Math.max(lineHeight,childHeight+mp.topMargin+mp.bottomMargin);
            linviews.add(child);
            if (i == cCount -1){
                allViews.add(linviews);
                allHeights.add(lineHeight);
            }

        }

        int left = 0;
        int top = 0;

        for (int i = 0; i < allViews.size(); i++) {
            int curLineHeight = allHeights.get(i);
            List<View>  views = allViews.get(i);
            for (View view : views) {
                int viewWidth = view.getMeasuredWidth();
                int viewHeight = view.getMeasuredHeight();
                MarginLayoutParams mp = (MarginLayoutParams) view.getLayoutParams();
                int lc = left + mp.leftMargin;
                int tc = top + mp.topMargin;
                int bc = tc + viewHeight;
                int rc = lc + viewWidth;
                view.layout(lc,tc,rc,bc);
                left += viewWidth + mp.rightMargin + mp.leftMargin;
            }
            left =0;
            top += curLineHeight;
        }
    }


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
            if (childWidth+linWidth + mp.leftMargin + mp.rightMargin > widthSize) {
                //换行 宽度就需要对比获得
                width = Math.max(width,linWidth);
                height += linHeight;
                linWidth = childWidth + mp.leftMargin + mp.rightMargin;
                linHeight = childHeight + mp.topMargin + mp.bottomMargin;
            } else {
                //不换行 高度对比获得
                linWidth += childWidth + mp.leftMargin + mp.rightMargin;
                linHeight = Math.max(linHeight,childHeight + mp.topMargin + mp.bottomMargin);
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
