package com.example.dimitrov.rougelike.objects.entities;


import com.example.dimitrov.rougelike.core.Graphics;

public class Chest extends Entity {
    private boolean isOpened = false;
    public Chest(int x, int y) {
        super(x, y);
        texture = "chest";
    }

    public void open() {
        texture = "chestOpen";
    }
    public void movement(Graphics core){
        if (!isOpened){
            if (Math.hypot(x - core.hero.x , y - core.hero.x )<1) {
                open();
                isOpened = true;
                core.score.score += 100;
            }

        }
    }
}
