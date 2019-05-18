package com.example.udesc.apirestaula270419.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.udesc.apirestaula270419.R;
import com.example.udesc.apirestaula270419.models.Movie;
import com.example.udesc.apirestaula270419.services.MoviesService;
import com.example.udesc.apirestaula270419.ui.adapter.MovieListAdapter;
import com.example.udesc.apirestaula270419.ui.listener.OnMovieClickListener;

import java.util.ArrayList;
import java.util.List;

public class DetailsMoviesActivity extends AppCompatActivity {

    String idMovie = null;
    TextView idDetailsMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movies);

        idDetailsMovie = findViewById(R.id.idDetailsMovie);

        Intent intent = getIntent();
        idMovie = intent.getStringExtra("id");

        new PopulateTask().execute(idMovie);
    }


    class PopulateTask extends AsyncTask<String, Void, Movie> {
        //1º: DoInbackground recebe
        //2º: Não usamos progress
        //3º: retorno do doInnbackground e onPostExecute recebe

        @Override
        protected Movie doInBackground(String... strings) {
            MoviesService conecta = new MoviesService();

            return conecta.getDetailsMovie(strings[0]);

        }

        @Override
        protected void onPostExecute(Movie filme) {
            idDetailsMovie.setText(filme.getTitle());
        }
    }
}
