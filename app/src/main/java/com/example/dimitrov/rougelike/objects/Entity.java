package com.example.dimitrov.rougelike.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.GraphicsUser;



public class Entity implements GraphicsUser {
    public float x;
    public float y;
    public String texture;
    Bitmap b,rb;
    boolean isReversed=true;
    long lastTime =System.currentTimeMillis();

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public void onDraw(Canvas canvas, Graphics core) {
        movement(core);

        if(!core.isInSight(x,y))
            return;
        if (isReversed)
            core.drawBitmap(canvas, rb, (int) ((x - core.cameraX) * core.scale), (int) ((y - core.cameraY) * core.scale), (int) core.scale, 255);
        else
            core.drawBitmap(canvas, b, (int) ((x - core.cameraX) * core.scale), (int) ((y - core.cameraY) * core.scale), (int) core.scale, 255);
    }

    @Override
    public void onScaleChange(Graphics core) {
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
    public void getBitmaps(Graphics core) {
        core.addBitmap(R.mipmap.chest,"chest");
        core.addBitmap(R.mipmap.chestopen,"chestOpen");
        core.addBitmap(R.mipmap.toppartofportal, "toppartofportal");
        core.addBitmap(R.mipmap.bottompartofportal, "bottompartofportal");
    }

    @Override
    public void postDraw(Canvas canvas, Graphics core) {

    }


    public void movement(Graphics core){

    }
}
