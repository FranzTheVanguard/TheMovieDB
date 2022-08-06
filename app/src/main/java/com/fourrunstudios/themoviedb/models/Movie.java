package com.fourrunstudios.themoviedb.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Movie {
    private String title;
    private String release_date;
    private String poster_path;
    private int year = 0;
    private int month = 0;
    private int date = 0;
    private int id;

    private Calendar calendar;

    public Movie(String title, String release_date, String poster_path, int id) {
        this.title = title;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.id = id;
    }

    public String getDate() {
        calendar = Calendar.getInstance();
        if(release_date!=null && release_date.length()>=10){
            year = Integer.parseInt(release_date.substring(0, 4));
            month = Integer.parseInt(release_date.substring(5, 7));
            date = Integer.parseInt(release_date.substring(8, 10));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LLL dd, yyyy");
        calendar.set(year, month, date);
        return simpleDateFormat.format(calendar.getTime());
    }

    public String getMovieTitle() {
        return title;
    }
    public String getPosterPath(){
        return poster_path;
    }
    public int getId() {
        return id;
    }
}
