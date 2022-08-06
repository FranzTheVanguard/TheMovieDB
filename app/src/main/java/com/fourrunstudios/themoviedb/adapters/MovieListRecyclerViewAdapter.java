package com.fourrunstudios.themoviedb.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fourrunstudios.themoviedb.R;
import com.fourrunstudios.themoviedb.models.Movie;

import java.util.List;

public class MovieListRecyclerViewAdapter extends RecyclerView.Adapter<MovieListRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<Movie> movieList;
    private OnMovieClickListener onMovieClickListener;

    public MovieListRecyclerViewAdapter(Context context, List<Movie> movieList, OnMovieClickListener onMovieClickListener){
        this.context = context;
        this.movieList = movieList;
        this.onMovieClickListener = onMovieClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_list_item, parent, false);
        return new MyViewHolder(view, onMovieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.dateView.setText(movieList.get(position).getDate());
        holder.titleView.setText(movieList.get(position).getMovieTitle());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2"+movieList.get(position).getPosterPath())
                .apply(new RequestOptions().override(150, 225))
                .into(holder.posterView);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView posterView;
        TextView titleView;
        TextView dateView;
        OnMovieClickListener onMovieClickListener;
        public MyViewHolder(@NonNull View itemView, OnMovieClickListener onMovieClickListener) {
            super(itemView);
            posterView = itemView.findViewById(R.id.poster_view);
            titleView = itemView.findViewById(R.id.movie_title_view);
            dateView = itemView.findViewById(R.id.release_date_view);
            this.onMovieClickListener = onMovieClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMovieClickListener.onMovieClick(getAdapterPosition());
        }
    }
    public interface OnMovieClickListener{
        void onMovieClick(int position);
    }
}
