package com.example.dimitrov.rougelike.objects;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Stage {
    int sideSize, cntRooms;
    public static int cellSideSize;
    private static final int mxCntRooms = 100;
    int[][] stagePlan = new int[sideSize][sideSize]; // Общий массив этажа
    ArrayList<Junction> junctions = new ArrayList<>(); // массив переходов
    Room[] rooms = new Room[cntRooms]; // массив всех комнат

    int[] pred = new int[cntRooms]; // специальные переменные для СНМ
    ArrayList<int[]> graphEdges = new ArrayList<>(); // ребра графа


    public Stage(int sideSize) {
        this.sideSize = sideSize;
        cellSideSize = (int) (sideSize * 0.1);

        stagePlanGenereation();

        for (int i=0;i<sideSize;i++){
            for (int j=0;j<sideSize;j++){
                stagePlan[i][j]=0;
            }
        }//пустой мир

        for (int k=0;k<cntRooms;k++){
            Point leftUpperCorner = rooms[k].getLeftUpperCorner();
            Point rightBottomCorner = rooms[k].getRightBottomCorner();
            for (int i=leftUpperCorner.x;i<rightBottomCorner.x;i++){
                for (int j=leftUpperCorner.y;j<rightBottomCorner.y;j++){
                    stagePlan[i][j]=1;
                }
            }
            for (int i=leftUpperCorner.x+1;i<rightBottomCorner.x-1;i++){
                for (int j=leftUpperCorner.y+1;j<rightBottomCorner.y-1;j++){
                    stagePlan[i][j]=2;
                }
            }
        }//комнаты

        for (Junction j:junctions){
            Point from = j.getFrom();
            Point to =j.getTo();
            int fromX= from.x;
            int fromY= from.y;
            int toX = to.x;
            int toY = to.y;
            if (fromX>toX){
                for (int i=toX;i<=fromX;i++){
                    stagePlan[i][fromY]=2;
                }
            }
            else {
                for (int i=fromX;i<=toX;i++){
                    stagePlan[i][fromY]=2;
                }
            }

            if (fromY>toY){
                for (int i=toY;i<=fromY;i++){
                    stagePlan[toX][i]=2;
                }
            }
            else {
                for (int i=fromY;i<=toY;i++){
                    stagePlan[toX][i]=2;
                }
            }


        }//переходы

    }

    private final int get(int room) {
        if (pred[room] == room) return room;

        return get(pred[room]);
    }

    private final void merge(int room1, int room2) {
        room1 = get(room1);
        room2 = get(room2);

        pred[room2] = room1;
    }

    private final void stagePlanGenereation() {
        Random random = new Random();
        cntRooms = (random.nextInt(mxCntRooms) + 50) % mxCntRooms; // сгененрировали количество комнат

        boolean [][] cellUsed = new boolean[sideSize / cellSideSize][sideSize / cellSideSize];
        for (int i = 0; i < cellUsed.length; i++)
            for (int j = 0; j < cellUsed[0].length; j++)
                cellUsed[i][j] = false;

        int alreadyRoomsGenerated = 0;
        while (alreadyRoomsGenerated < cntRooms) {
            int x = random.nextInt(cellUsed.length);
            int y = random.nextInt(cellUsed.length);

            if (!cellUsed[x][y]) {
                cellUsed[x][y] = true;
                alreadyRoomsGenerated++;

                rooms[alreadyRoomsGenerated] = new Room(x, y);
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

        int cntGenerateRandomJunctions = (int) (junctions.size() * 0.2); // добавляем еще 20% рандомных переходов
        int counter = 0;
        while (counter < cntGenerateRandomJunctions) {
            int ind = random.nextInt(graphEdges.size());

            if (!edgeUsed[ind]) {
                int vertex = graphEdges.get(ind)[0];
                int urtex = graphEdges.get(ind)[1];
                junctions.add(new Junction(rooms[vertex], rooms[urtex]));
                edgeUsed[ind] = true;
            }
        } // добавили еще 20% рандомных переходов
    }
}
