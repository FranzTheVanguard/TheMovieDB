package com.fourrunstudios.themoviedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.fourrunstudios.themoviedb.adapters.MovieListRecyclerViewAdapter;
import com.fourrunstudios.themoviedb.databinding.ActivityMovieListBinding;
import com.fourrunstudios.themoviedb.models.Movie;
import com.fourrunstudios.themoviedb.viewmodels.MovieListActivityViewModel;

import java.util.List;

public class MovieListActivity extends AppCompatActivity implements MovieListRecyclerViewAdapter.OnMovieClickListener {


    private MovieListActivityViewModel viewModel;
    private MovieListRecyclerViewAdapter adapter;
    private ActivityMovieListBinding binding;
    private Intent movieDetailIntent;
    private RecyclerView.OnScrollListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MovieListActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list);
        binding.setViewModel(viewModel);
        init();
    }
    private void init(){
        if(listener==null){
            listener = new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if(!recyclerView.canScrollVertically(1)&&recyclerView.canScrollVertically(-1)&&newState == RecyclerView.SCROLL_STATE_IDLE){
                        viewModel.fetchMovieNextPage();
                    }
                }
            };
        }
        viewModel.setSelectedGenre(getIntent());
        viewModel.init();

        viewModel.getMovieFinished().observe(this, aBoolean -> {
            if(aBoolean) setupRecyclerView();
        });
        movieDetailIntent = new Intent(this, MovieDetailActivity.class);
    }

    private void setupRecyclerView() {
        if(adapter==null){
            adapter = new MovieListRecyclerViewAdapter(this,
                    viewModel.getMovieLiveData().getValue(), this);
        }
        viewModel.getMovieLiveData().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.notifyItemInserted(viewModel.getItemPosition());
            }
        });
        if(binding.movieRecyclerview.getAdapter()==null) binding.movieRecyclerview.setAdapter(adapter);
        if(binding.movieRecyclerview.getLayoutManager()==null) binding.movieRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        binding.movieRecyclerview.clearOnScrollListeners();
        binding.movieRecyclerview.addOnScrollListener(listener);
    }

    @Override
    public void onMovieClick(int position) {
        movieDetailIntent.putExtra("movie_id", viewModel.getMovieLiveData().getValue().get(position).getId());
        startActivity(movieDetailIntent);
    }

}