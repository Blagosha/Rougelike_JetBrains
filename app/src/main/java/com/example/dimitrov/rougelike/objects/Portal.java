package com.example.dimitrov.rougelike.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.core.Graphics;

import java.util.Random;

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
        if(!core.isInSight(x,y)&&core.fadeEnabled)
            return;
        Matrix m = new Matrix();
        float n_x=(x - core.cameraX) * core.scale,n_y=(y - core.cameraY) * core.scale;
        m.setTranslate(n_x,n_y);
        m.postRotate(new Random().nextInt(360),n_x+b.getWidth()/2,n_y+b.getHeight()/2);

        canvas.drawBitmap(b,m,new Paint());

        m.reset();
        m.setTranslate(n_x,n_y);
        m.postRotate(new Random().nextInt(360),n_x+b.getWidth()/2,n_y+b.getHeight()/2);

        canvas.drawBitmap(bottom.b,m,new Paint());



    }

    @Override
    public void onScaleChange(Graphics core) {
        bottom.onScaleChange(core);
        super.onScaleChange(core);
    }

    @Override
    public void getBitmaps(Graphics core) {
        core.addBitmap(R.mipmap.toppartofportal, "toppartofportal");
        core.addBitmap(R.mipmap.bottompartofportal, "bottompartofportal");
        onScaleChange(core);
    }
}
