package com.ani.accgame.game.map.impl;

import android.graphics.PointF;
import android.graphics.RectF;
import com.ani.accgame.game.map.GameMap;
import com.ani.accgame.game.object.GameObject;
import com.ani.accgame.game.object.GameObjectFactory;
import com.ani.accgame.game.object.Player;
import com.ani.accgame.graphics.Circle;
import com.ani.accgame.graphics.Color;

import java.util.Arrays;

public class Level1Map extends GameMap {

    private final Player player = GameObjectFactory.createPlayer(new PointF(400, 250));

    private final GameObject goal = GameObjectFactory.createGoal(new Circle(400, 1000, 50));

    private final Iterable<GameObject> objects = Arrays.asList(
            player, goal,
            GameObjectFactory.createWall(new RectF(10, 400, 600, 430))
    );

    @Override
    public GameObject getGoal() {
        return goal;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Iterable<GameObject> getObjects() {
        return objects;
    }

    @Override
    protected int getBackgroundColor() {
        return Color.GRASS_GREEN;
    }

}
