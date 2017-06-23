package com.example.dimitrov.rougelike.objects.entities;


import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.Point;
import com.example.dimitrov.rougelike.objects.environment.Room;

public class Monster extends Character {

    public Monster(int x, int y, int hp) {
        super(x, y, hp);
        type = MONSTER;
        int monsterInd = Room.random(0, 3);
        texture = "triangle";
       setHp(300);
        speed = 0.001f;

        if (monsterInd == 0) {
            texture = "greenzombie";
            setHp(100);

        } else if (monsterInd == 1) {
            texture = "red";
            setHp(200);
        }
    }

    int newPosition;

    public void movement(Graphics core) {
        if (getHp()<=0){
            deleteEntity(core);
            return;
        }

        if (Math.hypot(core.hero.x - x, core.hero.y - y) < 0.5){
            core.hero.dead();
        }

        super.movement(core);
    }




    Point onFar() {
        int newX, newY;
        do {
            newPosition = Room.random(0, 4);

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
                default:
                    newX = (int) (x);
                    newY = (int) (y + 1);
            }
        } while (!core.labyrinth.stages[0].isNotWall(newX, newY));
        return new Point(newX, newY);
    }

    @Override
    public Point getTarget() {
        int noticeDistance = (int) (0.7 * core.hero.viewRadius);
        if (Math.hypot(core.hero.newX - newX, core.hero.newY - newY) > noticeDistance)
            return onFar();
        else
            return getNextStep(new Point(oldX, oldY), new Point(core.hero.newX, core.hero.newY));
    }

}
