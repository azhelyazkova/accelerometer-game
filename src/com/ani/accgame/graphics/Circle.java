package com.ani.accgame.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public class Circle {

    private final PointF center;

    private final float radius;

    public Circle(PointF center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle(float centerX, float centerY, float radius) {
        this(new PointF(centerX, centerY), radius);
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(center.x, center.y, radius, paint);
    }

    public PointF getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }
}
