package com.example.dimitrov.rougelike.objects;

import android.graphics.Canvas;

import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.GraphicsUser;

public class Labyrinth implements GraphicsUser {
    private static final int cntStages = 5; // Количество уровней в лабиринте
    public Stage[] stages = new Stage[cntStages]; // массив уровней

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
    public void onDraw(Canvas canvas, Graphics core) {
        stages[0].onDraw(canvas, core);
    }

    @Override
    public void onScaleChange(Graphics core) {
        stages[0].onScaleChange(core);
    }

    @Override
    public void getBitmaps(Graphics core) {
        stages[0].getBitmaps(core);
    }
}
