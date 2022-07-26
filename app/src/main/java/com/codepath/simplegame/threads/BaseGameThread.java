package com.codepath.simplegame.threads;

import com.codepath.simplegame.AbstractGamePanel;

import android.view.SurfaceHolder;

public abstract class BaseGameThread extends Thread {

    protected  SurfaceHolder surfaceHolder;
    protected AbstractGamePanel gamePanel;

    protected boolean running;

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return this.running;
    }

    public BaseGameThread(SurfaceHolder surfaceHolder, AbstractGamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public abstract void run();
}
