package com.fourrunstudios.themoviedb.models;

import android.util.Log;

import java.util.List;

public class MovieDetails {
    private String title;
    private List<Genre> genres;
    private String overview;
    private String poster_path;
    private String tagline;
    private int id;
    private float vote_average;
    private int vote_count;

    public MovieDetails(String title, List<Genre> genres, String overview, String poster_path, String tagline, int id, float vote_average, int vote_count) {
        this.title = title;
        this.genres = genres;
        this.overview = overview;
        this.poster_path = poster_path;
        this.tagline = tagline;
        this.id = id;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public String getTitle() {
        return title;
    }

    public String getVote_average() {
        return "Score: " + vote_average;
    }

    public String getVote_count() {
        return vote_count + " Votes";
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }
    public String getGenre(){
        String temp = "";
        for (Genre genre : genres) {
            temp = temp.concat(genre.getName()+", ");
        }
        temp = temp.substring(0, temp.length()-2);
        ;
        return temp;
    }
}
