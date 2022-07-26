package com.codepath.examples.snakegame;


import android.os.Bundle;

public class SnakeGameActivity extends com.codepath.simplegame.GameActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

    setTheme(R.style.Theme_SnakeGame);

            switchFullscreen();
            setContentView(new SnakeGamePanel(this));
          }
}