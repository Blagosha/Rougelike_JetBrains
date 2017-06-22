package com.example.dimitrov.rougelike.objects;



import com.example.dimitrov.rougelike.core.Graphics;

public class Monster extends Character {
    public Monster(int x, int y, int hp) {
        super(x, y, hp);
        oldX=x;
        oldY=y;
        int monsterInd = Room.random(0, 3);
        texture = "triangle";
        hp = 300;
        speed = 0.001f;

        if (monsterInd == 0) {
            texture = "greenzombie";
            hp = 100;

        } else if (monsterInd == 1) {
            texture = "red";
            hp = 200;
        }
    }
    int oldX,oldY;
    int newX=-1,newY=-1;
    int newPosition;
    @Override
    public void movement(Graphics core) {
        int noticeDistance = (int) (0);
        long currentTime = System.currentTimeMillis();
        long delta = currentTime - lastTime;
        lastTime = currentTime;
        if (monsterHeroDistance2(core.hero) > noticeDistance * noticeDistance) {
            //random monster moving

            if (Math.hypot(x-oldX,y-oldY)>1||newX==-1) {
                if(newX!=-1) {
                    x = newX;
                    y = newY;
                }
                oldY= (int) y;
                oldX= (int) x;



                do {
                    newPosition = Room.random(0, 4);

                    switch (newPosition) {
                        case 0:
                            newX = (int) (x - 1);
                            newY = (int) y;
                            isReversed=true;
                            break;
                        case 1:
                            newX = (int) (x + 1);
                            newY = (int) (y);
                            isReversed=false;
                            break;
                        case 2:
                            newX = (int) (x);
                            newY = (int) (y - 1);
                            break;
                        case 3:
                            newX = (int) (x);
                            newY = (int) (y + 1);
                    }

                } while (!core.labyrinth.stages[0].isNotWall(newX, newY));
            }
            if (newPosition == 0) {
                x -= speed * delta;
            }
            if (newPosition == 1) {
                x += speed * delta;
            }
            if (newPosition == 2) {
                y -= speed * delta;
            }
            if (newPosition == 3) {
                y += speed * delta;
            }


        } else {
            //monster moving to hero

        }
    }


    public int monsterHeroDistance2(Hero hero) {
        return (int) ((hero.x - x) * (hero.x - x) + (hero.y - y) * (hero.y - y));
    }

}
