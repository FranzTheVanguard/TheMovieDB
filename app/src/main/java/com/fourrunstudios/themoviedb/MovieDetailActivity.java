package com.fourrunstudios.themoviedb;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fourrunstudios.themoviedb.adapters.ReviewRecyclerViewAdapter;
import com.fourrunstudios.themoviedb.databinding.ActivityMovieDetailBinding;
import com.fourrunstudios.themoviedb.models.MovieDetails;
import com.fourrunstudios.themoviedb.models.ReviewResults;
import com.fourrunstudios.themoviedb.viewmodels.MovieDetailActivityViewModel;

import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private MovieDetailActivityViewModel viewModel;
    private ReviewRecyclerViewAdapter adapter;
    private ActivityMovieDetailBinding binding;
    private Context context;
    private RecyclerView.OnScrollListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MovieDetailActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        context = this;
        init();

    }
    private void init(){
        if(listener==null){
            listener = new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if(!recyclerView.canScrollVertically(1)&&recyclerView.canScrollVertically(-1)&&newState == RecyclerView.SCROLL_STATE_IDLE){
                        viewModel.fetchReviewNextPage();
                    }
                }
            };
        }
        viewModel.setMovieId(getIntent());
        viewModel.init();
        viewModel.getDetailFinished().observe(this, aBoolean -> {
            if(aBoolean) setupDetail();
        });
        viewModel.getReviewFinished().observe(this, aBoolean -> {
            if(aBoolean) setupReview();
        });

    }

    private void setupDetail() {
        viewModel.getDetails().observe(this, new Observer<MovieDetails>() {
            @Override
            public void onChanged(MovieDetails movieDetails) {
                Glide.with(context)
                        .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2"+movieDetails.getPoster_path())
                        .apply(new RequestOptions().override(300, 450))
                        .into(binding.ivPoster);
                binding.trailerbutton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Log.d("", "set onClick: ");
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+viewModel.getKey())));
                    }
                });
                binding.invalidateAll();
            }
        });
    }
    private void setupReview(){
        if(adapter == null){
            adapter = new ReviewRecyclerViewAdapter(this,
                    viewModel.getReview().getValue());
        }
        viewModel.getReview().observe(this, new Observer<List<ReviewResults>>() {
            @Override
            public void onChanged(List<ReviewResults> movies) {
                adapter.notifyItemInserted(viewModel.getItemPosition());

            }
        });
        if(binding.reviewRecyclerview.getAdapter()==null)binding.reviewRecyclerview.setAdapter(adapter);
        if(binding.reviewRecyclerview.getLayoutManager()==null)binding.reviewRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        binding.reviewRecyclerview.clearOnScrollListeners();
        binding.reviewRecyclerview.addOnScrollListener(listener);
    }
}