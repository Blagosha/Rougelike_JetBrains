package com.example.dimitrov.rougelike;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.DragEvent;
import android.view.View;

/**
 * Created by user on 6/20/17.
 */

public class Graphics extends View {
    Paint paint;

    Bitmap bitmapfloor;
    Bitmap bitmapwall;

    public Graphics(Context context) {
        super(context);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        bitmapfloor = BitmapFactory.decodeResource(getResources(), R.mipmap.floor);
        bitmapwall = BitmapFactory.decodeResource(getResources(), R.mipmap.wall);
        bitmapfloor=resizeBitmap(bitmapfloor,100,100);
        bitmapwall=resizeBitmap(bitmapwall,100,100);

    }

    public static Bitmap rotateBitmap(Bitmap b, int ang) {
        Matrix m = new Matrix();
        m.postRotate(ang);
        return Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
    }

    public static Bitmap resizeBitmap(Bitmap b, int w, int h) {
        return scaleBitmap(b, (float) w / b.getWidth(), (float) h / b.getHeight());
    }

    public static Bitmap scaleBitmap(Bitmap b, float scaleX, float scaleY) {
        Matrix m = new Matrix();
        m.preScale(scaleX, scaleY);
        return Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmapfloor, 0, 0, paint);

        canvas.drawBitmap(bitmapwall, 100, 100, paint);
    }

}
