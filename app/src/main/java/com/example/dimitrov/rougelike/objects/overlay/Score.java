package com.example.dimitrov.rougelike.objects.overlay;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.GraphicsUser;


public class Score extends GraphicsUser {
    public int score = 0;

    @Override
    public void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setTextSize(core.getHeight() / 10);
        canvas.drawText("Score: " + Integer.toString(score), 0,p.getTextSize(), p);
    }
}
