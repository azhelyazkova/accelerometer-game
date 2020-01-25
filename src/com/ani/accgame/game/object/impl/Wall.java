package com.ani.accgame.game.object.impl;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import com.ani.accgame.game.object.GameObject;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

public class Wall implements GameObject {
    private final RectF rectangle;

    private final int textureId;

    private Bitmap texture;

    private Body physicalBody;

    public Wall(RectF rectangle, int wallTextureId) {
        this.rectangle = rectangle;
        this.textureId = wallTextureId;
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawBitmap(texture, null, rectangle, null);
    }

    @Override
    public void init(World physicalWorld, Resources resources) {
        texture = BitmapFactory.decodeResource(resources, textureId);

        PolygonDef shape = new PolygonDef();
        shape.density = 0.1f;
        shape.friction = 0.0f;
        shape.setAsBox(rectangle.width() / 2.0f, rectangle.height() / 2.0f);

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(rectangle.centerX(), rectangle.centerY());

        physicalBody = physicalWorld.createStaticBody(bodyDef);
        physicalBody.createShape(shape);
        physicalBody.setMassFromShapes();
    }

    @Override
    public Body getPhysicalBody() {
        return physicalBody;
    }

}
