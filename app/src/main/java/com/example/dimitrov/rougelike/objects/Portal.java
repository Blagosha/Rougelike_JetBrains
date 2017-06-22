package com.example.dimitrov.rougelike.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.core.Graphics;

public class Portal extends Entity {
    Entity bottom;
    public Portal(int x, int y) {
        super(x, y);
        for(int i=0;i<18;i++)
            orientations[i/9][i%9]=null;
        bottom = new Entity(x,y);
        bottom.texture = "bottompartofportal";
        bottom.x = this.x;
        bottom.y = this.y;
        texture = "toppartofportal";
    }
    @Override
    public void onDraw(Canvas canvas, Graphics core) {
        b = orientations[0][aaa % 9];
        bottom.b = orientations[1][aaa++ % 9];
        bottom.onDraw(canvas, core);
        super.onDraw(canvas, core);
    }

    int aaa=0;
    Bitmap [][]orientations = new Bitmap[2][9];
    @Override
    public void onScaleChange(Graphics core) {
        bottom.onScaleChange(core);
        super.onScaleChange(core);
        if(orientations[0][0]==null)
        {
            for(int i=0;i<9;i++)
                orientations[0][i] = core.rotateBitmap(core.getBitmap("toppartofportal"),30*i);

            for(int i=0;i<9;i++)
                orientations[1][i] = core.rotateBitmap(core.getBitmap("bottompartofportal"),30*i);
        }
        /*if (orientations[0][0].getWidth() / core.scale > 1.25||orientations[0][0].getWidth() / core.scale < 0.8)
        {
            for(int i=0;i<9;i++)
                orientations[0][i] = core.resizeBitmap(core.rotateBitmap(core.getBitmap("toppartofportal"),40*i),(int)core.scale,(int)core.scale);

            for(int i=0;i<9;i++)
                orientations[1][i] = core.resizeBitmap(core.rotateBitmap(core.getBitmap("bottompartofportal"),40*i),(int)core.scale,(int)core.scale);
            Log.e("Deech","Yiss");
        }*/

    }

    @Override
    public void getBitmaps(Graphics core) {
        core.addBitmap(R.mipmap.toppartofportal, "toppartofportal");
        core.addBitmap(R.mipmap.bottompartofportal, "bottompartofportal");
        onScaleChange(core);
    }
}
