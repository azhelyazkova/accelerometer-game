package com.ani.accgame.game.object.impl;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import com.ani.accgame.game.object.GameObject;
import com.ani.accgame.graphics.Circle;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

public class Goal implements GameObject {
    private final Circle circle;

    private Body physicalBody;

    public Goal(Circle circle) {
        this.circle = circle;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        circle.draw(canvas, paint);
    }

    @Override
    public void init(World physicalWorld, Resources resources) {

        CircleDef shape = new CircleDef();
        shape.density = 0.1f;
        shape.friction = 0.0f;
        shape.radius = circle.getRadius();

        PointF centerP = circle.getCenter();
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(centerP.x, centerP.y);
        physicalBody = physicalWorld.createStaticBody(bodyDef);

        physicalBody.createShape(shape);
        physicalBody.setMassFromShapes();
    }

    @Override
    public Body getPhysicalBody() {
        return physicalBody;
    }
}
