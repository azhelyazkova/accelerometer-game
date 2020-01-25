package com.ani.accgame.game.map;

import com.ani.accgame.R;
import com.ani.accgame.game.map.impl.Level1Map;
import com.ani.accgame.game.map.impl.Level2Map;
import com.ani.accgame.game.map.impl.Level3Map;
import com.ani.accgame.game.map.impl.Level4Map;

public class GameMapFactory {

    public static GameMap createMap(int levelButtonId) {
        switch (levelButtonId) {
        case R.id.levelButton1: {
            return new Level1Map();
        }
        case R.id.levelButton2: {
            return new Level2Map();
        }
        case R.id.levelButton3: {
            return new Level3Map();
        }
        case R.id.levelButton4: {
            return new Level4Map();
        }
        default:
            throw new RuntimeException("Invalid button level ID: " + levelButtonId);
        }
    }

}
