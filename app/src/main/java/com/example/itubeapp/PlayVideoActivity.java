package com.example.itubeapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class PlayVideoActivity extends AppCompatActivity {
    private static final String TAG = "PlayVideoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        String videoUrl = getIntent().getStringExtra("video_url");
        String videoId = extractVideoId(videoUrl);

        if (videoId == null) {
            Toast.makeText(this, "Invalid YouTube URL", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0);
            }

            @Override
            public void onError(YouTubePlayer youTubePlayer, com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants.PlayerError error) {
                Log.e(TAG, "YouTube Player Error: " + error.toString());
                Toast.makeText(PlayVideoActivity.this, "Error playing video", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private String extractVideoId(String youtubeUrl) {
        if (youtubeUrl == null || youtubeUrl.trim().isEmpty()) {
            return null;
        }

        String videoId = null;
        String[] patterns = {
                "v=([^&]*)",
                "youtu.be/([^?&]*)",
                "embed/([^?&]*)",
                "v/([^?&]*)"
        };

        for (String pattern : patterns) {
            java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(pattern).matcher(youtubeUrl);
            if (matcher.find()) {
                videoId = matcher.group(1);
                break;
            }
        }

        return videoId;
    }
}