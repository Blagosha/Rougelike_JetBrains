package com.example.dimitrov.rougelike.objects;

import android.graphics.Point;

import java.util.Random;

public class Room {
    private int seflCellIndexesX, seflCellIndexesY; // Индексы ячейки комнаты
    private Point leftUpperCorner, rightBottomCorner; // Вершины комнаты
    private Point cellLeftUpperCorner; // Левый верхний угол ячейки

    Room(int seflCellIndexesX, int seflCellIndexesY) {
        this.seflCellIndexesX = seflCellIndexesX;
        this.seflCellIndexesY = seflCellIndexesY;
        cellLeftUpperCorner = new Point(seflCellIndexesX * Stage.cellSideSize, seflCellIndexesY * Stage.cellSideSize);

        int minRoomSide = 5; // минимальная длина стороны комнаты
        int roomHeight = random(minRoomSide, Stage.cellSideSize);
        int roomWidth = random(minRoomSide, Stage.cellSideSize);

        leftUpperCorner = new Point(cellLeftUpperCorner.x + random(0, Stage.cellSideSize - roomWidth), cellLeftUpperCorner.y + random(0, Stage.cellSideSize - roomHeight));
        rightBottomCorner = new Point(leftUpperCorner.x + roomWidth, leftUpperCorner.y + roomHeight);
    }

    public Point getCenter(){
        return new Point((rightBottomCorner.x+leftUpperCorner.x)/2,(rightBottomCorner.y+leftUpperCorner.y)/2);
    }

    public static int random(int mn, int mx) {
        Random random = new Random();
        return random.nextInt(mx - mn) + mn;
    }

    public Point getLeftUpperCorner(){
        return leftUpperCorner;
    }


    public Point getRightBottomCorner() {
        return rightBottomCorner;
    }
}
