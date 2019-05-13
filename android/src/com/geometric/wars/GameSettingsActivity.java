package com.geometric.wars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.geometric.mapgenerators.CornerMapPlayerPlacer;
import com.geometric.mapgenerators.DefaultMapGenerator;
import com.geometric.mapgenerators.GameMap;

public class GameSettingsActivity extends Activity {

    private Button playButton;
    private Button generateMap;

    private DefaultMapGenerator mapGenerator = new DefaultMapGenerator(2, .30f);
    private CornerMapPlayerPlacer playerPlacer = new CornerMapPlayerPlacer(1, 1);
    private GameMap map;

    @Override
    protected void onCreate(Bundle savedStateInstance) {
        super.onCreate(savedStateInstance);
        setContentView(R.layout.game_settings_activity);

        generateMap();

        playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGameActivity();
            }
        });

        generateMap = findViewById(R.id.generate_map_button);
        generateMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateMap();
            }
        });
    }

    private void generateMap() {
        map = mapGenerator.generate(20, 20);
        playerPlacer.place(map);
    }

    private void openGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("MAP", map);
        startActivity(intent);
    }
}
