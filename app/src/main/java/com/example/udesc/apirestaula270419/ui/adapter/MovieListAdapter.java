package com.example.udesc.apirestaula270419.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.udesc.apirestaula270419.R;
import com.example.udesc.apirestaula270419.models.Movie;
import com.example.udesc.apirestaula270419.ui.listener.OnMovieClickListener;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private OnMovieClickListener listener;

    public MovieListAdapter(OnMovieClickListener listener) {
        this.listener = listener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        this.listener = listener;
    }


    // Aqui cria a caixinha
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int index) {
        View movieItem = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.movie_list_item, viewGroup, false);

        Log.d("TESTE", "Criando holder na index: " + index);

        return new ViewHolder(movieItem, this.listener);
    }

    // Aqui coloca os dados na caixinha
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index) {
        final Movie movie = movies.get(index);
        viewHolder.setTitle(movie.getTitle());
        viewHolder.setYear(movie.getYear());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMovieClick(movie);
            }
        });
        Log.d("TESTE", "Setando dados na index: " + index);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView xtitle;
        private TextView xyear;
        private View itemView;

        public ViewHolder(@NonNull View itemView, final OnMovieClickListener listener) {
            super(itemView);
            this.itemView = itemView;
            xtitle = itemView.findViewById(R.id.xtitle);
            xyear = itemView.findViewById(R.id.xyear);
        }

        public void setTitle(String name){
            xtitle.setText(name);
        }

        public void setYear(Integer year){
            xyear.setText(year.toString());
        }
    }

}
