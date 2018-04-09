package com.finance.ccl.p2pfinance.utils;

import android.util.Log;

public  class  LogUtils {

    public static void logshow(String str,Class cls){
        Log.d(cls.getSimpleName(),"chencl_" + str);
    }

}
