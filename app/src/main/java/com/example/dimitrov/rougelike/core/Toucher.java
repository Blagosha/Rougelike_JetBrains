package com.example.dimitrov.rougelike.core;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.dimitrov.rougelike.objects.entities.Hero;
import com.example.dimitrov.rougelike.objects.environment.Labyrinth;

public class Toucher extends View {

    public boolean fadeEnabled = true;
    public float cameraX = 0, cameraY = 0;
    public float scale = 200, minScale, maxScale;
    public static int sideSize;
    public Hero hero;
    public Labyrinth labyrinth;

    public void resetCam() {
        scale = 200;
        cameraX = hero.x - getWidth() / scale / 2 + .5f;
        cameraY = hero.y - getHeight() / scale / 2 + .5f;
    }

    public Toucher(Context context) {
        super(context);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int currIndex = (event.getActionIndex() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                int currId = event.getPointerId(currIndex);
                if (event.getPointerCount() == 3) {
                    resetCam();
                }
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:

                        if (event.getEventTime() - event.getDownTime() < 200)
                            break;
                        try {
                            if (event.getPointerCount() < 3) {

                                cameraX -= (event.getX() - event.getHistoricalX(currId, 0)) / scale;
                                cameraY -= (event.getY() - event.getHistoricalY(currId, 0)) / scale;

                                cameraX -= (event.getX(event.findPointerIndex(1)) - event.getHistoricalX(event.findPointerIndex(1), 0)) / scale;
                                cameraY -= (event.getY(event.findPointerIndex(1)) - event.getHistoricalY(event.findPointerIndex(1), 0)) / scale;

                            }

                            if (event.getPointerCount() == 2) {

                                double k = Math.hypot(
                                        event.getX(event.findPointerIndex(0))
                                                - event.getX(event.findPointerIndex(1)),
                                        event.getY(event.findPointerIndex(0))
                                                - event.getY(event.findPointerIndex(1))

                                ) / Math.hypot(
                                        event.getHistoricalX((event.findPointerIndex(0)), 0)
                                                - event.getHistoricalX(event.findPointerIndex(1), 0),
                                        event.getHistoricalY(event.findPointerIndex(0), 0)
                                                - event.getHistoricalY(event.findPointerIndex(1), 0)
                                );
                                k = Math.max(k, minScale / scale);
                                k = Math.min(k, maxScale / scale);
                                scale *= k;
                                cameraX += (1 - 1 / k) * getWidth() / scale / 2;
                                cameraY += (1 - 1 / k) * getHeight() / scale / 2;

                            }
                        } catch (Exception e) {
                        }


                        break;
                    case MotionEvent.ACTION_UP:
                        if (event.getPointerCount() == 1 && event.getEventTime() - event.getDownTime() < 200) {
                            hero.newPos = new PointF(event.getX() / scale + cameraX - .5f, event.getY() / scale + cameraY - .5f);
                            Log.d("Yeah",Float.toString(hero.newPos.x)+" "+Float.toString(hero.newPos.y));
                            hero.isNew = true;
                        }

                }

                invalidate();
                return true;
            }
        });
    }

    public boolean isOnScreen(float x, float y) {
        x += .5f;
        y += .5f;
        if (x < (int) cameraX - 1 || y < cameraY - 1)
            return false;
        if (x > cameraX + getWidth() / scale + 1 || y > cameraY + getHeight() / scale + 1)
            return false;
        return true;
    }

    public boolean isVisible(float x, float y) {
        x += .5f;
        y += .5f;
        return Math.hypot(hero.x - x, hero.y - y) <= hero.viewRadius;
    }


}
