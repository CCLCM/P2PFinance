package com.finance.ccl.p2pfinance.common;

import android.content.Context;

import com.finance.ccl.p2pfinance.utils.LogUtils;

import java.lang.Thread.UncaughtExceptionHandler;

public class CrashHandler implements UncaughtExceptionHandler {
    private CrashHandler(){}
    public static CrashHandler crashHandler = new CrashHandler();
    private Context mContext;
    private UncaughtExceptionHandler defaultUncaughtExceptionHandler;
    public static CrashHandler getInsTance() {
        return crashHandler;
    }
    public  void init(Context context) {
        //将CrashHandler作为系统的默认处理器
        this.mContext = context;
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        LogUtils.logshow("chencl_",getClass());
    }
}
