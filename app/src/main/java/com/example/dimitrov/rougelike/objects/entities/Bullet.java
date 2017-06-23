package com.example.dimitrov.rougelike.objects.entities;

import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.GraphicsUser;
import com.example.dimitrov.rougelike.objects.entities.Entity;
import com.example.dimitrov.rougelike.objects.entities.Monster;

import java.util.ArrayList;

/**
 * Created by Санчес on 23.06.2017.
 */

public class Bullet extends Entity  {
    private double lastTime =System.currentTimeMillis();
    private float speed = 0.002f;
    private int direction;
    private ArrayList<Monster> monsters;


    public Bullet(int x, int y, int direction, ArrayList<Monster>monsters) {
        super(x, y);
        this.direction = direction;
        this.monsters = monsters;
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


    }

    private boolean isInMonster(Monster m){
        return Math.hypot(x-m.x,y-m.y)<1;
    }
}
