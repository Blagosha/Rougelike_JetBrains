package com.example.dimitrov.rougelike.core;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Entity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import com.example.dimitrov.rougelike.objects.Hero;

import java.util.ArrayList;

public class Toucher extends View {

    public float cameraX = 0, cameraY = 0;
    public float scale=200;
    public static int sideSize;
    public Hero hero;

    public void resetCam()
    {
        scale=200;
        cameraX = hero.getX()-getWidth()/scale/2+.5f;
        cameraY = hero.getY()-getHeight()/scale/2+.5f;
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

                        if (event.getDownTime() < 400)
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
                                scale *= k;
                                cameraX += (1 - 1 / k) * getWidth() / scale / 2;
                                cameraY += (1 - 1 / k) * getHeight() / scale / 2;

                            }
                        } catch (Exception e) {
                        }


                        break;
                    case MotionEvent.ACTION_UP:

                }

                invalidate();
                return true;
            }
        });
    }

}
