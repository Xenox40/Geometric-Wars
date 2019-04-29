package com.geometric.wars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class AndroidLauncher extends Activity {

	private Button playButton;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);

		playButton = findViewById(R.id.play_button);
		playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				openGameActivity();
			}
		});
	}

	private void openGameActivity() {
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
	}
}
