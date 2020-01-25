package com.ani.accgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LevelSelectionActivity extends Activity {


    private static final int[] LEVEL_BUTTON_IDs = {
            R.id.levelButton1,
            R.id.levelButton2,
            R.id.levelButton3,
            R.id.levelButton4
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_selector);

        LevelButtonListener levelButtonListener = new LevelButtonListener();
        for (int buttonId : LEVEL_BUTTON_IDs) {
            View button = findViewById(buttonId);
            button.setOnClickListener(levelButtonListener);
        }

    }

    private class LevelButtonListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), GameActivity.class);
            intent.putExtra(GameActivity.LEVEL_ID_INTENT, v.getId());
            startActivity(intent);
        }
    }

}
