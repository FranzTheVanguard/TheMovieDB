package com.fourrunstudios.themoviedb.viewmodels;

import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.fourrunstudios.themoviedb.models.Movie;
import com.fourrunstudios.themoviedb.models.MovieRepo;

import java.util.List;

public class MovieListActivityViewModel extends ViewModel {
    private String selectedGenre;
    private int selectedGenreId;
    private MovieRepo movieRepo;
    private int currentPage = 1;
    public void init(){
        currentPage = 1;
        movieRepo = MovieRepo.getInstance();
        movieRepo.fetchMovieByGenre(String.valueOf(selectedGenreId));
    }

    public void setSelectedGenre(Intent intent) {
        selectedGenre = intent.getStringExtra("genre_name");
        selectedGenreId = intent.getIntExtra("genre_id", 0);

    }

    public String getSelectedGenre() {
        return selectedGenre;
    }

    public int getSelectedGenreId() {
        return selectedGenreId;
    }

    public LiveData<List<Movie>> getMovieLiveData(){
        return movieRepo.getMovies();
    }
    public LiveData<Boolean> getMovieFinished(){
        return movieRepo.getMovieFinished();
    }

    public void fetchMovieNextPage(){
        currentPage++;
        movieRepo.fetchMovieByGenre(String.valueOf(selectedGenreId), currentPage);
    }

    public int getItemPosition() {
        return (currentPage-1)*20;
    }
}
