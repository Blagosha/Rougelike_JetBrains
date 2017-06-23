package com.example.dimitrov.rougelike.objects;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.GraphicsUser;

/**
 * Created by Санчес on 23.06.2017.
 */

public class Score implements GraphicsUser {
    public int score=0;
    @Override
    public void onDraw(Canvas canvas, Graphics core) {
        Paint p =new Paint();
        p.setColor(Color.WHITE);
        p.setTextSize(core.getHeight()/10);
        canvas.drawText("Score: "+Integer.toString(score),0,0,p);
    }

    @Override
    public void onScaleChange(Graphics core) {

    }

    @Override
    public void getBitmaps(Graphics core) {

    }

    @Override
    public void postDraw(Canvas canvas, Graphics core) {

    }
}
