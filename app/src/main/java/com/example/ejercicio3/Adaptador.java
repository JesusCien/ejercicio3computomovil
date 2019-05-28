package com.example.ejercicio3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ejercicio3.Curso;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private Context contexto;
    ArrayList<Curso> cursos;

    private static LayoutInflater inflater = null;


    public Adaptador(Context contexto, ArrayList<Curso> alumnos) {

        this.contexto = contexto;
        this.cursos = alumnos;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cursos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return cursos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.elemento_lista,null);
        TextView tvcursoid = vista.findViewById(R.id.tvid);
        TextView tvcursonombre = vista.findViewById(R.id.tvnombre);
        TextView tvcursosede= vista.findViewById(R.id.tvsede);
        TextView tvcursofechainic= vista.findViewById(R.id.tvfechainic);
        TextView tvcursofechafin= vista.findViewById(R.id.tvfechafin);
        tvcursoid.setText(cursos.get(position).getIdd());
        tvcursonombre.setText(cursos.get(position).getNombre());
        tvcursosede.setText(cursos.get(position).getSede());
        tvcursofechainic.setText(cursos.get(position).getFechainic());
        tvcursofechafin.setText(cursos.get(position).getFechafin());
        return vista;
    }

}