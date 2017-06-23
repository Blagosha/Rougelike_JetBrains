package com.example.dimitrov.rougelike.objects.entities;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.example.dimitrov.rougelike.core.Point;

public class Hero extends Character {
    public Hero(int x, int y, int hp) {
        super(x, y, hp);
        texture = "green";
        newPos = new PointF(x, y);
        viewRadius = (255 / fadeRate + 1.71f) * thickness;
        speed = 0.0015f;
    }

    public PointF newPos;
    public boolean isNew = false;

    @Override
    public Point getTarget() {
        if (!core.labyrinth.stages[0].isExplored[Math.round(newPos.x)][Math.round(newPos.y)] && core.fadeEnabled)
            return super.getTarget();
        return getNextStep(new Point((int) x, (int) y), new Point(Math.round(newPos.x), Math.round(newPos.y)));
    }

    public double viewRadius;

    float thickness = .5f;
    int fadeRate = 10;

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

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
            if (core.fadeEnabled)
                canvas.drawCircle(
                        (int) ((x - core.cameraX + .5f) * core.scale),
                        (int) ((y - core.cameraY + .5f) * core.scale), i * core.scale, paint);
            alpha += fadeRate;
        }
    }


}
