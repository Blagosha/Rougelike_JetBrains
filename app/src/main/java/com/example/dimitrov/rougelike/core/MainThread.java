package com.example.dimitrov.rougelike.core;

import com.example.dimitrov.rougelike.layouts.Game;


import java.util.Timer;
import java.util.TimerTask;

public class MainThread {
    public void main(final Game game) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                game.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        game.core.invalidate();
                    }
                });
            }
        };

        timer.schedule(task, 0,16);

    }





}
