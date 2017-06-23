package com.example.dimitrov.rougelike.objects.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.core.GraphicsUser;


public class Entity extends GraphicsUser {

    public float x;
    public float y;
    public String texture;
    public Bitmap b, rb;
    boolean isReversed = true;
    long lastTime = System.currentTimeMillis();

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public void onDraw(Canvas canvas) {

        if (!(core.isVisible(x, y) && core.isOnScreen(x, y)) && core.fadeEnabled)
            return;
        if (isReversed)
            core.drawBitmap(canvas, rb, (int) ((x - core.cameraX) * core.scale), (int) ((y - core.cameraY) * core.scale), (int) core.scale, 255);
        else
            core.drawBitmap(canvas, b, (int) ((x - core.cameraX) * core.scale), (int) ((y - core.cameraY) * core.scale), (int) core.scale, 255);
    }

    @Override
    public void onScaleChange() {
        if (b == null) {
            b = core.getBitmap(texture);
            rb = core.scaleBitmap(b, -1, 1);
        }
        if (b.getWidth() / core.scale > 1.25)
            b = core.resizeBitmap(core.getBitmap(texture), (int) core.scale, (int) core.scale);
        else if (b.getWidth() / core.scale < 0.8)
            b = core.resizeBitmap(core.getBitmap(texture), (int) core.scale, (int) core.scale);
        else return;
        rb = core.scaleBitmap(b, -1, 1);

    }

    @Override
    public void getBitmaps() {
        core.addBitmap(R.mipmap.chest, "chest");
        core.addBitmap(R.mipmap.chestopen, "chestOpen");
        core.addBitmap(R.mipmap.bullet,"bullet");
    }


}
