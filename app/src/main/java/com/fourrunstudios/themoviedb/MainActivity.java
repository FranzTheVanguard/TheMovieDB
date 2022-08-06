package com.fourrunstudios.themoviedb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.fourrunstudios.themoviedb.adapters.GenreGridAdapter;
import com.fourrunstudios.themoviedb.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private GenreGridAdapter genreGridAdapter;
    private Intent movieListIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    private void init(){
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.init();
        viewModel.getGenreFinished().observe(this, aBoolean -> {
            if(aBoolean) setupGrid();
        });
        movieListIntent = new Intent(this, MovieListActivity.class);
    }

    private void setupGrid() {
        GridView gridView = findViewById(R.id.gv_genre);
        genreGridAdapter = new GenreGridAdapter(MainActivity.this, viewModel.getGenreLiveData().getValue());
        gridView.setAdapter(genreGridAdapter);

        gridView.setOnItemClickListener((adapterView, view, position, l) -> {
            movieListIntent.putExtra("genre_name", viewModel.getGenreLiveData().getValue().get(position).getName());
            movieListIntent.putExtra("genre_id", viewModel.getGenreLiveData().getValue().get(position).getId());
            startActivity(movieListIntent);
        });
    }
}