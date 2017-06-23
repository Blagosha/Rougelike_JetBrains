package com.example.dimitrov.rougelike.objects.entities;


import android.graphics.Canvas;

import com.example.dimitrov.rougelike.core.Graphics;

public class Chest extends Entity {
    private boolean isOpened = false;
    public Chest(int x, int y) {
        super(x, y);
        texture = "chest";
    }
    @Override
    public void onDraw(Canvas canvas) {
        movement(core);
        super.onDraw(canvas);
    }

    public void open() {
        texture = "chestOpen";
        b=null;
        onScaleChange();
    }
    public void movement(Graphics core){
        if (!isOpened){
            if ((x==core.hero.x)&&(y==core.hero.y)){
                open();
                isOpened = true;
                core.score.score += 100;
            }

        }
    }
}
