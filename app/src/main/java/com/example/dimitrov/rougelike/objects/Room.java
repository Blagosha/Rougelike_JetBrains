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

        leftUpperCorner = new Point(random(cellLeftUpperCorner.x, cellLeftUpperCorner.x + Stage.cellSideSize - minRoomSide),
                random(cellLeftUpperCorner.y, cellLeftUpperCorner.y + Stage.cellSideSize - minRoomSide));

        rightBottomCorner = new Point(random(leftUpperCorner.x + minRoomSide, cellLeftUpperCorner.x + Stage.cellSideSize),
                random(leftUpperCorner.y + minRoomSide, cellLeftUpperCorner.y + Stage.cellSideSize));
    }

    int random(int mn, int mx) {
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
