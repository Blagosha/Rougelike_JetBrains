package com.example.dimitrov.rougelike.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.GraphicsUser;



public class Entity implements GraphicsUser {
    public int X;
    public int Y;
    public String texture;
    Bitmap b,rb;
    boolean isReversed=true;

    public Entity(int x, int y ) {
        X = x;
        Y = y;
    }


    @Override
    public void onDraw(Canvas canvas, Graphics core) {
        if(isReversed)
            core.drawBitmap(canvas, rb,
                    (int) ((X - core.cameraX) * core.scale),
                    (int) ((Y - core.cameraY) * core.scale));
        else
            core.drawBitmap(canvas, b,
                    (int) ((X - core.cameraX) * core.scale),
                    (int) ((Y - core.cameraY) * core.scale));
    }

    @Override
    public void onScaleChange(Graphics core) {
        b=core.resizeBitmap(core.getBitmap(texture),
                (int) (core.scale) + 1, (int) (core.scale) + 1);
        rb=core.scaleBitmap(b,-1,1);
    }

    @Override
    public void getBitmaps(Graphics core) {
        core.addBitmap(R.mipmap.chest,"chest");
        core.addBitmap(R.mipmap.chestopen,"chestOpen");
    }
}
