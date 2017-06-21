package com.example.dimitrov.rougelike.objects;

import android.graphics.Point;
import android.util.Log;

import java.util.Random;

import static android.R.attr.tag;

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
