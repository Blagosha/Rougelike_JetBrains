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
    float scaleBuff;
    int init=0;

    public Graphics(Context context) {
        super(context);
        bitmaps = new HashMap<>();
        objects = new ArrayList<>();

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

    public void addBitmap(int res, String name) {
        if (!bitmaps.containsKey(name)) {
            BitmapFactory.decodeResource(getResources(), res);
            bitmaps.put(name, resizeBitmap(BitmapFactory.decodeResource(getResources(), res), 300, 300));
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
    final float scaleBorder=50;

    @Override
    protected void onDraw(Canvas canvas) {
        if(init++==0)
            resetCam();
        minScale = (float)getWidth() / scaleBorder;
        maxScale = 300;
        if (scale < minScale)
            scale = minScale;
        if (scale > maxScale)
            scale = maxScale;
        if (cameraX > sideSize - getWidth() / scale)
            cameraX = sideSize - getWidth() / scale;
        if (cameraY > sideSize - getHeight() / scale)
            cameraY = sideSize - getHeight() / scale;
        if (cameraX < 0)
            cameraX = 0;
        if (cameraY < 0)
            cameraY = 0;
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).getBitmaps(this);
            if(scaleBuff!=scale)
                objects.get(i).onScaleChange(this);
            objects.get(i).onDraw(canvas, this);
        }
        hero.getBitmaps(this);
        if(scaleBuff!=scale)
            hero.onScaleChange(this);
        hero.onDraw(canvas, this);
        scaleBuff=scale;
    }

}
