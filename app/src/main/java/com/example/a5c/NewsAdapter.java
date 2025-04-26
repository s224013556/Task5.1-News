package com.example.a5c;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> newsList;
    private OnNewsClickListener listener;
    private boolean isNewsSection;

    public interface OnNewsClickListener {
        void onNewsClick(News news);
    }

    public NewsAdapter(List<News> newsList, OnNewsClickListener listener, boolean isNewsSection) {
        this.newsList = newsList;
        this.listener = listener;
        this.isNewsSection = isNewsSection;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = isNewsSection ? R.layout.item_news : R.layout.item_top_stories;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        if (isNewsSection) {
            holder.tvChannelName.setText(news.getChannelName());
            holder.tvDescription.setText(news.getDescription());
        }

        Glide.with(holder.itemView.getContext())
                .load(news.getImageResId()) // changed here
                .into(holder.ivImage);

        holder.cardView.setOnClickListener(v -> listener.onNewsClick(news));
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvChannelName, tvDescription;
        CardView cardView;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            cardView = itemView.findViewById(R.id.cardView);
            if (itemView.findViewById(R.id.tvChannelName) != null) {
                tvChannelName = itemView.findViewById(R.id.tvChannelName);
                tvDescription = itemView.findViewById(R.id.tvDescription);
            }
        }
    }
}