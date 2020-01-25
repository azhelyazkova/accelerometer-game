package com.ani.accgame.game.map.impl;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Range;
import com.ani.accgame.game.map.GameMap;
import com.ani.accgame.game.object.GameObject;
import com.ani.accgame.game.object.GameObjectFactory;
import com.ani.accgame.game.object.Player;
import com.ani.accgame.graphics.Circle;
import com.ani.accgame.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Level3Map extends GameMap {

    private final Player player = GameObjectFactory.createPlayer(new PointF(400, 250));

    private final GameObject goal = GameObjectFactory.createGoal(new Circle(400, 1000, 50));

    private final Collection<GameObject> objects = new ArrayList<>(Arrays.asList(
            player, goal,
            // boundary wall
            GameObjectFactory.createWall(new RectF(40, 200, 50, 1250)),
            GameObjectFactory.createWall(new RectF(740, 200, 750, 1250)),
            GameObjectFactory.createWall(new RectF(40, 200, 750, 210)),
            GameObjectFactory.createWall(new RectF(40, 1240, 750, 1250)),

            // inner wall
            GameObjectFactory.createWall(new RectF(330, 200, 350, 400)),
            GameObjectFactory.createWall(new RectF(490, 200, 510, 300)),
            GameObjectFactory.createWall(new RectF(330, 400, 670, 420)),
            GameObjectFactory.createWall(new RectF(600, 290, 620, 410)),
            GameObjectFactory.createWall(new RectF(500, 410, 520, 780)),

            //monsters
            GameObjectFactory.createMonster(
                    new PointF(700, 600),
                    20,
                    new Range<>(550f, 700f)),
            GameObjectFactory.createMonster(
                    new PointF(700, 700),
                    30,
                    new Range<>(550f, 700f)),
            GameObjectFactory.createMonster(
                    new PointF(700, 800),
                    40,
                    new Range<>(550f, 700f))

    ));

    public GameObject getGoal() {
        return goal;
    }

    public Player getPlayer() {
        return player;
    }

    public Iterable<GameObject> getObjects() {
        return objects;
    }

    @Override
    protected int getBackgroundColor() {
        return Color.GRASS_GREEN;
    }
}
