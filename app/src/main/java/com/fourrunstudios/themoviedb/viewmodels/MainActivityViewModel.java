package com.fourrunstudios.themoviedb.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.fourrunstudios.themoviedb.models.Genre;
import com.fourrunstudios.themoviedb.models.GenreRepo;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private GenreRepo genreRepo;

    public void init(){
        genreRepo = GenreRepo.getInstance();
        genreRepo.fetchGenre();
    }
    public LiveData<List<Genre>> getGenreLiveData(){
        return genreRepo.getGenres();
    }
    public LiveData<Boolean> getGenreFinished(){
        return genreRepo.getGenreFinished();
    }
}
