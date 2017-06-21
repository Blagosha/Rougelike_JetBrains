package com.example.dimitrov.rougelike.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graphics extends Toucher {

    private Map<String, Bitmap> bitmaps;
    private ArrayList<GraphicsUser> objects;

    public Graphics(Context context) {
        super(context);

        scale = 25;
        bitmaps = new HashMap<String, Bitmap>();
        objects = new ArrayList<GraphicsUser>();

    }


    //functions for playing with bitmaps
    public static Bitmap rotateBitmap(Bitmap b, int ang) {
        Matrix m = new Matrix();
        m.postRotate(ang);
        return Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
    }

    public static Bitmap resizeBitmap(Bitmap b, int w, int h) {
        return scaleBitmap(b, (float) w / b.getWidth(), (float) h / b.getHeight());
    }

    public static Bitmap scaleBitmap(Bitmap b, float scaleX, float scaleY) {
        Matrix m = new Matrix();
        m.preScale(scaleX, scaleY);
        return Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
    }

    public void addBitmap(int res,String name) {
        if (!bitmaps.containsKey(name)) {
            BitmapFactory.decodeResource(getResources(), res);
            bitmaps.put(name, resizeBitmap(BitmapFactory.decodeResource(getResources(), res),200,200));
        }
    }

    public Bitmap getBitmap(String name) {
        return bitmaps.get(name);
    }

    public void drawBitmap(Canvas canvas, Bitmap bitmap, int x, int y) {
        canvas.drawBitmap(bitmap, x, y, new Paint());
    }

    public void addObj(GraphicsUser obj) {
        objects.add(obj);
    }

    public void removeObj(GraphicsUser obj) {
        objects.remove(obj);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (cameraX < 0)
            cameraX = 0;
        if (cameraY < 0)
            cameraY = 0;
        if (cameraX > sideSize - getWidth() / scale)
            cameraX = sideSize - getWidth() / scale;
        if (cameraY > sideSize - getHeight() / scale)
            cameraY = sideSize - getHeight() / scale;
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).getBitmaps(this);
            objects.get(i).onDraw(canvas, this);
        }

    }

}
