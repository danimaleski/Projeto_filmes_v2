package com.example.udesc.apirestaula270419.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.udesc.apirestaula270419.R;

public class MainActivity extends AppCompatActivity {

    Button btnFilmes;
    Button btnDiretores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFilmes = findViewById(R.id.btnFilmes);
        btnDiretores = findViewById(R.id.btnDiretores);

        btnFilmes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListaMoviesActivity.class);
                startActivity(i);
            }
        });

        //Para criar automático, escreva ON após o new e escolha a primeira opção
        btnDiretores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListaDirectorActivity.class);
                startActivity(i);
            }
        });

    }

}


