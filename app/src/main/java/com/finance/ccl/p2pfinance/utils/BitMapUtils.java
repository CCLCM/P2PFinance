package com.finance.ccl.p2pfinance.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

public class BitMapUtils {

    public static Bitmap zoom(Bitmap source,float wf , float hf){

        Matrix matrix  = new Matrix();
        float sx = wf / source.getWidth();
        float sy = hf / source.getHeight();
        matrix.postScale(sx,sy);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

    }


    public static Bitmap circleBitMap(Bitmap source){
        final Paint paint = new Paint();
        int width = source.getWidth();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(width,width,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.drawCircle(width /2,width/2,width /2,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source,0,0,paint);
        return target;
    }
}
