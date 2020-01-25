package com.ani.accgame.game.map;

import android.content.res.Resources;
import android.graphics.Canvas;
import com.ani.accgame.game.object.GameObject;
import com.ani.accgame.game.object.Player;
import org.jbox2d.dynamics.World;

public abstract class GameMap {

    public void init(World physicalWorld, Resources resources) {
        for (GameObject object : getObjects()) {
            object.init(physicalWorld, resources);
        }
    }

    public void draw(Canvas canvas) {

        // BackGround
        canvas.drawColor(getBackgroundColor());

        for (GameObject object : getObjects()) {
            object.draw(canvas);
        }
    }

    public abstract Player getPlayer();

    public abstract GameObject getGoal();

    protected abstract Iterable<GameObject> getObjects();

    protected abstract int getBackgroundColor();

}
