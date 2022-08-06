package com.fourrunstudios.themoviedb.models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.fourrunstudios.themoviedb.retrofit.APIManager;
import com.fourrunstudios.themoviedb.retrofit.MovieAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsRepo {
    private static DetailsRepo instance;
    private MovieDetails details;
    private MutableLiveData <Boolean> done;
    private List<VideoObject> videoList;
    //singleton
    public static DetailsRepo getInstance() {
        if(instance==null){
            instance = new DetailsRepo();
        }
        return instance;
    }

    public MutableLiveData<MovieDetails> getDetails(){
        if(details!=null){
            MutableLiveData<MovieDetails> data = new MutableLiveData<>();
            data.setValue(details);
            return data;
        }
        else return null;
    }
    public MutableLiveData<Boolean> getDetailFinished(){
        if(done==null){
            done = new MutableLiveData<>();
        }
        done.setValue(false);
        return done;
    }
    public String getTrailerKey(){
        for (VideoObject result : videoList) {
            Log.d("", result.getType());
            if(result.getType().equals("Trailer")) return result.getKey();
        }
        return null;
    }
    public void fetchDetails(int id){
        MovieAPI movieAPI = APIManager.getMovieAPI();
        Call<MovieDetails> call = movieAPI.getDetails(id);

        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if(response.isSuccessful()){
                    details = response.body();
                    done.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {

            }
        });
    }
    public void fetchTrailer(int id){
        MovieAPI movieAPI = APIManager.getMovieAPI();
        Call<VideoResult> call = movieAPI.getTrailer(id);
        call.enqueue(new Callback<VideoResult>() {
            @Override
            public void onResponse(Call<VideoResult> call, Response<VideoResult> response) {
                if(response.isSuccessful()){
                    Log.d("", "onResponse: "+response.body().getResults());
                    videoList = response.body().getResults();
                }
                else Log.d("", "response not successful: "+response.errorBody());
            }

            @Override
            public void onFailure(Call<VideoResult> call, Throwable t) {

            }
        });
    }
}
