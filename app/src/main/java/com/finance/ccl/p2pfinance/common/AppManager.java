package com.finance.ccl.p2pfinance.common;

import android.app.Activity;
import android.nfc.cardemulation.OffHostApduService;

import java.util.Stack;

/**
 * 设计成单例模式
 * Created by ccl on 18-4-11.
 * 统一程序当中的所有的activity栈管理
 *  添加、删除、删除当前，删除所有，求栈大小。。。
 */

public class AppManager {
    private Stack<Activity> activityStack = new Stack<>();

    public static AppManager appManager = null;
    private AppManager(){}
    public static AppManager getAppManager(){
        if (appManager == null) {
            appManager = new AppManager();
        }
        return appManager;
    }

    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity){

        for(int i = activityStack.size() -1; i>=0; i--) {
            Activity activity1 = activityStack.get(i);
            if (activity1.getClass().equals(activity.getClass())){
                activity1.finish();
                activityStack.remove(activity1);
            }
        }




 /*       if (activity != null) {
            for (Activity temp : activityStack) {
                if (temp.getClass().equals(activity.getClass())){
                    temp.finish();
                    activityStack.remove(temp);
                    break;
                }
            }
        }*/
    }


    public void removeCurrent(Activity activity) {
      Activity lastElement = activityStack.lastElement();
      lastElement.finish();
      activityStack.remove(lastElement);
    }

    public void removeLast(Activity activity) {
        Activity firstElement = activityStack.firstElement();
        firstElement.finish();
        activityStack.remove(firstElement);
    }

    public void removeAll() {
        for(int i = activityStack.size() -1; i>=0; i--) {
            Activity activity1 = activityStack.get(i);
            activity1.finish();
            activityStack.remove(activity1);
        }
    }

    public int getSize() {
        return activityStack.size();
    }

}
