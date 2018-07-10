package com.finance.ccl.p2pfinance.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.widget.Toast;

import com.finance.ccl.p2pfinance.utils.LogUtils;
import com.finance.ccl.p2pfinance.utils.UIutils;

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

    /**
     * 记录一下日志信息,并把这个提示信息汉化,反馈到后台
     *
     * **/
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
//        LogUtils.logshow("chencl_",getClass());
        if (isHandle(throwable))  {
            handlerException(throwable);
        } else {
            defaultUncaughtExceptionHandler.uncaughtException(thread,throwable);
        }

    }

    public boolean isHandle(Throwable throwable){
        if (throwable == null) {
            return  false;
        }
        return true;
        
    }

    private void handlerException(Throwable throwable) {
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext,"抱歉,系统出现未知异常,即将退出.",Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();

        collectionException(throwable);

        try {
            Thread.sleep(2000);
            AppManager.getInstance().removeAll();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 搜集异常崩溃信息
     * @param throwable
     */

    private void collectionException(Throwable throwable) {
        final String deviceInfo = Build.DEVICE + Build.VERSION.SDK_INT+Build.MODEL;
        final String errorInfo = throwable.getMessage();
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext,"错误信息已经上传到后台" + deviceInfo +errorInfo,Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        }
}
