package com.geometric.wars;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.geometric.mapgenerators.CornerMapPlayerPlacer;
import com.geometric.mapgenerators.DefaultMapGenerator;
import com.geometric.mapgenerators.GameMap;

public class GameSettingsActivity extends Activity {

    private Button playButton;
    private Button generateMap;
    private ImageView previewImage;
    private Button mapSettings;

    SeekBar widthSlider;
    SeekBar heightSlider;
    SeekBar wallThresholdSlider;

    private int width = 10;
    private int height = 10;
    private float wallThreshold = .5f;

    private DefaultMapGenerator mapGenerator = new DefaultMapGenerator(2, wallThreshold);
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

        mapSettings = findViewById(R.id.map_settings);
        mapSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettingsPopup();
            }
        });


    }

    private void generateMap() {
        map = mapGenerator.generate(width, height);
        playerPlacer.place(map);

        updatePreview();
    }

    private void updatePreview() {
        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(map.getWidth(), map.getHeight(), conf); // this creates a MUTABLE bitmap

        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.get(i, j) == '#')
                    bmp.setPixel(j, i, Color.argb(255, 0, 255, 0));
                if (map.get(i, j) == 'P')
                    bmp.setPixel(j, i, Color.argb(255, 255, 0, 0));
                if (map.get(i, j) == 'B')
                    bmp.setPixel(j, i, Color.argb(255, 0, 0, 255));
                if (map.get(i, j) == '.')
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

    private void openSettingsPopup() {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.settings_popup, null);

        final int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        popupWindow.showAtLocation(playButton, Gravity.CENTER, 0, 0);


        widthSlider = popupView.findViewById(R.id.width_slider);
        heightSlider = popupView.findViewById(R.id.height_slider);
        wallThresholdSlider = popupView.findViewById(R.id.wall_threshold);

        Button ok = popupView.findViewById(R.id.ok_button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateThings();
                popupWindow.dismiss();
            }
        });

    }

    private void updateThings() {
        width = widthSlider.getProgress() + 5;
        height = heightSlider.getProgress() + 5;
        wallThreshold = wallThresholdSlider.getProgress() / 100;
    }
}
