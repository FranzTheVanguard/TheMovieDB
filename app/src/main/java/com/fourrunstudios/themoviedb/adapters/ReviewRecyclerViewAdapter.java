package com.fourrunstudios.themoviedb.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fourrunstudios.themoviedb.R;
import com.fourrunstudios.themoviedb.models.ReviewResults;

import java.util.List;

public class ReviewRecyclerViewAdapter extends RecyclerView.Adapter<ReviewRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<ReviewResults> reviews;

    public ReviewRecyclerViewAdapter(Context context, List<ReviewResults> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.review_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.usernameView.setText(reviews.get(position).getAuthor());
        holder.contentView.setText(reviews.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView usernameView;
        TextView contentView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameView = itemView.findViewById(R.id.tv_review_username);
            contentView = itemView.findViewById(R.id.tv_review_content);

        }
    }
}
