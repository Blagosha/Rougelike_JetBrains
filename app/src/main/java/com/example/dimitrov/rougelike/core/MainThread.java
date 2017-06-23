package com.example.dimitrov.rougelike.core;

import com.example.dimitrov.rougelike.layouts.Game;


import java.util.Timer;
import java.util.TimerTask;

import static com.example.dimitrov.rougelike.core.GraphicsUser.core;

public class MainThread {
public final static int WIN = 2;
public final static int LOSE = 1;
public static int gameResult = 0;
    public void main(final Game game) {
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                game.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (gameResult == 0){
                            core.invalidate();
                        } else {
                            timer.cancel();
                            core.invalidate();
                        }

                    }
                });
            }
        };

        timer.schedule(task, 0, 5);

    }
}
