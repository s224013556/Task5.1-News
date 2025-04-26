package com.example.a5c;

import java.util.ArrayList;
import java.util.List;

public class NewsData {

    public static List<News> getTopStories() {
        List<News> topStories = new ArrayList<>();
        topStories.add(new News("Global Climate Summit 2025", R.drawable.img1,
                "World leaders gather in Geneva to discuss urgent steps needed to combat climate change.",
                "TV9"));
        topStories.add(new News("Tech Giants Merge", R.drawable.img2,
                "Two of the world’s biggest tech firms have joined forces in a $200 billion deal to accelerate AI innovation.",
                "BBC"));
        topStories.add(new News("Historic Spacewalk", R.drawable.img3,
                "Astronauts successfully replace solar panels on the ISS during a record-breaking 6-hour spacewalk.",
                "ANC"));
        topStories.add(new News("Economic Outlook 2025", R.drawable.img4,
                "Experts forecast a robust global recovery driven by green technologies and digital transformation.",
                "News for you"));
        return topStories;
    }

    public static List<News> getNewsList() {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News("Tech University Launch", R.drawable.img1,
                "A new high-tech university has opened in Melbourne, focusing on AI, robotics, and blockchain.",
                "TV9"));
        newsList.add(new News("Wildlife Comeback", R.drawable.img2,
                "Conservation efforts pay off as the tiger population in India increases by 25%.",
                "BBC"));
        newsList.add(new News("EVs Break Records", R.drawable.img3,
                "Electric vehicles outsell petrol cars in Europe for the first time in history.",
                "ANC"));
        newsList.add(new News("Cancer Research Breakthrough", R.drawable.img4,
                "Researchers have developed a promising treatment with a 90% success rate in early cancer trials.",
                "News for you"));
        return newsList;
    }

    public static List<News> getRelatedStories() {
        List<News> relatedStories = new ArrayList<>();
        relatedStories.add(new News("AI's Impact on Jobs", R.drawable.img5,
                "A recent study shows AI automation is rapidly transforming traditional job roles.",
                "Dailt News"));
        relatedStories.add(new News("Green Architecture Trend", R.drawable.img2,
                "Urban cities are embracing eco-friendly building designs to tackle rising temperatures.",
                "Channel News"));
        return relatedStories;
    }
}
