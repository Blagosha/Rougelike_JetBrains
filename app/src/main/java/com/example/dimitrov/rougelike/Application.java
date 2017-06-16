package com.example.dimitrov.rougelike;

public class Application {
    private boolean running = false;

    private void init() {
    }

    private void render() {

    }

    private void update(long delta) {
    }

    private void run() {
        long lastTime = System.currentTimeMillis();
        long delta;

        while(running) {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            update(delta);
            render();
        }
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.init();
        application.run();
    }
}

