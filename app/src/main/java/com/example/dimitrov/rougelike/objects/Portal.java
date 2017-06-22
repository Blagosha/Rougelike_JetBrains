package com.example.dimitrov.rougelike.objects;

import android.graphics.Canvas;
import android.graphics.Matrix;

import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.core.Graphics;

import java.util.Random;

/**
 * Created by Санчес on 22.06.2017.
 */

public class Portal extends Entity {
    Entity bottom;
    public Portal(int x, int y) {
        super(x, y);
        bottom = new Entity(x,y);
        bottom.texture = "bottompartofportal";
        bottom.x = this.x;
        bottom.y = this.y;

        texture = "toppartofportal";
    }
    @Override
    public void onDraw(Canvas canvas, Graphics core) {
        bottom.b=core.rotateBitmap(bottom.b,new Random().nextInt(360));
        bottom.onDraw(canvas,core);
        super.onDraw(canvas, core);
    }

    @Override
    public void onScaleChange(Graphics core) {
        bottom.onScaleChange(core);
        super.onScaleChange(core);
    }

    @Override
    public void getBitmaps(Graphics core) {
        super.getBitmaps(core);
        bottom.getBitmaps(core);

    }
}
