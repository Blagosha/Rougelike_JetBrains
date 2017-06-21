package com.example.dimitrov.rougelike.objects;



public class Chest extends Entity {
    public Chest(int x, int y) {
        super(x, y);
        texture="chest";
    }

    public void open(){
        texture="chestOpen";
    }

}
