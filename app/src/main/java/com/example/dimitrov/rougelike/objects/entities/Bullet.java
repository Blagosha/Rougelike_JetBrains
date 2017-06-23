package com.example.dimitrov.rougelike.objects.entities;

import android.graphics.Canvas;

import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.GraphicsUser;
import com.example.dimitrov.rougelike.objects.entities.Entity;
import com.example.dimitrov.rougelike.objects.entities.Monster;
import com.example.dimitrov.rougelike.objects.environment.Stage;

import java.util.ArrayList;

/**
 * Created by Санчес on 23.06.2017.
 */

public class Bullet extends Entity  {
    private float speed = 0.002f;
    private int direction;
    private ArrayList<Monster> monsters;

    @Override
    public void onDraw(Canvas canvas) {
        movement(core);
        super.onDraw(canvas);
    }


    public Bullet(float x, float y, int direction, ArrayList<Monster>monsters) {
        super((int)x, (int)y);
        this.direction = direction;
        this.monsters = monsters;
        texture = "bullet";
        onScaleChange();
    }

    private void processMoving(double delta){
        if (direction==0){
            x-=speed*delta;
        }
        if (direction==1){
            x+=speed*delta;
        }
        if (direction==2){
            y-=speed*delta;
        }
        if (direction==3){
            y+=speed*delta;
        }
    }

    public void movement(Graphics core){
        double currentTime =System.currentTimeMillis();
        double delta = currentTime - lastTime;
        processMoving(delta);

        for (Monster monster: monsters){
            if (isInMonster(monster)){
                monster.setHp(monster.getHp()-100);
                if (monster.getHp()<=0){
                    monsters.remove(monster);
                }
                deleteEntity(core);
                break;
            }
        }

        if (core.labyrinth.stages[0].stagePlan[Math.round(x)][Math.round(y)]== Stage.WALL){
            deleteEntity(core);
        }
    }

    private boolean isInMonster(Monster m){
        return Math.hypot(x-m.x,y-m.y)<1;
    }



}
