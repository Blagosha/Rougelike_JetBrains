package com.example.dimitrov.rougelike.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.objects.overlay.Score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.dimitrov.rougelike.core.GraphicsUser.MONSTER;

public class Graphics extends Toucher {

    public Map<String, Bitmap> bitmaps;
    public ArrayList<GraphicsUser> objects;

    float scaleBuff;
    int init = 0;
    public Score score;
    Bitmap w,l;


    public Graphics(Context context) {
        super(context);
        bitmaps = new HashMap<>();
        objects = new ArrayList<>();
        w = BitmapFactory.decodeResource(getResources(), R.mipmap.youwon);
        l = BitmapFactory.decodeResource(getResources(),R.mipmap.youaredead);

    }


    //functions for playing with bitmaps
    public static Bitmap rotateBitmap(Bitmap b, int ang) {
        Matrix m = new Matrix();
        m.reset();
        m.setRotate(ang, b.getWidth() / 2, b.getHeight() / 2);
        return Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
    }

    public static Bitmap resizeBitmap(Bitmap b, int w, int h) {
        return scaleBitmap(b, (float) w / b.getWidth(), (float) h / b.getHeight());
    }

    public static Bitmap scaleBitmap(Bitmap b, float scaleX, float scaleY) {
        Matrix m = new Matrix();
        m.reset();
        m.postScale(scaleX, scaleY);
        return Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
    }

    public void addBitmap(int res, String name) {
        if (!bitmaps.containsKey(name)) {
            bitmaps.put(name, resizeBitmap(BitmapFactory.decodeResource(getResources(), res), 300, 300));
        }
    }

    public Bitmap getBitmap(String name) {
        return bitmaps.get(name);
    }

    public void drawBitmap(Canvas canvas, Bitmap b, int x, int y, int size, int alpha) {
        Paint paint = new Paint();
        paint.setAlpha(alpha);
        canvas.drawBitmap(b, new Rect(0, 0, b.getWidth(), b.getHeight()), new Rect(x, y, x + size, y + size), paint);
    }

    public void addObj(GraphicsUser obj) {
        objects.add(obj);
    }

    public void removeObj(GraphicsUser obj) {
        if(obj.type == MONSTER)
            score.score +=new Random().nextInt(5);
        objects.remove(obj);
    }

    final float scaleBorder = 50;

    void proceed(GraphicsUser g, Canvas canvas) {
        g.getBitmaps();
        if (scaleBuff != scale)
            g.onScaleChange();
        g.onDraw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (init++ == 0)
            resetCam();
        minScale = (float) getWidth() / scaleBorder;
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
        canvas.drawRect(0, 0, getWidth(), getHeight(), new Paint());

        proceed(labyrinth, canvas);
        for (int i = 0; i < objects.size(); i++) {
            proceed(objects.get(i), canvas);
        }
        proceed(hero, canvas);
        if (fadeEnabled)
            labyrinth.postDraw(canvas, this);
        proceed(score,canvas);
        scaleBuff = scale;

        if (MainThread.gameResult != 0) {
            if (MainThread.gameResult == 1) {
                drawBitmap(canvas, l, (getWidth() - getHeight()) / 2, 0, getHeight(), 255);
            }
            if (MainThread.gameResult == 2) {
                drawBitmap(canvas, w, (getWidth() - getHeight()) / 2, 0, getHeight(), 255);
            }
        }

    }

}
