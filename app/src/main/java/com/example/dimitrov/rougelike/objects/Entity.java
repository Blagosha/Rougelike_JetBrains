package com.example.dimitrov.rougelike.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.GraphicsUser;



public class Entity implements GraphicsUser {
    public int x;
    public int y;
    public String texture;
    Bitmap b,rb;
    boolean isReversed=true;

    public Entity(int x, int y ) {
        this.x = x;
        this.y = y;
    }


    @Override
    public void onDraw(Canvas canvas, Graphics core) {
        if(!core.isInSight(x,y))
            return;
        canvas.drawBitmap(rb,
                (int) ((x - core.cameraX) * core.scale),
                (int) ((y - core.cameraY) * core.scale), new Paint());
        if(isReversed)
            canvas.drawBitmap(rb,
                    (int) ((x - core.cameraX) * core.scale),
                    (int) ((y - core.cameraY) * core.scale), new Paint());
        else
            canvas.drawBitmap(b,
                    (int) ((x - core.cameraX) * core.scale),
                    (int) ((y - core.cameraY) * core.scale), new Paint());
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

    @Override
    public void postDraw(Canvas canvas, Graphics core) {

    }
}
