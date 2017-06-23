package com.example.dimitrov.rougelike.objects.environment;

import android.graphics.Canvas;

import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.GraphicsUser;

public class Labyrinth extends GraphicsUser {
    private static final int cntStages = 5; // count of stages in Labyrinth
    public Stage[] stages = new Stage[cntStages]; // array of stages

    public Labyrinth() {
        for (int i = 0; i < cntStages; i++) {
            int stageSideSize = 100;
            stages[i] = new Stage(stageSideSize);
        }
    }

    public void postDraw(Canvas canvas, Graphics core) {
        stages[0].postDraw(canvas, core);
    }

    @Override
    public void onDraw(Canvas canvas) {
        stages[0].onDraw(canvas);
    }

    @Override
    public void onScaleChange() {
        stages[0].onScaleChange();
    }

    @Override
    public void getBitmaps() {
        stages[0].getBitmaps();
    }
}
