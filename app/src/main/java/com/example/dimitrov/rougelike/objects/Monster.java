package com.example.dimitrov.rougelike.objects;


public class Monster extends Character {
    public Monster(int x, int y, int hp) {
        super(x, y, hp);

        int monsterInd = Room.random(0, 3);
        texture = "triangle";
        hp = 300;

        if (monsterInd == 0) {
            texture = "greenzombie";
            hp = 100;
        } else if (monsterInd == 1) {
            texture = "red";
            hp = 200;
        }
    }
}
