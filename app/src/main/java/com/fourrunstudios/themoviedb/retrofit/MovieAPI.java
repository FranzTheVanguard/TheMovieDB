package com.fourrunstudios.themoviedb.retrofit;

import com.fourrunstudios.themoviedb.BuildConfig;
import com.fourrunstudios.themoviedb.models.GenreObject;
import com.fourrunstudios.themoviedb.models.MovieDetails;
import com.fourrunstudios.themoviedb.models.MovieResults;
import com.fourrunstudios.themoviedb.models.Reviews;
import com.fourrunstudios.themoviedb.models.VideoResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {

    @GET("genre/movie/list?language=en-US&api_key="+ BuildConfig.API_KEY)
    Call<GenreObject> getGenres();

    @GET("discover/movie?language=en-US&sort_by=popularity.desc&api_key="+ BuildConfig.API_KEY)
    Call<MovieResults> getMovies(@Query ("with_genres") String genre, @Query ("page") int page);

    @GET("movie/{movie_id}?language=en-US&api_key="+ BuildConfig.API_KEY)
    Call<MovieDetails> getDetails(@Path("movie_id") int id);

    @GET("movie/{movie_id}/reviews?language=en-US&api_key="+ BuildConfig.API_KEY)
    Call<Reviews> getReviews(@Path("movie_id") int id, @Query("page") int page);

    @GET("movie/{movie_id}/videos?&api_key="+ BuildConfig.API_KEY)
    Call<VideoResult> getTrailer(@Path("movie_id") int id);

}
