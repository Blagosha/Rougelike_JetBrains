package com.example.dimitrov.rougelike.objects;

import android.support.annotation.NonNull;

public class Point extends android.graphics.Point implements Comparable<Point>{
    public Point() {
    }

    public Point(int x, int y) {
        super(x, y);
    }

    @Override
    public int compareTo(@NonNull Point point) {
        if (this.x > point.x || this.x == point.x && this.y > point.y) return 1;
        if (this.x < point.x || this.x == point.x && this.y < point.y) return -1;
        return (this.x == point.x && this.y == point.y ? 0 : 1);
    }
}
