package com.finance.ccl.p2pfinance.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

import com.finance.ccl.p2pfinance.utils.LogUtils;

public class MyScrollView extends ScrollView {
     //返回操作的布局
    private View innerview;
    private float y;
    private Rect normal = new Rect();
    private boolean animationFinish = true;

    public MyScrollView(Context context) {
        this(context,null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        int childCount = getChildCount();
        LogUtils.logshow("chencl_  onFinishInflate " +childCount,getClass());
        if (childCount>0) {
            innerview = getChildAt(0);
        }
        super.onFinishInflate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        
        if (innerview == null) {
            return super.onTouchEvent(ev);
        } else {
            commonTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 处理之定义touch事件
     * @param ev
     */
    private void commonTouchEvent(MotionEvent ev) {
        Log.d("chencl_", "commonTouchEvent");
        if (animationFinish) {


            int action = ev.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    y = ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float preY = y == 0 ? ev.getY() : y;
                    float nowY = ev.getY();
                    int detailY = (int) (preY - nowY);
                    y = nowY;
                    //操作view进行拖动的一半
                    if (isNeedMove()) {
                        //布局改变位置之前,记录正常状态位置
                        if (normal.isEmpty()) {
                            normal.set(innerview.getLeft(), innerview.getTop(), innerview.getRight(),
                                    innerview.getBottom());
                        }
                        innerview.layout(innerview.getLeft(), innerview.getTop() - detailY / 2,
                                innerview.getRight(), innerview.getBottom() - detailY / 2);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    y = 0;
                    //把布局回回滚到原来的位置
                    LogUtils.logshow("chencl_  " +isNeedAnimation(), getClass());
                    if (isNeedAnimation()) {
                        animation();
                    }


                    break;
            }
        }
    }
    /**
     * 执行动画
     */
    private void animation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0,normal.top - innerview.getTop());
        translateAnimation.setDuration(200);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animationFinish = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                innerview.clearAnimation();
                innerview.layout(normal.left,normal.top,normal.right,normal.bottom);
                normal.setEmpty();
                animationFinish = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        innerview.startAnimation(translateAnimation);

    }

    private boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    public boolean isNeedMove() {
        int offset = innerview.getMeasuredHeight() - getHeight();
        int scrolly = getScrollY();
        if (scrolly ==0 || scrolly == offset) {
            return true;
        }
        return false;
    }
}
