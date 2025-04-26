package com.example.a5c;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTopStories;
    private ImageButton btnLeftArrow, btnRightArrow;
    private View mainContent;
    private View fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContent = findViewById(R.id.mainContent);
        fragmentContainer = findViewById(R.id.fragmentContainer);
        rvTopStories = findViewById(R.id.rvTopStories);
        btnLeftArrow = findViewById(R.id.btnLeftArrow);
        btnRightArrow = findViewById(R.id.btnRightArrow);

        LinearLayoutManager topStoriesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvTopStories.setLayoutManager(topStoriesLayoutManager);
        NewsAdapter topStoriesAdapter = new NewsAdapter(NewsData.getTopStories(), this::showNewsDetails, false);
        rvTopStories.setAdapter(topStoriesAdapter);

        btnLeftArrow.setOnClickListener(v -> rvTopStories.smoothScrollBy(-200, 0));
        btnRightArrow.setOnClickListener(v -> rvTopStories.smoothScrollBy(200, 0));

        RecyclerView rvNews = findViewById(R.id.rvNews);
        GridLayoutManager newsLayoutManager = new GridLayoutManager(this, 2);
        rvNews.setLayoutManager(newsLayoutManager);
        NewsAdapter newsAdapter = new NewsAdapter(NewsData.getNewsList(), this::showNewsDetails, true);
        rvNews.setAdapter(newsAdapter);
    }

    private void showNewsDetails(News news) {
        NewsDetailsFragment fragment = NewsDetailsFragment.newInstance(news);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();

        fragmentContainer.setVisibility(View.VISIBLE);
        mainContent.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (fragmentContainer.getVisibility() == View.VISIBLE) {
            getSupportFragmentManager().popBackStack();
            fragmentContainer.setVisibility(View.GONE);
            mainContent.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }
}