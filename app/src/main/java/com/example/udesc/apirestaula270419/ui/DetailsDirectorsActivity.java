package com.example.udesc.apirestaula270419.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.udesc.apirestaula270419.R;
import com.example.udesc.apirestaula270419.models.Director;
import com.example.udesc.apirestaula270419.models.Movie;
import com.example.udesc.apirestaula270419.services.DirectorService;
import com.example.udesc.apirestaula270419.services.MoviesService;

public class DetailsDirectorsActivity extends AppCompatActivity {

    String idDirector = null;
    TextView idDetailsDirector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_directors);

        idDetailsDirector = findViewById(R.id.idDetailsDirector);

        Intent intent = getIntent();
        idDirector = intent.getStringExtra("id");

        new PopulateTask().execute(idDirector);

    }

    class PopulateTask extends AsyncTask<String, Void, Director> {
        //1º: DoInbackground recebe
        //2º: Não usamos progress
        //3º: retorno do doInnbackground e onPostExecute recebe

        @Override
        protected Director doInBackground(String... strings) {
            DirectorService conecta = new DirectorService();

            return conecta.getById(strings[0]);

        }

        @Override
        protected void onPostExecute(Director diretor) {

            idDetailsDirector.setText(
                    "Id: " + diretor.getId() + "\n" +
                    "Nome: " + diretor.getName() + "\n" +
                    "Aniversário: " + diretor.getBirth() + "\n" +
                    "País: " + diretor.getCountry()
            );
        }
    }
}
