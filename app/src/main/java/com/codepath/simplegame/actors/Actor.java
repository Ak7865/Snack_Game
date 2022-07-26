package com.codepath.simplegame.actors;

import  android.graphics.Canvas;
import  android.graphics.Paint;
import android.view.MotionEvent;

public abstract class Actor {

    private Paint paint;
    private boolean enabled;

    public Actor() {

        this.enabled = true;

        this.paint = new Paint();
        stylePaint(paint);
    }

    public Paint getPaint() {

        return paint;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean state) {
        this.enabled = state;
    }

    public abstract  void stylePaint(Paint p);

    public abstract void draw(Canvas canvas);

    public void handleTouchInput(MotionEvent event) {
    }
}
