package com.geometric.wars;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.geometric.wars.screens.HowToPlayScreen;
import com.geometric.wars.utils.Values;

public class AndroidLauncher extends Activity {

	private Button playButton;
	private Button customGameButton;
	private Button aboutButton;
	private Button howToPlayButton;
	private Button exitButton;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		initializeButtons();
	}

	private void initializeButtons() {
		playButton = findViewById(R.id.play_button);
		playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				openGameActivity();
			}
		});

		customGameButton = findViewById(R.id.custom_game_button);
		customGameButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				openGameSettingsActivity();
			}
		});

		aboutButton = findViewById(R.id.about_button);
		aboutButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				openAboutActivity();
			}
		});

		howToPlayButton = findViewById(R.id.how_to_play_button);
		howToPlayButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				openHowToPlayButton();
			}
		});

		exitButton = findViewById(R.id.exit_button);
		exitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
				System.exit(0);
			}
		});
	}

	private void openHowToPlayButton() {
		openPopupWindow("How to play?", HowToPlayScreen.strings[0]+" To move simply swipe finger on screen, to shoot tap on screen.");
	}

	private void openAboutActivity() {
		openPopupWindow("About the application", Values.aboutText);

	}

	private void openGameSettingsActivity() {
		Intent intent = new Intent(this, GameSettingsActivity.class);
		startActivity(intent);
	}

	private void openGameActivity() {
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
	}

	private void openPopupWindow(String title, String content) {
		LayoutInflater inflater = (LayoutInflater)
				getSystemService(LAYOUT_INFLATER_SERVICE);
		View popupView = inflater.inflate(R.layout.popup_window, null);

		int width = LinearLayout.LayoutParams.MATCH_PARENT;
		int height = LinearLayout.LayoutParams.MATCH_PARENT;
		final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

		popupWindow.showAtLocation(playButton, Gravity.CENTER, 0, 0);

		popupView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				popupWindow.dismiss();
				return true;
			}
		});

		TextView titleTv = popupView.findViewById(R.id.title);
		TextView contentTv = popupView.findViewById(R.id.content);

		titleTv.setTextColor(Color.parseColor("#000000"));
		contentTv.setTextColor(Color.parseColor("#000000"));
		titleTv.setText(title);
		contentTv.setText(content);
	}
}
