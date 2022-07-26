package com.codepath.simplegame.threads;

import com.codepath.simplegame.AbstractGamePanel;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class FrameGameLoopThread extends BaseGameThread {

    private final static int MAX_FPS = 50;

    private final static int MAX_FRAME_SKIPS = 5;

    private final static int FRAME_PERIOD = 1000 / MAX_FPS;

    public FrameGameLoopThread(SurfaceHolder surfaceHolder, AbstractGamePanel gamePanel) {
        super(surfaceHolder, gamePanel);
    }

    @Override
    public void run() {
        Canvas canvas;
        long beginTime;
        long timeDiff;
        int sleepTime;
        int framesSkippked;

        sleepTime = 0;

        this.gamePanel.onStart();

        while (running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    beginTime = System.currentTimeMillis();
                    framesSkippked = 0;

                    this.gamePanel.onTimer();

                    if (canvas != null) {
                        this.gamePanel.render(canvas);
                    }

                    timeDiff = System.currentTimeMillis() - beginTime;

                    sleepTime = (int) (FRAME_PERIOD - timeDiff);

                    if (sleepTime > 0) {
                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException e) {

                        }
                    }

                    while (sleepTime < 0 && framesSkippked < MAX_FRAME_SKIPS) {

                        this.gamePanel.onTimer();
                        sleepTime += FRAME_PERIOD;
                        framesSkippked++;
                    }
                }
            } finally {

                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
