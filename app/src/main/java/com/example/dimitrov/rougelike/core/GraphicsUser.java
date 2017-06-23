package com.example.dimitrov.rougelike.core;


import android.graphics.Canvas;

public abstract class GraphicsUser {
    public final int BULLET = 0;
    public final int CHEST = 1;
    public final int HERO = 2;
    public final int MONSTER = 3;
    public final int PORTAL = 4;


    public int type;
    public static Graphics core;

    public void onDraw(Canvas canvas) {
    }

    public void onScaleChange() {
    }

    public void getBitmaps() {
    }
}
