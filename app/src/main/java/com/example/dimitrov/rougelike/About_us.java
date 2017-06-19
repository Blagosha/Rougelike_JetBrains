package com.example.dimitrov.rougelike;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static com.example.dimitrov.rougelike.Application.tagBlagoi;

public class About_us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tagBlagoi, "About_us_activity onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tagBlagoi, "About_us_activity onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tagBlagoi, "About_us_activity onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(tagBlagoi, "About_us_activity onDestroy");
    }
}
