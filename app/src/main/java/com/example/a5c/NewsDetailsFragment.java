package com.example.a5c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

public class NewsDetailsFragment extends Fragment {

    private static final String ARG_NEWS = "news";

    public static NewsDetailsFragment newInstance(News news) {
        NewsDetailsFragment fragment = new NewsDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NEWS, news);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_details, container, false);

        News news = (News) getArguments().getSerializable(ARG_NEWS);
        if (news != null) {
            ImageView ivImage = view.findViewById(R.id.ivImage);
            TextView tvChannel = view.findViewById(R.id.tvChannel);
            TextView tvDescription = view.findViewById(R.id.tvDescription);
            RecyclerView rvRelatedStories = view.findViewById(R.id.rvRelatedStories);

            Glide.with(this).load(news.getImageResId()).into(ivImage);
            tvDescription.setText(news.getDescription());

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            rvRelatedStories.setLayoutManager(layoutManager);
            NewsAdapter relatedAdapter = new NewsAdapter(NewsData.getRelatedStories(), this::showRelatedNewsDetails, true);
            rvRelatedStories.setAdapter(relatedAdapter);
        }

        return view;
    }

    private void showRelatedNewsDetails(News news) {
        NewsDetailsFragment fragment = NewsDetailsFragment.newInstance(news);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();

        View fragmentContainer = getActivity().findViewById(R.id.fragmentContainer);
        View mainContent = getActivity().findViewById(R.id.mainContent);
        if (fragmentContainer != null && mainContent != null) {
            fragmentContainer.setVisibility(View.VISIBLE);
            mainContent.setVisibility(View.GONE);
        }
    }
}
