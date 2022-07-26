package com.codepath.simplegame.actors;

import android.content.Context;
import android.graphics.Canvas;

import com.codepath.simplegame.AbstractGamePanel;

public abstract class MyGamePanel extends AbstractGamePanel {
    HeroActor player;
         public MyGamePanel(Context context) {
             super(context);
         }

         public void onStart() {
             player = new HeroActor(50, 50);
         }

         public void redrawCanvas(Canvas canvas) {
             player.draw(canvas);
         }

}
