package com.example.dimitrov.rougelike.core;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public interface GraphicsUser {
    void onDraw(Canvas canvas, Graphics core);

    void getBitmaps(Graphics core);
}
