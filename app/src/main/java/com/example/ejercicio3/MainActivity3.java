package com.example.ejercicio3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity{
    private EditText text;
    private EditText text2;
    private EditText text3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        text = (EditText) findViewById(R.id.tvid2);
        text2 = (EditText) findViewById(R.id.tvnombre2);
        text3 = (EditText) findViewById(R.id.tvdescripcion2);

        String valor = getIntent().getStringExtra("id");
        String valor2 = getIntent().getStringExtra("nombre");
        String valor3 = getIntent().getStringExtra("descripcion");
        text.setText(valor);//"6541");
        text2.setText(valor2);//"word avanzado");
        text3.setText(valor3);//"El participante aplicará las herramientas de Word para elaborar y aplicar estilos, cartas personalizadas, tablas de contenido, índices, formularios y macroinstrucciones.");


    }
}