package com.example.dimitrov.rougelike.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dimitrov.rougelike.R;

public class Application extends AppCompatActivity {
    public static final String tagBlagoi = "Blagoi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start__page);

        //Setting button actions
        Button start_game_button = (Button) findViewById(R.id.start_game_btn);
        final View.OnClickListener start_game_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Application.this, Game.class);
                startActivity(intent);
            }
        };
        start_game_button.setOnClickListener(start_game_listener);

        Button how_to_play_button = (Button) findViewById(R.id.how_to_play_btn);
        final View.OnClickListener how_to_play_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Application.this, How_to_play.class);
                startActivity(intent);
            }
        };
        how_to_play_button.setOnClickListener(how_to_play_listener);

        Button about_us_button = (Button) findViewById(R.id.about_us_btn);
        final View.OnClickListener about_us_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Application.this, About_us.class);
                startActivity(intent);
            }
        };
        about_us_button.setOnClickListener(about_us_listener);
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
