package com.example.dimitrov.rougelike.objects;


import com.example.dimitrov.rougelike.core.Graphics;

public class Monster extends Character {
    public Monster(int x, int y, int hp) {
        super(x, y, hp);

        int monsterInd = Room.random(0, 3);
        texture = "triangle";
        hp = 300;
        speed =1;

        if (monsterInd == 0) {
            texture = "greenzombie";
            hp = 100;

        } else if (monsterInd == 1) {
            texture = "red";
            hp = 200;
        }
    }

    @Override
    public void movement(Graphics core) {
        int noticeDistance = (int) (1.5 * core.hero.viewRadius);
        float currentTime = System.currentTimeMillis();
        float delta = currentTime - lastTime;
        lastTime = currentTime;
        if (monsterHeroDistance2(core.hero) > noticeDistance * noticeDistance) {
            //random monster moving
            if (isInCell()) {
                int newX = 0, newY = 0;
                do {
                    int newPosition = Room.random(0, 4);

                    switch (newPosition) {
                        case 0:
                            newX = (int) (x - 1);
                            newY = (int) y;
                            break;
                        case 1:
                            newX = (int) (x + 1);
                            newY = (int) (y);
                            break;
                        case 2:
                            newX = (int) (x);
                            newY = (int) (y - 1);
                            break;
                        case 3:
                            newX = (int) (x);
                            newY = (int) (y + 1);
                            break;

                    }
                } while (!core.labyrinth.stages[0].isNotWall(newX, newY));
            }

        } else {
            //monster moving to hero

        }
    }


    public int monsterHeroDistance2(Hero hero) {
        return (int) ((hero.x - this.x) * (hero.x - this.x) + (hero.y - this.y) * (hero.y - this.y));
    }

    public boolean isInCell(){
        float nanoDelta =0.1f;
        return (Math.abs((x-Math.round(x)))<nanoDelta)&&(Math.abs((y-Math.round(y)))<nanoDelta);
    }
}
