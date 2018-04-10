package com.finance.ccl.p2pfinance.utils;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;


import com.finance.ccl.p2pfinance.common.MyApplication;

/**
 * Created by ccl on 18-4-10.
 */

public class UIutils {

    public static int getColor(int colorId){
        return getContext().getResources().getColor(colorId);
    }

    public static Context getContext(){
        return MyApplication.context;
    }

    public static Handler getHandler(){
        return MyApplication.handler;
    }

    public static  View getXmlView(int layoutId){
        return android.view.View.inflate(getContext(),layoutId,null);
    }

    /*   public static int getColor(int colorId, Context context) {
        return context.getResources().getColor(colorId);
    }*/



}
