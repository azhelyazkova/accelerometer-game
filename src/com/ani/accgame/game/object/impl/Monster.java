package com.ani.accgame.game.object.impl;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.Log;
import android.util.Range;
import android.util.SizeF;
import com.ani.accgame.game.object.GameObject;
import com.ani.accgame.graphics.Animation;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import java.util.Random;

public class Monster implements GameObject {

    private final Animation animation;

    private final PointF initialPosition;

    private final float speed;

    private final Range<Float> patrolRange;

    private Direction moveDirection;

    private Body physicalBody;

    public Monster(
            PointF initialPosition,
            float speed,
            Range<Float> patrolRange,
            int animationId) {

        this.initialPosition = initialPosition;
        this.speed = speed;
        this.patrolRange = patrolRange;

        animation = new Animation(animationId);

        Direction[] directionTypes = Direction.values();
        moveDirection = directionTypes[new Random().nextInt(directionTypes.length)];
    }

    @Override
    public void draw(Canvas canvas) {
        try {
            checkPatrol();

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
        shape.density = 0.3f;
        shape.friction = 0.0f;
        shape.radius = (Math.min(animationSize.getWidth(), animationSize.getHeight()) / 2) * 0.9f;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(initialPosition.x, initialPosition.y);

        physicalBody = physicalWorld.createDynamicBody(bodyDef);
        physicalBody.createShape(shape);
        physicalBody.setMassFromShapes();

        setPatrol();
    }

    @Override
    public Body getPhysicalBody() {
        return physicalBody;
    }

    private void setPatrol() {

        switch (moveDirection) {
        case LEFT:
            physicalBody.setLinearVelocity(new Vec2(-speed, 0));
            break;
        case RIGHT:
            physicalBody.setLinearVelocity(new Vec2(speed, 0));
            break;
        default:
            throw new RuntimeException("Unsupported Direction type: " + moveDirection);
        }
    }

    private void checkPatrol() {
        Vec2 position = physicalBody.getPosition();
        switch (moveDirection) {
        case LEFT:
            if (position.x < patrolRange.getLower()) {
                moveDirection = Direction.RIGHT;
                setPatrol();
            }
            break;
        case RIGHT:
            if (position.x > patrolRange.getUpper()) {
                moveDirection = Direction.LEFT;
                setPatrol();
            }
            break;
        default:
            throw new RuntimeException("Unsupported Direction type: " + moveDirection);
        }
    }

    private enum Direction {
        LEFT,
        RIGHT
    }

}
