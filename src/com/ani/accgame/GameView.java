package com.ani.accgame;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.View;
import com.ani.accgame.game.Game;
import com.ani.accgame.game.object.Player;

class GameView extends View {

    private final GameActivity gameActivity;

    public GameView(GameActivity gameActivity) {
        super(gameActivity);
        this.gameActivity = gameActivity;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Game game = gameActivity.getGame();
        Player player = game.getPlayer();
        PointF playerPosition = player.getPosition();
        canvas.translate(
                this.getWidth() / 2.0f - playerPosition.x,
                this.getHeight() / 2.0f - playerPosition.y);

        game.draw(canvas);

    }
}
