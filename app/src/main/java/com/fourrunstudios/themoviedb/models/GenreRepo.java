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

public class GenreRepo {
    private static GenreRepo instance;
    private List<Genre> genreList;
    private MutableLiveData<Boolean> done;


    //singleton
    public static GenreRepo getInstance() {
        if(instance==null){
            instance = new GenreRepo();
        }
        return instance;
    }
    public MutableLiveData<List<Genre>> getGenres(){
        if(genreList!=null){
            MutableLiveData<List<Genre>> data = new MutableLiveData<>();
            data.setValue(genreList);
            return data;
        }
        else return null;
    }
    public MutableLiveData<Boolean> getGenreFinished(){
        if(done==null){
            done = new MutableLiveData<>();
        }
        done.setValue(false);
        return done;
    }

    public void fetchGenre(){
        MovieAPI movieAPI = APIManager.getMovieAPI();
        Call<GenreObject> call = movieAPI.getGenres();
        call.enqueue(new Callback<GenreObject>() {
            @Override
            public void onResponse(Call<GenreObject> call, Response<GenreObject> response) {
                if(response.isSuccessful()){
                    genreList = response.body().getGenres();

                    ;
                    done.setValue(true);
                }

            }

            @Override
            public void onFailure(Call<GenreObject> call, Throwable t) {

            }
        });
    }


}
