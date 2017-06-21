package com.example.dimitrov.rougelike.core;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class Toucher extends View {

    public float cameraX = 0, cameraY = 0;
    public float scale;
    public static int sideSize;


    public Toucher(Context context) {
        super(context);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int currIndex = (event.getActionIndex() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                int currId = event.getPointerId(currIndex);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:

                        if (event.getDownTime() < 300)
                            break;
                        try {
                            if (event.getPointerCount() == 1) {

                                cameraX -= (event.getX() - event.getHistoricalX(currId, 0)) / scale;
                                cameraY -= (event.getY() - event.getHistoricalY(currId, 0)) / scale;

                            }

                            if (event.getPointerCount() == 2) {

                                scale *= Math.hypot(
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
