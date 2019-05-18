package com.example.udesc.apirestaula270419.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.udesc.apirestaula270419.R;
import com.example.udesc.apirestaula270419.models.Director;
import com.example.udesc.apirestaula270419.models.Movie;
import com.example.udesc.apirestaula270419.ui.listener.OnDirectorClickListener;
import com.example.udesc.apirestaula270419.ui.listener.OnMovieClickListener;

import java.util.ArrayList;
import java.util.List;

public class DirectorListAdapter extends RecyclerView.Adapter<DirectorListAdapter.ViewHolder> {

    private List<Director> directors = new ArrayList<>();
    private OnDirectorClickListener listener;

    public DirectorListAdapter(OnDirectorClickListener listener) {
        this.listener = listener;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
        this.listener = listener;
    }


    // Aqui cria a caixinha
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int index) {
        View directorItem = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.movie_list_item, viewGroup, false);

        Log.d("TESTE", "Criando holder na index: " + index);

        return new ViewHolder(directorItem, this.listener);
    }

    // Aqui coloca os dados na caixinha
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index) {
        final Director director = directors.get(index);
        viewHolder.setName(director.getName());
        viewHolder.setCountry(director.getCountry());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDirectorClick(director);
            }
        });
        Log.d("TESTE", "Setando dados na index: " + index);
    }

    @Override
    public int getItemCount() {
        return directors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView xtitle;
        private TextView xyear;
        private View itemView;

        public ViewHolder(@NonNull View itemView, final OnDirectorClickListener listener) {
            super(itemView);
            this.itemView = itemView;
            xtitle = itemView.findViewById(R.id.xtitle);
            xyear = itemView.findViewById(R.id.xyear);
        }

        public void setName(String name){
            xtitle.setText(name);
        }

        public void setCountry(String country){
            xyear.setText(country);
        }
    }

}
