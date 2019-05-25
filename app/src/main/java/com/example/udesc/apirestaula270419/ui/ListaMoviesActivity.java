package com.example.udesc.apirestaula270419.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.udesc.apirestaula270419.services.MoviesService;
import com.example.udesc.apirestaula270419.R;
import com.example.udesc.apirestaula270419.models.Movie;
import com.example.udesc.apirestaula270419.ui.adapter.MovieListAdapter;
import com.example.udesc.apirestaula270419.ui.listener.OnMovieClickListener;

import java.util.List;

public class ListaMoviesActivity extends AppCompatActivity {

    RecyclerView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_movies);

        lista = findViewById(R.id.lista);

        new PopulateTask().execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        new PopulateTask().execute();
    }

    class PopulateTask extends AsyncTask<Void, Void, List<Movie>> {
        //1º: DoInbackground recebe
        //2º: Não usamos progress
        //3º: retorno do doInnbackground e onPostExecute recebe

        @Override
        protected List<Movie> doInBackground(Void... filmes) {
            MoviesService conecta = new MoviesService();

            return conecta.getAll();

        }

        @Override
        protected void onPostExecute(List<Movie> filmes) {
            MovieListAdapter adapter = new MovieListAdapter(new OnMovieClickListener() {
                @Override
                public void onMovieClick(Movie movie) {
                    //Log.d("TESTE onPostExecute", movie.toString());
                    //DetailsMoviesActivity
                    Intent i = new Intent(ListaMoviesActivity.this, DetailsMoviesActivity.class);
                    i.putExtra("id", movie.getId());
                    startActivity(i);
                }
            });

            adapter.setMovies(filmes);

            RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
            lista.setLayoutManager(manager);
            lista.setAdapter(adapter);
        }
    }
}
