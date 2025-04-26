package com.example.a5c;

import java.io.Serializable;

public class News implements Serializable {
    private String title;
    private int imageResId;
    private String description;
    private String channelName;

    public News(String title, int imageResId, String description, String channelName) {
        this.title = title;
        this.imageResId = imageResId;
        this.description = description;
        this.channelName = channelName;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }

    public String getChannelName() {
        return channelName;
    }
}
