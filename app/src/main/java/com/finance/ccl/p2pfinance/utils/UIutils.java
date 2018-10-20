package com.finance.ccl.p2pfinance.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.finance.ccl.p2pfinance.common.MyApplication;

/**
 * Created by ccl on 18-4-10.
 */

public class UIutils {

    public static String[] getStringArray(int arrayId){
        return getContext().getResources().getStringArray(arrayId);
    }


    public static int dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density*dp + 0.5);

    }

    public static int px2dp(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px/density + 0.5);
    }

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

    /**
     * 保证rnnnable 保险证在主线程中的
     * @param runnable
     */
    public static  void runOnUIThread(Runnable runnable) {
        if (isInMainThread()){
            runnable.run();
        } else {
           getHandler().post(runnable);
        }
    }

    private static boolean isInMainThread() {
        //当前线程的id
        int tid = android.os.Process.myTid();
        if (tid == MyApplication.mainThreadId) {
            return true;
        }
        return false;
    }


}
