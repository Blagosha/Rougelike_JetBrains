package com.example.dimitrov.rougelike.objects;


import android.graphics.Picture;
import android.util.Pair;

import com.example.dimitrov.rougelike.core.Graphics;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class Monster extends Character {
    Graphics core;
    public Monster(int x, int y, int hp, Graphics core) {
        super(x, y, hp);
        this.core=core;
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
        int noticeDistance = (int) (1.5 * core.hero.viewRadius);
        long currentTime = System.currentTimeMillis();
        float delta = currentTime - lastTime;
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
                    int newPosition = Room.random(0, 4);

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
            Point heroCurrentPosition = new Point((int)(core.hero.x), (int)(core.hero.y));
            Point monsterCurrentPosition = new Point((int) x, (int) y);
            Point nextStepPoint = bfs(monsterCurrentPosition, heroCurrentPosition);
            // moving realization
        }
    }

    public Point bfs(Point from, Point to) {
        Set<Point> used = new TreeSet<Point>();
        Map<Point, Integer> map = new HashMap<Point, Integer>();
        Queue<Pair<Point, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(from, 0));

        while (q.size() != 0) {
            Point current = q.peek().first;
            Integer depth = q.poll().second;

            if (current.equals(to)) {
                while (map.get(current) > 1) {
                    Point nextRetirePoint = new Point(current.x + 1, current.y);
                    if (used.contains(nextRetirePoint) && (map.get(nextRetirePoint) < map.get(current))) {
                        current = new Point(nextRetirePoint.x, nextRetirePoint.y);
                        continue;
                    }

                    nextRetirePoint = new Point(current.x - 1, current.y);
                    if (used.contains(nextRetirePoint) && (map.get(nextRetirePoint) < map.get(current))) {
                        current = new Point(nextRetirePoint.x, nextRetirePoint.y);
                        continue;
                    }

                    nextRetirePoint = new Point(current.x, current.y + 1);
                    if (used.contains(nextRetirePoint) && (map.get(nextRetirePoint) < map.get(current))) {
                        current = new Point(nextRetirePoint.x, nextRetirePoint.y);
                        continue;
                    }

                    nextRetirePoint = new Point(current.x, current.y - 1);
                    if (used.contains(nextRetirePoint) && (map.get(nextRetirePoint) < map.get(current))) {
                        current = new Point(nextRetirePoint.x, nextRetirePoint.y);
                    }
                }

                return current;
            }
            Point addPoint = new Point(current.x - 1, current.y);
            boolean kek = !used.contains(addPoint);
            boolean mem = core.labyrinth.stages[0].isNotWall(addPoint.x, addPoint.y);
            if (!used.contains(addPoint) && core.labyrinth.stages[0].isNotWall(addPoint.x, addPoint.y)) {
                q.add(new Pair<Point, Integer>(addPoint, depth + 1));
                map.put(addPoint, depth + 1);
                used.add(addPoint);
            }

            addPoint = new Point(current.x + 1, current.y);
            if (!used.contains(addPoint) && core.labyrinth.stages[0].isNotWall(addPoint.x, addPoint.y)) {
                q.add(new Pair<>(addPoint, depth + 1));
                map.put(addPoint, depth + 1);
                used.add(addPoint);
            }

            addPoint = new Point(current.x, current.y - 1);
            if (!used.contains(addPoint) && core.labyrinth.stages[0].isNotWall(addPoint.x, addPoint.y)) {
                q.add(new Pair<>(addPoint, depth + 1));
                map.put(addPoint, depth + 1);
                used.add(addPoint);
            }

            addPoint = new Point(current.x, current.y + 1);
            if (!used.contains(addPoint) && core.labyrinth.stages[0].isNotWall(addPoint.x, addPoint.y)) {
                q.add(new Pair<>(addPoint, depth + 1));
                map.put(addPoint, depth + 1);
                used.add(addPoint);
            }
        }

        return new Point();
    }

    public int monsterHeroDistance2(Hero hero) {
        return (int) ((hero.x - x) * (hero.x - x) + (hero.y - y) * (hero.y - y));
    }

}
