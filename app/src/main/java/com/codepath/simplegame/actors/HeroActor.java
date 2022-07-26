package com.codepath.simplegame.actors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class HeroActor extends SimpleMovingActor {
    public HeroActor(int x, int y) {
        super(x, y, 25, 25);

    }

    @Override
    public void stylePaint(Paint paint) {
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(getRect(), getPaint());
    }
}
