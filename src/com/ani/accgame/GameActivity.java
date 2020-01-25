package com.ani.accgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import com.ani.accgame.game.Game;
import com.ani.accgame.game.GameListener;
import com.ani.accgame.game.object.Player;

public class GameActivity extends Activity implements GameListener {

    static final String LEVEL_ID_INTENT = "level";

    private Game game;

    private GameView gameView;

    private Handler handler = new Handler();

    private Integer levelID;

    private Runnable updateTask = new Runnable() {
        public void run() {
            game.updateWorld();
            gameView.postInvalidate();
            handler.postDelayed(updateTask, (long) Game.WORLD_UPDATE_TIME * 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        setContentView(gameView);

        levelID = (Integer) getIntent().getSerializableExtra(LEVEL_ID_INTENT);
        createNewGame();

        handler.post(updateTask);

        subscribeSensorListener();
    }

    @Override
    public void onSuccess() {
        handler.removeCallbacks(updateTask);
        unsubscribeSensorListener();

        new AlertDialog.Builder(this)
                .setTitle(R.string.successTitle)
                .setMessage(R.string.successMessage)
                .setPositiveButton(R.string.successConfirmation,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                .show();
    }

    private void subscribeSensorListener() {
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        assert sensorManager != null;
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Player player = game.getPlayer();
        sensorManager.registerListener(player, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    private void unsubscribeSensorListener() {
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        assert sensorManager != null;
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Player player = game.getPlayer();
        sensorManager.unregisterListener(player, accelerometer);
    }

    @Override
    public void onFailure() {
        unsubscribeSensorListener();

        createNewGame();
        subscribeSensorListener();
    }

    protected Game getGame() {
        return game;
    }

    private void createNewGame() {
        game = new Game(this);
        game.init(levelID, getResources());
    }

}
