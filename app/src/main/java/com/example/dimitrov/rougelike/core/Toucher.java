package com.example.dimitrov.rougelike.core;

import android.content.Context;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

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

                        if (event.getDownTime() > 300
                                && event.getPointerCount() == 1
                                && event.getHistorySize() > 0) {
                            cameraX -= (event.getX() - event.getHistoricalX(currId, 0)) / scale;
                            cameraY -= (event.getY() - event.getHistoricalY(currId, 0)) / scale;
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
