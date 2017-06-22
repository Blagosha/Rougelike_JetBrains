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
        this.core = core;
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

    int newPosition;

    void processMoving(long delta) {
        if (newX == oldX + 1) {
            x += speed * delta;
            isReversed = false;
        }
        if (newX == oldX - 1) {
            x -= speed * delta;
            isReversed = true;
        }
        if (newY == oldY + 1) {
            y += speed * delta;
        }
        if (newY == oldY - 1) {
            y -= speed * delta;
        }
    }

    @Override
    public void movement(Graphics core) {
        int noticeDistance = (int) (1.5 * core.hero.viewRadius);
        long currentTime = System.currentTimeMillis();
        long delta = currentTime - lastTime;
        lastTime = currentTime;

        if (Math.hypot(x - oldX, y - oldY) > Math.hypot(newX - oldX, newY - oldY)
                || (newX == oldX && oldY == newY)) {
            x = oldX = newX;
            y = oldY = newY;

            Point p;

            if (Math.hypot(core.hero.newX - newX, core.hero.newY - newY) > noticeDistance)
                p = onFar();
            else
                p = bfs(new Point(oldX, oldY), new Point(core.hero.newX, core.hero.newY));

            newX = p.x;
            newY = p.y;

        }
        processMoving(delta);
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

    public Point bfs(Point from, Point to) {
        if(from.equals(to))
            return from;
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
                    if (used.contains(nextRetirePoint) && (map.get(nextRetirePoint) == map.get(current) - 1)) {
                        current = new Point(nextRetirePoint.x, nextRetirePoint.y);
                        continue;
                    }

                    nextRetirePoint = new Point(current.x - 1, current.y);
                    if (used.contains(nextRetirePoint) && (map.get(nextRetirePoint) == map.get(current) - 1)) {
                        current = new Point(nextRetirePoint.x, nextRetirePoint.y);
                        continue;
                    }

                    nextRetirePoint = new Point(current.x, current.y + 1);
                    if (used.contains(nextRetirePoint) && (map.get(nextRetirePoint) == map.get(current) - 1)) {
                        current = new Point(nextRetirePoint.x, nextRetirePoint.y);
                        continue;
                    }

                    nextRetirePoint = new Point(current.x, current.y - 1);
                    if (used.contains(nextRetirePoint) && (map.get(nextRetirePoint) == map.get(current) - 1)) {
                        current = new Point(nextRetirePoint.x, nextRetirePoint.y);
                    }
                }

                return current;
            }
            Point addPoint = new Point(current.x - 1, current.y);
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


}
