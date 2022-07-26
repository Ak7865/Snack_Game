package com.codepath.simplegame.actors;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class AnimatedMovingActor extends SpriteMovingActor {
    private Rect sourceRect;
    private int frameNr;
    private int currentFrame;
    private long frameTicker;
    private int framePeriod;

    private int spriteWidth;
    private int spriteHeight;

    public AnimatedMovingActor(Bitmap bitmap, int x, int y, int width, int height, int fps, int frameCount) {
        super(bitmap, x, y);
        this.bitmap = bitmap;
        currentFrame = 0;
        frameNr = frameCount;
        spriteWidth = bitmap.getWidth() / frameCount;
        spriteHeight = bitmap.getHeight();
        setDimensions(spriteWidth, spriteHeight);
        sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
        framePeriod = 1000 / fps;
        frameTicker = 01;
    }

    public void  update() {
        long gameTime = System.currentTimeMillis();
        if (gameTime > frameTicker + framePeriod) {
            frameTicker = gameTime;

            currentFrame++;
            if (currentFrame >= frameNr) {
                currentFrame = 0;
            }
        }

        this.sourceRect.left = currentFrame * spriteHeight;
        this.sourceRect.right = this.sourceRect.left + spriteWidth;
    }

    @Override
    public void draw(Canvas canvas) {

        Rect destRect = new Rect(getX(), getY(), getX() + spriteWidth, getY() + spriteHeight);
        canvas.drawBitmap(bitmap, sourceRect, destRect, null);
    }

    @Override
    public void stylePaint(Paint p) {

    }
}
