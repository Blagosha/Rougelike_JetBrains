package com.example.dimitrov.rougelike.objects.entities;


import android.graphics.Canvas;

import com.example.dimitrov.rougelike.core.Graphics;

import java.util.Random;

public class Chest extends Entity {
    private boolean isOpened = false;

    public Chest(int x, int y) {
        super(x, y);
        texture = "chest";
        type = CHEST;
    }

    @Override
    public void onDraw(Canvas canvas) {
        movement(core);
        super.onDraw(canvas);
    }

    public void open() {
        texture = "chestOpen";
        b = null;
        onScaleChange();
    }

    public void movement(Graphics core) {
        if (!isOpened) {
            if ((Math.round(x) == Math.round(core.hero.x)) && (Math.round(y) == Math.round(core.hero.y))) {
                open();
                isOpened = true;
                core.score.score += new Random().nextInt(10) + 1;
            }

        }
    }
}
