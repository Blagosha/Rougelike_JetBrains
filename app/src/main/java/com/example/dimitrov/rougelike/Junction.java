package com.example.dimitrov.rougelike;

import android.graphics.Point;

public class Junction {

    private Point from,to;

    public Junction(Room from, Room to) {
        this.from = getCenter(from);
        this.to = getCenter(to);

    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }

    private Point getCenter(Room room){
        Point leftUpperCorner = room.getLeftUpperCorner();
        Point rightBottomCorner = room.getRightBottomCorner();
        return new Point((rightBottomCorner.x-leftUpperCorner.x)/2,(rightBottomCorner.y-leftUpperCorner.y)/2);
    }


}
