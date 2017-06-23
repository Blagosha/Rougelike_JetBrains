package com.example.dimitrov.rougelike.objects.overlay;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.GraphicsUser;


public class Score extends GraphicsUser {
    public int score = 0;

    @Override
    public void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setTextSize(core.getHeight() / 10);
        Bitmap b = core.getBitmap("coin");
        core.drawBitmap(canvas,b,0,0,150,255);
        canvas.drawText(Integer.toString(score),150,p.getTextSize(), p);
    }

    public void getBitmaps() {
        core.addBitmap(R.mipmap.coin, "coin");
    }


}
