package com.example.dimitrov.rougelike.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.DragEvent;
import android.view.View;

import com.example.dimitrov.rougelike.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by user on 6/20/17.
 */

public class Graphics extends View {

    Map<String, Bitmap> bitmaps;
    ArrayList<Drawable> objects;

    public Graphics(Context context) {
        super(context);
        bitmaps = new HashMap<String, Bitmap>();
        objects = new ArrayList<Drawable>();
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

    public Bitmap getBitmap(String name)
    {
        return bitmaps.get(name);
    }

    public void drawBitmap(Canvas canvas, Bitmap bitmap, int x, int y) {
        canvas.drawBitmap(bitmap, x, y, new Paint());
    }

    public void setBitmaps(Drawable d) {
        if (!bitmaps.containsKey(d.getBitmapIndex()))
            bitmaps.put(d.getBitmapIndex(), d.getBitmap());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i=0;i<objects.size();i++)
        {
            setBitmaps(objects.get(i));
            objects.get(i).onDraw(canvas,this);
        }
    }

}
