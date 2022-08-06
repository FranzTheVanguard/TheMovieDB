package com.fourrunstudios.themoviedb.models;

import java.util.List;

public class MovieResults {
    private List<Movie> results;

    public MovieResults(List<Movie> results) {
        this.results = results;
    }

    public List<Movie> getResults() {
        return results;
    }
}
