package com.example.dimitrov.rougelike;

public class Labyrinth {
    private static final int cntStages = 5;
    Stage[] stages = new Stage[cntStages];

    Labyrinth() {
        for (int i = 0; i < cntStages; i++) {
            int stageSideSize = 100;
            stages[i] = new Stage(stageSideSize);
        }
    }

}
