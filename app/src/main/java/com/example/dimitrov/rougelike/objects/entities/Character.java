package com.example.dimitrov.rougelike.objects.entities;


import android.graphics.Canvas;
import android.util.Pair;

import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.MainThread;
import com.example.dimitrov.rougelike.core.Point;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;


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

    public void movement(Graphics core) {
        long currentTime = System.currentTimeMillis();
        long delta = currentTime - lastTime;
        lastTime = currentTime;

        if (Math.hypot(x - oldX, y - oldY) > Math.hypot(newX - oldX, newY - oldY)
                || (newX == oldX && oldY == newY)) {
            x = oldX = newX;
            y = oldY = newY;

            Point p = getTarget();

            newX = p.x;
            newY = p.y;

        }

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

    public Point getTarget() {
        return new Point(newX, newY);
    }

    public Point getNextStep(Point from, Point to) {
        if (from.equals(to))
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
            if (!used.contains(addPoint) && core.labyrinth.stages[0].isNotWall(addPoint.x, addPoint.y) && canGo(addPoint)) {
                q.add(new Pair<Point, Integer>(addPoint, depth + 1));
                map.put(addPoint, depth + 1);
                used.add(addPoint);
            }

            addPoint = new Point(current.x + 1, current.y);
            if (!used.contains(addPoint) && core.labyrinth.stages[0].isNotWall(addPoint.x, addPoint.y) && canGo(addPoint)) {
                q.add(new Pair<>(addPoint, depth + 1));
                map.put(addPoint, depth + 1);
                used.add(addPoint);
            }

            addPoint = new Point(current.x, current.y - 1);
            if (!used.contains(addPoint) && core.labyrinth.stages[0].isNotWall(addPoint.x, addPoint.y) && canGo(addPoint)) {
                q.add(new Pair<>(addPoint, depth + 1));
                map.put(addPoint, depth + 1);
                used.add(addPoint);
            }

            addPoint = new Point(current.x, current.y + 1);
            if (!used.contains(addPoint) && core.labyrinth.stages[0].isNotWall(addPoint.x, addPoint.y) && canGo(addPoint)) {
                q.add(new Pair<>(addPoint, depth + 1));
                map.put(addPoint, depth + 1);
                used.add(addPoint);
            }
        }

        return from;
    }

    boolean canGo(Point p)
    {
        return type!=HERO || core.labyrinth.stages[0].isExplored[p.x][p.y] || !core.fadeEnabled;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (MainThread.gameResult == 0)
            movement(core);
        if (hp <= 0)
            core.removeObj(this);
        movement(core);
        super.onDraw(canvas);
    }

    @Override
    public void getBitmaps() {
        core.addBitmap(R.mipmap.green, "green");
        core.addBitmap(R.mipmap.greenzombie, "greenzombie");
        core.addBitmap(R.mipmap.red, "red");
        core.addBitmap(R.mipmap.triangle, "triangle");

    }
}
