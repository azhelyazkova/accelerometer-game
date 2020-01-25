package com.ani.accgame.game.object;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Range;
import com.ani.accgame.R;
import com.ani.accgame.game.object.impl.Goal;
import com.ani.accgame.game.object.impl.Monster;
import com.ani.accgame.game.object.impl.Wall;
import com.ani.accgame.graphics.Circle;

public class GameObjectFactory {

    public static Player createPlayer(PointF initialPosition) {
        return new Player(initialPosition, R.raw.unit2);
    }

    public static GameObject createWall(RectF rectangle) {
        return new Wall(rectangle, R.drawable.obstacle2);
    }

    public static GameObject createGoal(Circle circle) {
        return new Goal(circle);
    }

    public static GameObject createMonster(
            PointF initialPosition,
            float speed,
            Range<Float> patrolRange) {

        return new Monster(initialPosition, speed, patrolRange, R.raw.unit4);
    }
}
