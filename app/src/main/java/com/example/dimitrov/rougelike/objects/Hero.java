package com.example.dimitrov.rougelike.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.dimitrov.rougelike.core.Graphics;

public class Hero extends Character {
    public Hero(int x, int y, int hp) {
        super(x, y, hp);
        texture = "green";
    }

    @Override
    public void onDraw(Canvas canvas, Graphics core) {
        super.onDraw(canvas, core);

        float thickness = core.scale / 2;
        int fadeRate = 10;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(thickness);

        int alpha = 0;
        for (float i = 0; i < core.sideSize * core.scale * 2; i += thickness-1) {
            if (alpha > 255)
                alpha = 255;
            paint.setAlpha(alpha);
            canvas.drawCircle(
                    (int) ((X - core.cameraX + .5f) * core.scale),
                    (int) ((Y - core.cameraY + .5f) * core.scale), i, paint);
            alpha += fadeRate;
        }
    }
}
