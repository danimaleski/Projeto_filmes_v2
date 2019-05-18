package com.example.udesc.apirestaula270419.ui;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.udesc.apirestaula270419.models.Movie;
import com.example.udesc.apirestaula270419.services.DirectorService;
import com.example.udesc.apirestaula270419.R;
import com.example.udesc.apirestaula270419.models.Director;
import com.example.udesc.apirestaula270419.ui.adapter.DirectorListAdapter;
import com.example.udesc.apirestaula270419.ui.adapter.MovieListAdapter;
import com.example.udesc.apirestaula270419.ui.listener.OnDirectorClickListener;
import com.example.udesc.apirestaula270419.ui.listener.OnMovieClickListener;

import java.util.List;

public class ListaDirectorActivity extends AppCompatActivity {

    RecyclerView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_director);

        lista = findViewById(R.id.lista);

        new PopulateTask().execute();
    }

    class PopulateTask extends AsyncTask<Void, Void, List<Director>> {
        //1º: DoInbackground recebe
        //2º: Não usamos progress
        //3º: retorno do doInnbackground e onPostExecute recebe

        @Override
        protected List<Director> doInBackground(Void... diretores) {
            DirectorService conecta = new DirectorService();

            return conecta.getAll();

        }

        @Override
        protected void onPostExecute(List<Director> directors) {
            DirectorListAdapter adapter = new DirectorListAdapter(new OnDirectorClickListener() {
                @Override
                public void onDirectorClick(Director director) {
                    Log.d("TESTE", director.toString());
                }
            });

            adapter.setDirectors(directors);

            RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
            lista.setLayoutManager(manager);
            lista.setAdapter(adapter);
        }
    }
}