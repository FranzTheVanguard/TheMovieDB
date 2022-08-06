package com.fourrunstudios.themoviedb.models;

public class VideoObject {
    private String key;
    private String type;

    public VideoObject(String key, String type) {
        this.key = key;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }
}
