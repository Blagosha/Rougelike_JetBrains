package com.example.dimitrov.rougelike.objects;


import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.core.Graphics;


public class Character extends Entity {
    private int hp;
    protected double speed;

    int oldX, oldY;
    int newX, newY;

    public Character(int x, int y, int hp) {
        super(x, y);
        oldX = newX = x;
        oldY = newY = y;
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }


    @Override
    public void getBitmaps(Graphics core) {
        core.addBitmap(R.mipmap.green, "green");
        core.addBitmap(R.mipmap.greenzombie, "greenzombie");
        core.addBitmap(R.mipmap.red, "red");
        core.addBitmap(R.mipmap.triangle, "triangle");

    }
}
