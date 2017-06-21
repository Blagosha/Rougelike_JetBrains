package com.example.dimitrov.rougelike.objects;

import android.graphics.Canvas;

import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.GraphicsUser;


public class Character extends Entity {
    private int hp;

    public Character(int x, int y, int hp) {
        super(x,y);
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
        core.setBitmap("green", core.readBitmap(R.mipmap.green));
        core.setBitmap("greenzombie", core.readBitmap(R.mipmap.greenzombie));
        core.setBitmap("red", core.readBitmap(R.mipmap.red));
        core.setBitmap("triangle", core.readBitmap(R.mipmap.triangle));
    }
}
