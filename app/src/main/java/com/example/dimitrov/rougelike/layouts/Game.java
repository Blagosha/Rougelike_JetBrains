package com.example.dimitrov.rougelike.layouts;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.dimitrov.rougelike.core.Graphics;
import com.example.dimitrov.rougelike.R;
import com.example.dimitrov.rougelike.objects.Chest;
import com.example.dimitrov.rougelike.objects.Hero;
import com.example.dimitrov.rougelike.objects.Labyrinth;
import com.example.dimitrov.rougelike.objects.Monster;
import com.example.dimitrov.rougelike.objects.Room;

import java.util.ArrayList;

public class Game extends AppCompatActivity {
    Graphics main_g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Applying graphics core to layout
        main_g = new Graphics(this);
        ((LinearLayout) findViewById(R.id.game_layout)).addView(main_g);
        Labyrinth l = new Labyrinth();
        main_g.addObj(l);

        int heroRoomGenerationIndex = Room.random(0, l.stages[0].rooms.length);
        Point p = l.stages[0].rooms[heroRoomGenerationIndex].getCenter();
        Hero hero = new Hero(p.x, p.y, 100); // creating hero 100 hp
        main_g.addObj(hero);

        int cntMonstersSpawn = Room.random(2, l.stages[0].rooms.length);
        ArrayList<Monster> monsters = new ArrayList<>();
        ArrayList<Chest>chests = new ArrayList<>();

        int counter = 0;
        while (counter < cntMonstersSpawn) {
            if (counter == heroRoomGenerationIndex) {
                counter++;
                cntMonstersSpawn++;
                continue;
            }

            p = l.stages[0].rooms[counter].getCenter();
            Monster monster = new Monster(p.x, p.y, 100);
            monsters.add(monster);
            main_g.addObj(monster);
            counter++;
        }

        for (int i=0;i<l.stages[0].stagePlan.length;i++){
            for (int j=0;j<l.stages[0].stagePlan[0].length;j++){
                if (l.stages[0].stagePlan[i][j]==2 && isWallNear(i,j,l)){
                    int chestGenerationFrequency = 100;
                    if (Room.random(0, chestGenerationFrequency) == 0) {
                        Chest chest = new Chest(i,j);
                        chests.add(chest);
                        main_g.addObj(chest);
                    }
                }
            }
        }
        Log.d("Blagoi",new Integer(chests.size()).toString());
    }

    private boolean isWallNear(int i, int j,Labyrinth l){

        return (l.stages[0].stagePlan[i-1][j]==1)||(l.stages[0].stagePlan[i+1][j]==1)||
                (l.stages[0].stagePlan[i][j-1]==1)||(l.stages[0].stagePlan[i][j+1]==1);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
