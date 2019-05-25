package com.example.udesc.apirestaula270419.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.udesc.apirestaula270419.R;
import com.example.udesc.apirestaula270419.services.MoviesService;

public class IncluiFilmeActivity extends AppCompatActivity {

    Button btnAdicionar;
    EditText campoIdFilme;
    EditText campoTituloFilme;
    EditText campoDuracao;
    EditText campoCusto;
    EditText campoAno;
    EditText campoMoeda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inclui_filme);

        campoIdFilme = findViewById(R.id.campoIDFilme);
        campoTituloFilme = findViewById(R.id.campoTituloFilme);
        campoDuracao = findViewById(R.id.campoDuracao);
        campoCusto = findViewById(R.id.campoCusto);
        campoAno = findViewById(R.id.campoAno);
        campoMoeda = findViewById(R.id.campoMoeda);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PopulateTask().execute();
            }
        });

    }

    class PopulateTask extends AsyncTask<Void, Void, Boolean> {
        //1º: DoInbackground recebe
        //2º: Não usamos progress
        //3º: retorno do doInnbackground e onPostExecute recebe

        @Override
        protected Boolean doInBackground(Void... voids) {
            MoviesService conecta = new MoviesService();
            boolean resultado = conecta.addMovie(
                    campoIdFilme.getText().toString(),
                    campoTituloFilme.getText().toString(),
                    Integer.parseInt(campoDuracao.getText().toString()),
                    Double.parseDouble(campoCusto.getText().toString()),
                    Integer.parseInt(campoAno.getText().toString()),
                    campoMoeda.getText().toString()
            );

            return resultado;
        }

        @Override
        protected void onPostExecute(Boolean sucesso) {
            if (sucesso) {
                Toast.makeText(IncluiFilmeActivity.this, "Incluído com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(IncluiFilmeActivity.this, "ERROOOOO!", Toast.LENGTH_LONG).show();
            }

        }
    }
}
