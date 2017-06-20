package com.example.dimitrov.rougelike.core;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by user on 6/20/17.
 */

public interface GraphicsUser {
    void onDraw(Canvas canvas, Graphics core);
    Bitmap getBitmap();
    String getBitmapIndex();
}
