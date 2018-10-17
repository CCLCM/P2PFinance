package com.finance.ccl.p2pfinance.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.finance.ccl.p2pfinance.R;

public class RoundProgress extends View {
    Paint paint = new Paint();
    private int roundColor;
    private int roundProgressColor;
    private float roundWidth;
    private int textColor;
    private float textSize;
    private int progress =50;
    private int max = 100;

    public RoundProgress(Context context) {
        this(context,null);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.RoundProgress);
        roundColor = typedArray.getColor(R.styleable.RoundProgress_roundColor, Color.RED);
        roundProgressColor = typedArray.getColor(R.styleable.RoundProgress_roundProgressColor, Color.GREEN);
        roundWidth = typedArray.getDimension(R.styleable.RoundProgress_roundWidth, 5);
        textColor = typedArray.getColor(R.styleable.RoundProgress_textColor, Color.GREEN);
        textSize = typedArray.getDimension(R.styleable.RoundProgress_textSize, 15);
        typedArray.recycle();
    }



    @Override
    protected void onDraw(Canvas canvas) {

        //第一步画外圈
        paint.setColor(roundColor);
        paint.setStrokeWidth(roundWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        int center = getWidth()/2;
        int radius = (int) (center -roundWidth/2);
        canvas.drawCircle(center,center,radius,paint);


        //第二部画文本
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        float textWidth = paint.measureText(progress + "%");
        canvas.drawText(progress+"%",center-textWidth/2,center+textSize/2,paint);


        //第三步 画弧形
        /**
         * 参数解释:
         * oval:绘制弧形所包含的矩形范围轮廓
         * 0:开始的角度
         * 360* progress / max: 扫描过的角度
         * false:是否包含圆心
         * paint:画弧形所所用的笔
         */
        paint.setColor(roundProgressColor);
        paint.setStrokeWidth(roundWidth);
        RectF oval = new RectF(center-radius,center-radius,radius+center,radius+center);
        canvas.drawArc(oval,0,360 * progress / max,false,paint);

    }

    public void setProgress(int progress){
        this.progress = progress;
        if (progress >= 100) {
            this.progress =100;
        } else if (progress <= 0){
            this.progress  =0;
        }
        postInvalidate();
    }
}
