package com.codepath.simplegame.threads;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.codepath.simplegame.AbstractGamePanel;

public class TimerGameThread extends BaseGameThread {
    private int tickInterval;

    public TimerGameThread(SurfaceHolder surfaceHolder, AbstractGamePanel gamePanel) {
        super(surfaceHolder, gamePanel);
        tickInterval = 200;
    }

    public void setTickInterval(int millis) {
        this.tickInterval = millis;

    }

    @Override
    public void run() {
           Canvas canvas;

           this.gamePanel.onStart();
           while (running) {
               canvas = null;

               try {
                   canvas = this.surfaceHolder.lockCanvas();
                   synchronized (surfaceHolder) {

                       this.gamePanel.onTimer();

                       this.gamePanel.render(canvas);
                   }
               } finally {

                   if (canvas != null) {
                       surfaceHolder.unlockCanvasAndPost(canvas);
                   }
               }

               if (tickInterval > 0) {
                   try {
                       Thread.sleep(tickInterval);
                   } catch (InterruptedException e) {

                   }
               }
           }
       }
}
