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

public class Level2Map extends GameMap {

    private final Player player = GameObjectFactory.createPlayer(new PointF(400, 250));

    private final GameObject goal = GameObjectFactory.createGoal(new Circle(400, 1000, 50));

    private final Iterable<GameObject> objects = Arrays.asList(
            player, goal,
            //boundary
            GameObjectFactory.createWall(new RectF(40, 200, 50, 1250)),
            GameObjectFactory.createWall(new RectF(740, 200, 750, 1250)),
            GameObjectFactory.createWall(new RectF(40, 200, 750, 210)),
            GameObjectFactory.createWall(new RectF(40, 1240, 750, 1250)),
            //inner
            GameObjectFactory.createWall(new RectF(300, 200, 310, 400)),
            GameObjectFactory.createWall(new RectF(500, 200, 510, 550)),
            GameObjectFactory.createWall(new RectF(200, 400, 310, 410)),
            GameObjectFactory.createWall(new RectF(300, 550, 510, 560)),
            GameObjectFactory.createWall(new RectF(200, 400, 210, 700)),
            GameObjectFactory.createWall(new RectF(500, 600, 700, 610)),
            GameObjectFactory.createWall(new RectF(200, 700, 500, 710)),
            GameObjectFactory.createWall(new RectF(500, 700, 510, 900)),
            GameObjectFactory.createWall(new RectF(700, 600, 710, 1100)),
            GameObjectFactory.createWall(new RectF(500, 1100, 710, 1110)),
            GameObjectFactory.createWall(new RectF(320, 900, 510, 910)),
            GameObjectFactory.createWall(new RectF(320, 900, 330, 1250))
    );

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
