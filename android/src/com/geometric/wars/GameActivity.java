package com.geometric.wars;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

public class GameActivity extends FragmentActivity implements AndroidFragmentApplication.Callbacks {

    private GameFragment gameFragment;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity_layout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        gameFragment = new GameFragment();
        gameFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.game_fragment_container, gameFragment)
                .commit();
    }

    @Override
    public void exit() {
        gameFragment.getGame().dispose();
    }
}
