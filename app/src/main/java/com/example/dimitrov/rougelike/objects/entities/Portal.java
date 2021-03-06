package com.example.dimitrov.rougelike.objects.entities;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.example.dimitrov.rougelike.R;

import java.util.Random;

public class Portal extends Entity {
    Entity bottom;

    public Portal(int x, int y) {
        super(x, y);
        type = PORTAL;
        bottom = new Entity(x, y);
        bottom.texture = "bottompartofportal";
        bottom.x = this.x;
        bottom.y = this.y;
        texture = "toppartofportal";
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (heroPortalDistance(core.hero)<0.5){
            core.hero.win();
        }
        if (!(core.isVisible(x, y) && core.isOnScreen(x, y)) && core.fadeEnabled)
            return;
        Matrix m = new Matrix();
        float n_x = (x - core.cameraX) * core.scale, n_y = (y - core.cameraY) * core.scale;
        m.setTranslate(n_x, n_y);
        m.postRotate(new Random().nextInt(360), n_x + b.getWidth() / 2, n_y + b.getHeight() / 2);

        canvas.drawBitmap(b, m, new Paint());

        m.reset();
        m.setTranslate(n_x, n_y);
        m.postRotate(new Random().nextInt(360), n_x + b.getWidth() / 2, n_y + b.getHeight() / 2);

        canvas.drawBitmap(bottom.b, m, new Paint());
    }

    public double heroPortalDistance(Hero hero){
        return Math.hypot(x-hero.x,y-hero.y);
    }

    @Override
    public void onScaleChange() {
        bottom.onScaleChange();
        super.onScaleChange();
    }

    @Override
    public void getBitmaps() {
        core.addBitmap(R.mipmap.toppartofportal, "toppartofportal");
        core.addBitmap(R.mipmap.bottompartofportal, "bottompartofportal");
        onScaleChange();
    }
}
