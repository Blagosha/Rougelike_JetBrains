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

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmapfloor, 50, 50, paint);
        canvas.drawBitmap(bitmapwall,100,100,paint);
    }

}
