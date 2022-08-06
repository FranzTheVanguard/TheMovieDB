package com.fourrunstudios.themoviedb.models;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.fourrunstudios.themoviedb.retrofit.APIManager;
import com.fourrunstudios.themoviedb.retrofit.MovieAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepo {
    private static MovieRepo instance;
    private List<Movie> movieList;
    private MutableLiveData<Boolean> done;

    //singleton
    public static MovieRepo getInstance() {
        if(instance==null){
            instance = new MovieRepo();
        }
        return instance;
    }
    public MutableLiveData<List<Movie>> getMovies(){
        if(movieList!=null){
            MutableLiveData<List<Movie>> data = new MutableLiveData<>();
            data.setValue(movieList);
            return data;
        }
        else return null;
    }
    public MutableLiveData<Boolean> getMovieFinished(){
        if(done==null){
            done = new MutableLiveData<>();
        }
        done.setValue(false);
        return done;
    }

    public void fetchMovieByGenre(String genre, int page){
        MovieAPI movieAPI = APIManager.getMovieAPI();
        Call<MovieResults> call = movieAPI.getMovies(genre, page);
        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                if(response.isSuccessful()){
                    movieList.addAll(response.body().getResults());
                    done.setValue(true);
                }

            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {

            }
        });
    }

    public void fetchMovieByGenre(String genre){
        MovieAPI movieAPI = APIManager.getMovieAPI();
        Call<MovieResults> call = movieAPI.getMovies(genre, 1);

        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                if(response.isSuccessful()){
                    movieList = response.body().getResults();
                    done.setValue(true);
                }

            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {

            }
        });
    }


}
