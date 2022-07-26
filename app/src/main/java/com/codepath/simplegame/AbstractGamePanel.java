package com.codepath.simplegame;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.codepath.simplegame.threads.BaseGameThread;
import com.codepath.simplegame.threads.TimerGameThread;

public abstract class AbstractGamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private BaseGameThread gameThread;
    private Paint paint;
    private  int panelColor = Color.GRAY;

    public AbstractGamePanel(Context context) {

        this(TimerGameThread.class, context);
    }

    public AbstractGamePanel(Class<? extends BaseGameThread> loopClass, Context context) {
        super(context);

        getHolder().addCallback(this);

        setupGameloop(loopClass);

        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        setWillNotDraw(true);

        panelColor = Color.GRAY;
        paint = new Paint();
    }

    public  void render(Canvas canvas) {

        canvas.drawColor(panelColor);

        this.redrawCanvas(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        startGameLoop();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void setPanelColor(int color) {

        panelColor = color;
    }

    public abstract void onStart();

    public abstract void onTimer();

    public abstract void redrawCanvas(Canvas canvas);

    public abstract boolean onKeyDown(int keyCode, KeyEvent event);

    public abstract boolean onTouchEvent(MotionEvent event);

    protected Paint getPaint() {

        return paint;
    }

    protected BaseGameThread getGameLoop() {

        return gameThread;
    }

    protected void startGameLoop() {
        gameThread.setRunning(true);
        gameThread.start();
    }

    protected void stopGameLoop() {

        gameThread.setRunning(false);
    }

    protected void  setupGameloop(Class<? extends BaseGameThread> loopClass) {
        try {
            gameThread = (BaseGameThread) loopClass.getConstructors()[0].newInstance(getHolder(), this);
        } catch (Exception e) {

        }
    }
}
