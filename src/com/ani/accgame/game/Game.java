package com.ani.accgame.game;

import android.content.res.Resources;
import android.graphics.Canvas;
import com.ani.accgame.game.map.GameMap;
import com.ani.accgame.game.map.GameMapFactory;
import com.ani.accgame.game.object.Player;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.ContactListener;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.ContactPoint;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Game {

    public static final float WORLD_UPDATE_TIME = 1.0f / 60.0f;

    private static final int WORLD_STEP_ITERATIONS = 10;

    private static final Vec2 WORLD_GRAVITY = new Vec2(0.0f, 0.0f);

    private final GameListener listener;

    private World physicalWorld;

    private GameMap gameMap;

    public Game(GameListener listener) {
        this.listener = listener;
    }

    public void init(int levelButtonId, Resources resources) {
        AABB worldAABB = new AABB();
        worldAABB.lowerBound.set(-1000.0f, -1000.0f);
        worldAABB.upperBound.set(1200.0f, 1200.0f);

        physicalWorld = new World(worldAABB, WORLD_GRAVITY, true);
        physicalWorld.setListener(new PhysicalCollisionListener());

        gameMap = GameMapFactory.createMap(levelButtonId);
        gameMap.init(physicalWorld, resources);
    }

    public void updateWorld() {
        physicalWorld.step(WORLD_UPDATE_TIME, WORLD_STEP_ITERATIONS);
    }

    public void draw(Canvas canvas) {
        gameMap.draw(canvas);
    }

    public Player getPlayer() {
        return gameMap.getPlayer();
    }

    private class PhysicalCollisionListener implements ContactListener {
        @Override
        public void add(ContactPoint contactPoint) {

            Set<Body> collidedBodies = new HashSet<>(Arrays.asList(
                    contactPoint.shape1.getBody(),
                    contactPoint.shape2.getBody()
            ));
            Body playerBody = gameMap.getPlayer().getPhysicalBody();

            if (!collidedBodies.contains(playerBody)) {
                return;
            }

            Body goalBody = gameMap.getGoal().getPhysicalBody();

            if (collidedBodies.contains(goalBody)) {
                listener.onSuccess();

            } else {
                listener.onFailure();
            }
        }

        @Override
        public void persist(ContactPoint arg0) {
        }

        @Override
        public void remove(ContactPoint arg0) {
        }
    }
}
