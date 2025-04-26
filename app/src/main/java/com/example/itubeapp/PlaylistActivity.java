package com.example.itubeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PlaylistActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView emptyView;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        recyclerView = findViewById(R.id.playlist_recycler_view);
        emptyView = findViewById(R.id.empty_view);
        dbHelper = new DatabaseHelper(this);

        List<PlaylistItem> playlist = dbHelper.getAllPlaylistItems();
        if (playlist.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            PlaylistAdapter adapter = new PlaylistAdapter(playlist);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }
}