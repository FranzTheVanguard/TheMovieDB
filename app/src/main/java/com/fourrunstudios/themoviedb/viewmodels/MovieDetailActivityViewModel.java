package com.fourrunstudios.themoviedb.viewmodels;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fourrunstudios.themoviedb.models.DetailsRepo;
import com.fourrunstudios.themoviedb.models.MovieDetails;
import com.fourrunstudios.themoviedb.models.ReviewRepo;
import com.fourrunstudios.themoviedb.models.ReviewResults;

import java.util.List;

public class MovieDetailActivityViewModel extends ViewModel {
    private int movie_id;
    private DetailsRepo detailsRepo;
    private ReviewRepo reviewRepo;
    private int currentPage = 1;

    public void init(){
        currentPage = 1;
        detailsRepo = DetailsRepo.getInstance();
        reviewRepo = ReviewRepo.getInstance();

        detailsRepo.fetchDetails(movie_id);
        detailsRepo.fetchTrailer(movie_id);
        reviewRepo.fetchReview(movie_id);

        detailsRepo.getDetails();
        reviewRepo.getReview();
    }

    public void setMovieId(Intent intent){
        movie_id = intent.getIntExtra("movie_id", 0);
    }
    public LiveData<MovieDetails> getDetails(){
        return detailsRepo.getDetails();
    }
    public LiveData<List<ReviewResults>> getReview(){
        return reviewRepo.getReview();
    }
    public LiveData<Boolean> getDetailFinished(){
        return detailsRepo.getDetailFinished();
    }
    public LiveData<Boolean> getReviewFinished() {
        return reviewRepo.getReviewFinished();
    }
    public void fetchReviewNextPage(){
        currentPage++;
        reviewRepo.fetchReview(movie_id, currentPage);
    }
    public int getItemPosition() {
        return (currentPage-1)*20;
    }
    public String getKey(){
        return detailsRepo.getTrailerKey();
    }


}
