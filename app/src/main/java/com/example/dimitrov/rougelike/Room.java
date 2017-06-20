package com.example.dimitrov.rougelike;

import android.graphics.Point;

import java.util.Random;

import static com.example.dimitrov.rougelike.Stage.cellSideSize;

public class Room {
    private int seflCellIndexesX, seflCellIndexesY; // Индексы ячейки комнаты
    Point leftUpperCorner, rightBottomCorner; // Вершины комнаты
    Point cellLeftUpperCorner; // Левый верхний угол ячейки

    Room(int seflCellIndexesX, int seflCellIndexesY) {
        this.seflCellIndexesX = seflCellIndexesX;
        this.seflCellIndexesY = seflCellIndexesY;
        cellLeftUpperCorner = new Point(seflCellIndexesX * cellSideSize, seflCellIndexesY * cellSideSize);

        int minRoomSide = 5; // минимальная длина стороны комнаты

        leftUpperCorner = new Point(random(cellLeftUpperCorner.x, cellLeftUpperCorner.x + cellSideSize - minRoomSide),
                random(cellLeftUpperCorner.y, cellLeftUpperCorner.y + cellSideSize - minRoomSide));

        rightBottomCorner = new Point(random(leftUpperCorner.x + minRoomSide, cellLeftUpperCorner.x + cellSideSize),
                random(leftUpperCorner.y + minRoomSide, cellLeftUpperCorner.y + cellSideSize));
    }

    int random(int mn, int mx) {
        Random random = new Random();
        return random.nextInt(mx - mn) + mn;
    }

}
