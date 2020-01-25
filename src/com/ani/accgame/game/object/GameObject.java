package com.ani.accgame.game.object;

import android.content.res.Resources;
import android.graphics.Canvas;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

public interface GameObject {
    void draw(Canvas canvas);

    void init(World physicalWorld, Resources resources);

    Body getPhysicalBody();
}
