package com.example.dimitrov.rougelike.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.GraphicsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static com.example.dimitrov.rougelike.core.Graphics.scale;

public class Stage implements GraphicsUser {
    private final int forestIndificator = 0;
    private final int wallIndificator = 1;
    private final int floorIndificator = 2;
    private final int chestIndificator = 3;
    private final int mosterIndificator = 4;

    public int sideSize;
    public int cntRooms;
    public static int cellSideSize;
    private static final int mxCntRooms = 30;
    private static final int mnCntRooms = 20;
    public int[][] stagePlan; // Общий массив этажа
    public int[][] orients;
    ArrayList<Junction> junctions; // массив переходов
    public Room[] rooms; // массив всех комнат

    int[] pred; // специальные переменные для СНМ
    ArrayList<int[]> graphEdges; // ребра графа


    public Stage(int sideSize) {
        this.sideSize = sideSize;
        cellSideSize = (int) (sideSize * 0.1);

        stagePlanGenereation();
    }

    private final int get(int room) {
        if (pred[room] == room) return room;

        return get(pred[room]);
    }

    private final void merge(int room1, int room2) {
        pred[get(room2)] = get(room1);
    }

    private final void stagePlanGenereation() {
        Random random = new Random();
        cntRooms = mnCntRooms + random.nextInt(mxCntRooms - mnCntRooms);// сгененрировали количество комнат
        junctions = new ArrayList<>();
        graphEdges = new ArrayList<>();
        rooms = new Room[cntRooms];
        pred = new int[cntRooms];
        stagePlan = new int[sideSize][sideSize];
        orients = new int[sideSize][sideSize];
        for (int i = 0; i < sideSize; i++)
            for (int j = 0; j < sideSize; j++)
                orients[i][j] = new Random().nextInt(4);

        boolean[][] cellUsed = new boolean[sideSize / cellSideSize][sideSize / cellSideSize];
        for (int i = 0; i < cellUsed.length; i++)
            for (int j = 0; j < cellUsed[0].length; j++)
                cellUsed[i][j] = false;

        int alreadyRoomsGenerated = 0;
        while (alreadyRoomsGenerated < cntRooms) {
            int x = random.nextInt(cellUsed.length);
            int y = random.nextInt(cellUsed.length);
            if (!cellUsed[x][y]) {
                cellUsed[x][y] = true;

                rooms[alreadyRoomsGenerated++] = new Room(x, y);
            }
        } // сгенерировали комнаты (по ячейкам)


        for (int i = 0; i < cntRooms; i++) pred[i] = i;

        for (int i = 0; i < cntRooms; i++) {
            for (int j = i + 1; j < cntRooms; j++) {
                graphEdges.add(new int[]{i, j});
            }
        }
        Collections.shuffle(graphEdges); // перемешанный массив ребер графа

        boolean[] edgeUsed = new boolean[graphEdges.size()];
        for (int i = 0; i < graphEdges.size(); i++) edgeUsed[i] = false;

        for (int i = 0; i < graphEdges.size(); i++) {
            int vertex = graphEdges.get(i)[0];
            int urtex = graphEdges.get(i)[1];
            if (get(vertex) != get(urtex)) {
                merge(vertex, urtex);
                edgeUsed[i] = true;
                junctions.add(new Junction(rooms[vertex], rooms[urtex]));
            }
        } // создали минимальный остов
        /*int cntGenerateRandomJunctions = (int) (junctions.size() * 0.2); // добавляем еще 20% рандомных переходов
        int counter = 0;
        while (counter < cntGenerateRandomJunctions) {
            int ind = random.nextInt(graphEdges.size());

            if (!edgeUsed[ind]) {
                int vertex = graphEdges.get(ind)[0];
                int urtex = graphEdges.get(ind)[1];
                junctions.add(new Junction(rooms[vertex], rooms[urtex]));
                edgeUsed[ind] = true;
                counter++;
            }
        } */ //добавили еще 20% рандомных переходов

        for (int i = 0; i < sideSize; i++) {
            for (int j = 0; j < sideSize; j++) {
                stagePlan[i][j] = 0;
            }
        }//пустой мир
        for (int k = 0; k < cntRooms; k++) {
            Point leftUpperCorner = rooms[k].getLeftUpperCorner();
            Point rightBottomCorner = rooms[k].getRightBottomCorner();
            for (int i = leftUpperCorner.x; i < rightBottomCorner.x; i++) {
                for (int j = leftUpperCorner.y; j < rightBottomCorner.y; j++) {
                    stagePlan[i][j] = 1;
                }
            }
            for (int i = leftUpperCorner.x + 1; i < rightBottomCorner.x - 1; i++) {
                for (int j = leftUpperCorner.y + 1; j < rightBottomCorner.y - 1; j++) {
                    stagePlan[i][j] = 2;
                }
            }
        }//комнаты
        for (Junction j : junctions) {
            Point from = j.getFrom();
            Point to = j.getTo();
            int fromX = from.x;
            int fromY = from.y;
            int toX = to.x;
            int toY = to.y;
            if (fromX > toX) {
                for (int i = toX; i <= fromX; i++) {
                    stagePlan[i][fromY] = 2;
                }
            } else {
                for (int i = fromX; i <= toX; i++) {
                    stagePlan[i][fromY] = 2;
                }
            }

            if (fromY > toY) {
                for (int i = toY; i <= fromY; i++) {
                    stagePlan[toX][i] = 2;
                }
            } else {
                for (int i = fromY; i <= toY; i++) {
                    stagePlan[toX][i] = 2;
                }
            }


        }//переходы

        for (int i = 1; i < sideSize - 1; i++) {
            for (int j = 1; j < sideSize - 1; j++) {
                if (stagePlan[i][j] == 2) {
                    if (stagePlan[i][j - 1] == 0) {
                        stagePlan[i][j - 1] = 1;
                    }
                    if (stagePlan[i][j + 1] == 0) {
                        stagePlan[i][j + 1] = 1;
                    }
                    if (stagePlan[i - 1][j] == 0) {
                        stagePlan[i - 1][j] = 1;
                    }
                    if (stagePlan[i + 1][j] == 0) {
                        stagePlan[i + 1][j] = 1;
                    }

                    if (stagePlan[i + 1][j + 1] == 0) {
                        stagePlan[i + 1][j + 1] = 1;
                    }

                    if (stagePlan[i + 1][j - 1] == 0) {
                        stagePlan[i + 1][j - 1] = 1;
                    }
                    if (stagePlan[i - 1][j - 1] == 0) {
                        stagePlan[i - 1][j - 1] = 1;
                    }

                    if (stagePlan[i - 1][j + 1] == 0) {
                        stagePlan[i - 1][j + 1] = 1;
                    }
                }
            }
        }
    }

