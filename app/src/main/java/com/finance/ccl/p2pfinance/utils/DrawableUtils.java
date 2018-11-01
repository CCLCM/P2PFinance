package com.finance.ccl.p2pfinance.utils;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class DrawableUtils {
    /**
     *
     * @param rgb
     * @param corneradius
     * @return
     */
    public static GradientDrawable getDrawable(int rgb ,int corneradius){
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(rgb);
        gradientDrawable.setGradientType(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(corneradius);
        gradientDrawable.setStroke(UIutils.px2dp(1),rgb);
        return gradientDrawable;
    }

    public static StateListDrawable getSelect(Drawable normal,Drawable press) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled,android.R.attr.state_pressed},press);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled},normal);
        stateListDrawable.addState(new int[]{},normal);
        return stateListDrawable;
    }
}
