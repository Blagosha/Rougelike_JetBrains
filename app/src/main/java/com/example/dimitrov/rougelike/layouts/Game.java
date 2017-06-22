package com.example.dimitrov.rougelike.layouts;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.core.MainThread;
import com.example.dimitrov.rougelike.objects.Chest;
import com.example.dimitrov.rougelike.objects.Hero;
import com.example.dimitrov.rougelike.objects.Labyrinth;
import com.example.dimitrov.rougelike.objects.Monster;
import com.example.dimitrov.rougelike.objects.Portal;
import com.example.dimitrov.rougelike.objects.Room;

import java.util.ArrayList;

public class Game extends AppCompatActivity {
    public Graphics core;
    public ArrayList<Monster> monsters;
    public ArrayList<Chest> chests;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Applying graphics core to layout
        core = new Graphics(this);
        ((LinearLayout) findViewById(R.id.game_layout)).addView(core);
        core.labyrinth = new Labyrinth();

        int heroRoomGenerationIndex = Room.random(0, core.labyrinth.stages[0].rooms.length);
        Point p = core.labyrinth.stages[0].rooms[heroRoomGenerationIndex].getCenter();
        core.hero = new Hero(p.x, p.y, 100); // creating hero 100 hp

        int cntMonstersSpawn = Room.random(2, core.labyrinth.stages[0].rooms.length);
        monsters = new ArrayList<>();
        chests = new ArrayList<>();

        int counter = 0;
        while (counter < cntMonstersSpawn) {
            if (counter == heroRoomGenerationIndex) {
                counter++;
                cntMonstersSpawn++;
                continue;
            }

            p = core.labyrinth.stages[0].rooms[counter].getCenter();
            Monster monster = new Monster(p.x, p.y, 100, core);
            monsters.add(monster);
            core.addObj(monster);
            counter++;
        } // spawning monsters



        for (int i = 0; i < core.labyrinth.stages[0].stagePlan.length; i++) {
            for (int j = 0; j < core.labyrinth.stages[0].stagePlan[0].length; j++) {
                if (core.labyrinth.stages[0].stagePlan[i][j] == 2 && isWallNear(i, j, core.labyrinth)) {
                    int chestGenerationFrequency = 100;
                    if (Room.random(0, chestGenerationFrequency) == 0) {
                        Chest chest = new Chest(i, j);
                        chests.add(chest);
                        core.addObj(chest);
                    }
                }
            }
        } // spawning chests

        counter=Room.random(2,core.labyrinth.stages[0].rooms.length);
        p = core.labyrinth.stages[0].rooms[counter].getCenter();
        Portal Portal = new Portal(p.x,p.y-1);
        core.addObj(Portal);// spawning Portal


        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new MainThread().main(Game.this);
            }
        });
    }

    private boolean isWallNear(int i, int j, Labyrinth l) { // return true if there is wall near else false
        return (l.stages[0].stagePlan[i - 1][j] == 1) || (l.stages[0].stagePlan[i + 1][j] == 1) ||
                (l.stages[0].stagePlan[i][j - 1] == 1) || (l.stages[0].stagePlan[i][j + 1] == 1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        thread.interrupt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        thread.run();
    }

}
