package com.example.dimitrov.rougelike.core;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by user on 6/21/17.
 */

public class Toucher extends View {

    int cameraX, cameraY;
    public Toucher(Context context) {
        super(context);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

}
