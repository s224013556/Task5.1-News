package com.example.itubeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    EditText urlInput;
    Button playButton, addToPlaylistButton, viewPlaylistButton;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        urlInput = findViewById(R.id.youtube_url_input);
        playButton = findViewById(R.id.play_button);
        addToPlaylistButton = findViewById(R.id.add_to_playlist_button);
        viewPlaylistButton = findViewById(R.id.my_playlist_button);
        dbHelper = new DatabaseHelper(this);

        playButton.setOnClickListener(v -> {
            String url = urlInput.getText().toString().trim();
            if (url.isEmpty()) {
                Toast.makeText(this, "Please enter a YouTube URL", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(HomeActivity.this, PlayVideoActivity.class);
            intent.putExtra("video_url", url);
            startActivity(intent);
        });

        addToPlaylistButton.setOnClickListener(v -> {
            String url = urlInput.getText().toString().trim();
            if (url.isEmpty()) {
                Toast.makeText(this, "Please enter a YouTube URL", Toast.LENGTH_SHORT).show();
                return;
            }
            dbHelper.insertPlaylistItem(url);
            Toast.makeText(this, "Video added to playlist", Toast.LENGTH_SHORT).show();
        });

        viewPlaylistButton.setOnClickListener(v -> {
            startActivity(new Intent(this, PlaylistActivity.class));
        });
    }
}