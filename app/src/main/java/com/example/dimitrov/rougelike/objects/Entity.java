package com.example.dimitrov.rougelike.objects;

import android.graphics.Canvas;

import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.GraphicsUser;



public class Entity implements GraphicsUser {
    private int X;
    private int Y;
    protected String texture;

    public Entity(int x, int y ) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public void onDraw(Canvas canvas, Graphics core) {
        core.drawBitmap(canvas, core.resizeBitmap(core.getBitmap(texture),
                (int) (core.scale) + 1, (int) (core.scale) + 1),
                (int) ((X - core.cameraX) * core.scale),
                (int) ((Y - core.cameraY) * core.scale));
    }

    @Override
    public void getBitmaps(Graphics core) {
        core.addBitmap(R.mipmap.chest,"chest");
        core.addBitmap(R.mipmap.chestopen,"chestOpen");
    }
}
