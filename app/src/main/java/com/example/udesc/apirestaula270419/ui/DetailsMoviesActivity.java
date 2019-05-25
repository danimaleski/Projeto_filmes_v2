package com.example.udesc.apirestaula270419.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    Button btnExcluiFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movies);

        idDetailsMovie = findViewById(R.id.idDetailsMovie);
        btnExcluiFilme = findViewById(R.id.btnExcluiFilme);

        Intent intent = getIntent();
        idMovie = intent.getStringExtra("id");

        new PopulateTask().execute(idMovie);

        btnExcluiFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(idMovie);
            }
        });

    }

    private void openDialog (final String id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                new DeleteTask().execute(id);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
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

            idDetailsMovie.setText("Título: " + filme.getTitle() + "\n" +
                    "Duração: " + filme.getDuration() + "\n" +
                    "Custo: " + filme.getCost() + "\n" +
                    "Ano: " + filme.getYear() + "\n" +
                    "Moeda: " + filme.getCurrency());
        }
    }

    class DeleteTask extends AsyncTask<String, Void, Boolean> {
        //1º: DoInbackground recebe
        //2º: Não usamos progress
        //3º: retorno do doInnbackground e onPostExecute recebe

        @Override
        protected Boolean doInBackground(String... strings) {
            MoviesService conecta = new MoviesService();

            return conecta.deleteMovie(strings[0]);

        }

        @Override
        protected void onPostExecute(Boolean sucesso) {
            if (sucesso) {
                Toast.makeText(DetailsMoviesActivity.this, "Excluído com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(DetailsMoviesActivity.this, "ERROOOOO!", Toast.LENGTH_LONG).show();
            }

            finish();
        }
    }
}