    @Override
    public void onDraw(Canvas canvas, Graphics core) {
        Bitmap[][] bits = new Bitmap[3][4];
        for (int i = 0; i < 12; i++)
            bits[i / 4][i % 4] = null;
        float stageHeight = core.getHeight();
        float stageWidth = core.getWidth();
        for (int i = (int) core.cameraX - 1; i < core.cameraX + stageWidth / core.scale + 2; i++) {
            for (int j = (int) core.cameraY - 1; j < core.cameraY + stageHeight / core.scale + 2; j++) {
                if (i < 0 || j < 0 || i >= sideSize || j >= sideSize)
                    continue;

                int coordX = (int) ((i - core.cameraX) * core.scale) + 1;
                int coordY = (int) ((j - core.cameraY) * core.scale) + 1;

                Bitmap b = core.getBitmap("forest");
                int orientation = orients[i][j];
                switch (stagePlan[i][j]) {
                    case 0:
                        b = core.getBitmap("forest");
                        break;
                    case 1:
                        b = core.getBitmap("wall");
                        orientation = 0;
                        break;
                    case 2:
                        b = core.getBitmap("floor");
                }
                if (bits[stagePlan[i][j]][orientation] == null)
                    bits[stagePlan[i][j]][orientation] = core.rotateBitmap(core.resizeBitmap(b,
                            (int) (scale) + 1,
                            (int) (scale) + 1), 90 * orientation);
                b = bits[stagePlan[i][j]][orientation];
                core.drawBitmap(canvas, b, coordX, coordY);
            }
        }
    }

    public void getBitmaps(Graphics core) {
        core.setBitmap("floor", core.readBitmap(R.mipmap.floor));
        core.setBitmap("wall", core.readBitmap(R.mipmap.wall));
        core.setBitmap("forest", core.readBitmap(R.mipmap.forest));
    }

}
