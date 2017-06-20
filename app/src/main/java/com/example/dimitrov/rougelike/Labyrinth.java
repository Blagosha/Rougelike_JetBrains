package com.example.dimitrov.rougelike;

public class Labyrinth {
    private static final int cntStages = 5; // Количество уровней в лабиринте
    Stage[] stages = new Stage[cntStages]; // массив уровней

    Labyrinth() {
        for (int i = 0; i < cntStages; i++) {
            int stageSideSize = 100;
            stages[i] = new Stage(stageSideSize);
        }
    }

}
