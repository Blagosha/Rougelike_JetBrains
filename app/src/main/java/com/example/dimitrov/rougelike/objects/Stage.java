package com.example.dimitrov.rougelike.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.core.GraphicsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static com.example.dimitrov.rougelike.core.Toucher.sideSize;


public class Stage implements GraphicsUser {
    private final int FOREST = 0;
    private final int WALL = 1;
    private final int FLOOR = 2;


    public int cntRooms;
    public static int cellSideSize;
    private static final int mxCntRooms = 30;
    private static final int mnCntRooms = 20;
    public int[][] stagePlan; // all stage array
    public int[][] orients;
    public boolean[][] isExplored;
    ArrayList<Junction> junctions; // array of all junctions
    public Room[] rooms; // array of all rooms

    int[] pred; // speacial variables for DSU
    ArrayList<int[]> graphEdges; // graph edges


    public Stage(int sideSize) {
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
        sideSize = 100;
        cntRooms = mnCntRooms + random.nextInt(mxCntRooms - mnCntRooms); // generation random count for rooms
        junctions = new ArrayList<>();
        graphEdges = new ArrayList<>();
        rooms = new Room[cntRooms];
        pred = new int[cntRooms];
        stagePlan = new int[sideSize][sideSize];
        isExplored = new boolean[sideSize][sideSize];
        orients = new int[sideSize][sideSize];
        for (int i = 0; i < sideSize; i++)
            for (int j = 0; j < sideSize; j++) {
                orients[i][j] = new Random().nextInt(4);
            }

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
        Collections.shuffle(graphEdges); // shuffled graph edges array

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
        } // created carcass

        for (int i = 0; i < sideSize; i++) {
            for (int j = 0; j < sideSize; j++) {
                stagePlan[i][j] = FOREST;
            }
        }// empty stage

        for (int k = 0; k < cntRooms; k++) {
            Point leftUpperCorner = rooms[k].getLeftUpperCorner();
            Point rightBottomCorner = rooms[k].getRightBottomCorner();
            for (int i = leftUpperCorner.x; i < rightBottomCorner.x; i++) {
                for (int j = leftUpperCorner.y; j < rightBottomCorner.y; j++) {
                    stagePlan[i][j] = WALL;
                }
            }
            for (int i = leftUpperCorner.x + 1; i < rightBottomCorner.x - 1; i++) {
                for (int j = leftUpperCorner.y + 1; j < rightBottomCorner.y - 1; j++) {
                    stagePlan[i][j] = FLOOR;
                }
            }
        } // rooms

        for (Junction j : junctions) {
            Point from = j.getFrom();
            Point to = j.getTo();
            int fromX = from.x;
            int fromY = from.y;
            int toX = to.x;
            int toY = to.y;
            if (fromX > toX) {
                for (int i = toX; i <= fromX; i++) {
                    stagePlan[i][fromY] = FLOOR;
                }
            } else {
                for (int i = fromX; i <= toX; i++) {
                    stagePlan[i][fromY] = FLOOR;
                }
            }

            if (fromY > toY) {
                for (int i = toY; i <= fromY; i++) {
                    stagePlan[toX][i] = FLOOR;
                }
            } else {
                for (int i = fromY; i <= toY; i++) {
                    stagePlan[toX][i] = FLOOR;
                }
            }


        } // junctions

        for (int i = 1; i < sideSize - 1; i++) {
            for (int j = 1; j < sideSize - 1; j++) {
                if (stagePlan[i][j] == FLOOR) {
                    if (stagePlan[i][j - 1] == FOREST) {
                        stagePlan[i][j - 1] = WALL;
                    }
                    if (stagePlan[i][j + 1] == FOREST) {
                        stagePlan[i][j + 1] = WALL;
                    }
                    if (stagePlan[i - 1][j] == FOREST) {
                        stagePlan[i - 1][j] = WALL;
                    }
                    if (stagePlan[i + 1][j] == FOREST) {
                        stagePlan[i + 1][j] = WALL;
                    }

                    if (stagePlan[i + 1][j + 1] == FOREST) {
                        stagePlan[i + 1][j + 1] = WALL;
                    }

                    if (stagePlan[i + 1][j - 1] == FOREST) {
                        stagePlan[i + 1][j - 1] = WALL;
                    }
                    if (stagePlan[i - 1][j - 1] == FOREST) {
                        stagePlan[i - 1][j - 1] = WALL;
                    }

                    if (stagePlan[i - 1][j + 1] == FOREST) {
                        stagePlan[i - 1][j + 1] = WALL;
                    }
                }
            }
        }
    }

    Bitmap[][] bits = new Bitmap[3][4];
    Bitmap b;

    @Override
    public void onDraw(Canvas canvas, Graphics core) {
        for (int i = 0; i < sideSize; i++) {
            for (int j = 0; j < sideSize; j++) {
                isExplored[i][j] |= core.isInVision(i, j);

                if (!core.isInSight(i, j))
                    continue;

                int orientation = orients[i][j];
                switch (stagePlan[i][j]) {
                    case FOREST:
                        b = core.getBitmap("forest");
                        break;
                    case WALL:
                        b = core.getBitmap("wall");
                        orientation = 0;
                        break;
                    case FLOOR:
                    default:
                        b = core.getBitmap("floor");
                }
                if (bits[stagePlan[i][j]][orientation] == null)
                    bits[stagePlan[i][j]][orientation] = core.rotateBitmap(core.resizeBitmap(b,
                            (int) (core.scale) + 1,
                            (int) (core.scale) + 1), orientation * 90);
                b = bits[stagePlan[i][j]][orientation];
                int coordX = (int) ((i - core.cameraX) * core.scale);
                int coordY = (int) ((j - core.cameraY) * core.scale);
                core.drawBitmap(canvas, b, coordX, coordY, (int) core.scale + 1, 255);
            }
        }
    }

    @Override
    public void onScaleChange(Graphics core) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 4; j++)
                if (bits[i][j] != null)
                    if (bits[i][j].getWidth() / core.scale > 1.25 || bits[i][j].getWidth() / core.scale < 0.8)
                        bits[i][j] = null;

    }

    public void getBitmaps(Graphics core) {
        core.addBitmap(R.mipmap.floor, "floor");
        core.addBitmap(R.mipmap.wall, "wall");
        core.addBitmap(R.mipmap.forest, "forest");
    }

    @Override
    public void postDraw(Canvas canvas, Graphics core) {
        for (int i = 0; i < sideSize; i++) {
            for (int j = 0; j < sideSize; j++) {
                if (core.isInSight(i, j) || !core.isOnScreen(i, j))
                    continue;
                if (!isExplored[i][j])
                    continue;
                if (stagePlan[i][j] == FOREST)
                    continue;

                int orientation = orients[i][j];
                switch (stagePlan[i][j]) {
                    case WALL:
                        b = core.getBitmap("wall");
                        orientation = 0;
                        break;
                    case FLOOR:
                    default:
                        b = core.getBitmap("floor");
                }
                if (bits[stagePlan[i][j]][orientation] == null)
                    bits[stagePlan[i][j]][orientation] = core.rotateBitmap(core.resizeBitmap(b,
                            (int) (core.scale) + 1,
                            (int) (core.scale) + 1), orientation * 90);
                b = bits[stagePlan[i][j]][orientation];
                int coordX = (int) ((i - core.cameraX) * core.scale);
                int coordY = (int) ((j - core.cameraY) * core.scale);
                core.drawBitmap(canvas, b, coordX + (int) core.scale / 10, coordY + (int) core.scale / 10, (int) core.scale * 4 / 5, 42);
            }
        }
    }

}
