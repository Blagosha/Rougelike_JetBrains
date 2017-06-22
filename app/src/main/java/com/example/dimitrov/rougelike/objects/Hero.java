package com.example.dimitrov.rougelike.objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

import com.example.dimitrov.rougelike.core.Graphics;

public class Hero extends Character {
    public Hero(int x, int y, int hp) {
        super(x, y, hp);
        texture = "green";
        viewRadius = (255 / fadeRate + 1.71f) * thickness;
        speed = 0.001f;
    }

    public PointF newPos;
    public boolean isNew = false;

    public double viewRadius;

    float thickness = .5f;
    int fadeRate = 1;

    @Override
    public void onDraw(Canvas canvas, Graphics core) {
        super.onDraw(canvas, core);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(thickness * core.scale);

        int alpha = 0;
        int j = 0;
        for (float i = 0; i < core.sideSize * 2; i += thickness * 0.98) {
            if (alpha > 255)
                alpha = 255;
            if (alpha == 255)
                if (j++ > 10)
                    break;
            paint.setAlpha(alpha);
            canvas.drawCircle(
                    (int) ((x - core.cameraX + .5f) * core.scale),
                    (int) ((y - core.cameraY + .5f) * core.scale), i * core.scale, paint);
            alpha += fadeRate;
        }
    }
}
