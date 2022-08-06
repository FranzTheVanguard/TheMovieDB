package com.fourrunstudios.themoviedb.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResult {
    @SerializedName("results")
    private List<VideoObject> results;

    public VideoResult(List<VideoObject> results) {
        this.results = results;
    }

    public List<VideoObject> getResults() {
        return results;
    }
}
