package com.example.dimitrov.rougelike.objects.entities;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;

import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.GraphicsUser;
import com.example.dimitrov.rougelike.objects.entities.Entity;
import com.example.dimitrov.rougelike.objects.entities.Monster;
import com.example.dimitrov.rougelike.objects.environment.Stage;

import java.util.ArrayList;


public class Bullet extends Entity {
    private float speed = 0.002f;
    public PointF target;

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        movement(core);
    }


    public Bullet(float x, float y, PointF target) {
        super((int) x, (int) y);
        this.target = target;
        texture = "bullet";
        type = BULLET;
        onScaleChange();
    }

    private void processMoving(double delta) {
        x += speed * delta * Math.cos(Math.atan2(target.y, target.x));
        y += speed * delta * Math.sin(Math.atan2(target.y, target.x));

    }

    public void movement(Graphics core) {
        double currentTime = System.currentTimeMillis();
        double delta = currentTime - lastTime;
        processMoving(delta);

        for (int i = 0; i < core.objects.size(); i++) {
            if (core.objects.get(i).type != MONSTER)
                break;

            Monster monster = (Monster) core.objects.get(i);

            if (isInMonster(monster)) {
                monster.setHp(monster.getHp() - 100);
                deleteBullet(core);
                return;
            }
        }

        if(x<0||y<0||x>core.sideSize||y>core.sideSize)
            deleteBullet(core);
        if (core.labyrinth.stages[0].stagePlan[Math.round(x)][Math.round(y)] == Stage.WALL ||
                core.labyrinth.stages[0].stagePlan[Math.round(x)][Math.round(y)] == Stage.FOREST) {
            deleteBullet(core);
            return;
        }
    }

    private boolean isInMonster(Monster m) {
        return Math.hypot(x - m.x, y - m.y) < 1;
    }

    private void deleteBullet(Graphics core) {
        core.removeObj(this);
    }


}
