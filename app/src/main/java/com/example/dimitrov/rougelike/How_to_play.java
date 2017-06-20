package com.example.dimitrov.rougelike;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static com.example.dimitrov.rougelike.Application.tagBlagoi;

public class How_to_play extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tagBlagoi, "How_to_play_activity onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tagBlagoi, "How_to_play_activity onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tagBlagoi, "How_to_play_activity onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(tagBlagoi, "How_to_play_activity onDestroy");
    }
}
