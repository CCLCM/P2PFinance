package com.finance.ccl.p2pfinance.common;


import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;


/**
 * Created by ccl on 18-4-10.
 */

public class MyApplication extends Application {
    public static Context context = null;
    public static Handler handler = null;
    public static Thread mainThread = null;
    public static int mainThreadId =0;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadId = Process.myTid();
        CrashHandler.getInsTance().init(this);
    }
}
