package com.example.dimitrov.rougelike.objects;

/**
 * Created by Санчес on 20.06.2017.
 */

public class Character{
    private int X;
    private int Y;
    private int hp;

    public Character(int x, int y, int hp) {
        X = x;
        Y = y;
        this.hp = hp;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
