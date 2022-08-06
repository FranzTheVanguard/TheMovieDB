package com.fourrunstudios.themoviedb.models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.fourrunstudios.themoviedb.retrofit.APIManager;
import com.fourrunstudios.themoviedb.retrofit.MovieAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewRepo {
    private static ReviewRepo instance;
    private List<ReviewResults> results;
    private MutableLiveData<Boolean> done;

    //singleton
    public static ReviewRepo getInstance() {
        if(instance==null){
            instance = new ReviewRepo();
        }
        return instance;
    }

    public MutableLiveData<List<ReviewResults>> getReview(){
        if(results!=null){
            MutableLiveData<List<ReviewResults>> data = new MutableLiveData<>();
            data.setValue(results);
            return data;
        }
        else return null;
    }

    public MutableLiveData<Boolean> getReviewFinished(){
        if(done==null){
            done = new MutableLiveData<>();
        }
        done.setValue(false);
        return done;
    }

    public void fetchReview(int id){
        MovieAPI movieAPI = APIManager.getMovieAPI();
        Call<Reviews> call = movieAPI.getReviews(id, 1);

        call.enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                if(response.isSuccessful()){
                    results = response.body().getResults();
                    done.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<Reviews> call, Throwable t) {

            }
        });
    }

    public void fetchReview(int id, int page){
        MovieAPI movieAPI = APIManager.getMovieAPI();
        Call<Reviews> call = movieAPI.getReviews(id, page);

        call.enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                if(response.isSuccessful()){
                    results.addAll(response.body().getResults());
                    done.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<Reviews> call, Throwable t) {

            }
        });
    }
}
