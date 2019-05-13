package com.geometric.wars;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.geometric.mapgenerators.CornerMapPlayerPlacer;
import com.geometric.mapgenerators.DefaultMapGenerator;
import com.geometric.mapgenerators.GameMap;

public class GameSettingsActivity extends Activity {

    private Button playButton;
    private Button generateMap;
    private ImageView previewImage;

    private DefaultMapGenerator mapGenerator = new DefaultMapGenerator(2, .30f);
    private CornerMapPlayerPlacer playerPlacer = new CornerMapPlayerPlacer(3, 3);
    private GameMap map;

    @Override
    protected void onCreate(Bundle savedStateInstance) {
        super.onCreate(savedStateInstance);
        setContentView(R.layout.game_settings_activity);
        previewImage = findViewById(R.id.preview_image);
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
        map = mapGenerator.generate(10, 10);
        playerPlacer.place(map);

        updatePreview();
    }

    private void updatePreview() {
        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(map.getWidth(), map.getHeight(), conf); // this creates a MUTABLE bitmap

        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.get(j, i) == '#')
                    bmp.setPixel(j, i, Color.argb(255, 0, 255, 0));
                if (map.get(j, i) == 'P')
                    bmp.setPixel(j, i, Color.argb(255, 255, 0, 0));
                if (map.get(j, i) == 'B')
                    bmp.setPixel(j, i, Color.argb(255, 0, 0, 255));
                if (map.get(j, i) == '.')
                    bmp.setPixel(j, i, Color.argb(255, 0, 0, 0));
            }
        }

        BitmapDrawable bd = new BitmapDrawable(this.getResources(), bmp);
        bd.setFilterBitmap(false);

        previewImage.setImageDrawable(bd);



    }

    private void openGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("MAP", map);
        startActivity(intent);
    }
}
