package com.example.dimitrov.rougelike.core;


import android.graphics.Canvas;

public abstract class GraphicsUser {
    public static final int BULLET = 0;
    public static final int CHEST = 1;
    public static final int HERO = 2;
    public static final int MONSTER = 3;
    public static final int PORTAL = 4;


    public int type;
    public static Graphics core;

    public void onDraw(Canvas canvas) {
    }

    public void onScaleChange() {
    }

    public void getBitmaps() {
    }
}
