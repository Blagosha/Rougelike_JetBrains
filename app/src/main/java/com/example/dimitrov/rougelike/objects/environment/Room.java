package com.example.dimitrov.rougelike.objects.environment;

import android.graphics.Point;

import java.util.Random;

public class Room {
    private int seflCellIndexesX, seflCellIndexesY; // indexes for room's cell
    private Point leftUpperCorner, rightBottomCorner; // room's corners
    private Point cellLeftUpperCorner; // left upper cell's corner

    Room(int seflCellIndexesX, int seflCellIndexesY) {
        this.seflCellIndexesX = seflCellIndexesX;
        this.seflCellIndexesY = seflCellIndexesY;
        cellLeftUpperCorner = new Point(seflCellIndexesX * Stage.cellSideSize, seflCellIndexesY * Stage.cellSideSize);

        int minRoomSide = 5; // minimal room side length
        int roomHeight = random(minRoomSide, Stage.cellSideSize);
        int roomWidth = random(minRoomSide, Stage.cellSideSize);

        leftUpperCorner = new Point(cellLeftUpperCorner.x + random(0, Stage.cellSideSize - roomWidth), cellLeftUpperCorner.y + random(0, Stage.cellSideSize - roomHeight));
        rightBottomCorner = new Point(leftUpperCorner.x + roomWidth, leftUpperCorner.y + roomHeight);
    }

    public Point getCenter() {
        return new Point((rightBottomCorner.x + leftUpperCorner.x) / 2, (rightBottomCorner.y + leftUpperCorner.y) / 2);
    }

    public static int random(int mn, int mx) {
        Random random = new Random();
        return random.nextInt(mx - mn) + mn;
    }

    public Point getLeftUpperCorner() {
        return leftUpperCorner;
    }


    public Point getRightBottomCorner() {
        return rightBottomCorner;
    }
}
