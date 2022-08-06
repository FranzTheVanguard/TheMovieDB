package com.fourrunstudios.themoviedb.models;

import java.util.List;

public class GenreObject {
    private List<Genre> genres;

    public GenreObject(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Genre> getGenres(){
        return genres;
    }
}
