package com.ani.accgame.game.object;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.util.SizeF;
import com.ani.accgame.graphics.Animation;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

public class Player implements GameObject, SensorEventListener {

    private static final int SPEED_SENSITIVITY = 40;

    private final Animation animation;

    private final PointF initialPosition;

    private Body physicalBody;

    public Player(PointF initialPosition, int animationId) {
        this.initialPosition = initialPosition;

        animation = new Animation(animationId);
    }

    @Override
    public void draw(Canvas canvas) {

        try {
            Vec2 position = physicalBody.getPosition();
            animation.draw(canvas, new PointF(position.x, position.y));
        } catch (Exception e) {
            Log.e("Game", "Exception: " + e.toString(), e);
        }
    }

    @Override
    public void init(World physicalWorld, Resources resources) {

        animation.init(resources);
        SizeF animationSize = animation.getSize();

        CircleDef shape = new CircleDef();
        shape.density = 0.1f;
        shape.friction = 0.0f;
        shape.radius = (Math.min(animationSize.getWidth(), animationSize.getHeight()) / 2) * 0.9f;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(initialPosition.x, initialPosition.y);

        physicalBody = physicalWorld.createDynamicBody(bodyDef);
        physicalBody.createShape(shape);
        physicalBody.setMassFromShapes();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float xV = -event.values[0] * SPEED_SENSITIVITY;
        float yV = event.values[1] * SPEED_SENSITIVITY;
        Vec2 v = new Vec2(xV, yV);
        physicalBody.setLinearVelocity(v);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public Body getPhysicalBody() {
        return physicalBody;
    }

    public PointF getPosition() {
        Vec2 position = physicalBody.getPosition();
        return new PointF(position.x, position.y);
    }

}

