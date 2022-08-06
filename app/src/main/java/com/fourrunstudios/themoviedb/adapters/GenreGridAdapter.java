package com.fourrunstudios.themoviedb.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fourrunstudios.themoviedb.R;
import com.fourrunstudios.themoviedb.models.Genre;

import java.util.List;

public class GenreGridAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Genre> genreList;

    public GenreGridAdapter(Context context, List<Genre> genreList) {
        this.context = context;
        this.genreList = genreList;
    }

    @Override
    public int getCount() {
        return genreList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(inflater==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view==null){
            view = inflater.inflate(R.layout.genre_grid_item, null);
        }
        TextView tv_name = view.findViewById(R.id.tv_genre_name);
        tv_name.setText(genreList.get(position).getName());

        return view;
    }


}
