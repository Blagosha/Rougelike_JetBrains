package com.example.dimitrov.rougelike.core;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

public class Toucher extends View {

    public float cameraX = 0, cameraY = 0;

    public Toucher(Context context) {
        super(context);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        cameraX+=0.1;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        cameraX-=0.1;
                        cameraY-=0.1;
                        break;
                    case MotionEvent.ACTION_UP:
                        cameraY+=0.1;
                }
                invalidate();
                return true;
            }
        });
    }

}
