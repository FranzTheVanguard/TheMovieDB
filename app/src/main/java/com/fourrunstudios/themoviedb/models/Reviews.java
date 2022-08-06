package com.fourrunstudios.themoviedb.models;

import java.util.List;

public class Reviews {
    private int page;
    private List<ReviewResults> results;

    public Reviews(int page, List<ReviewResults> results) {
        this.page = page;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public List<ReviewResults> getResults() {
        return results;
    }

    public int getCount() {
        return results.size();
    }
}
