package com.example.ejercicio3;

import android.content.Context;

import com.example.ejercicio3.R;

import java.io.Serializable;

public class Curso2 implements Serializable {
    private int id;
    private String idd;
    private String nombre;
    private String descripcion;
    private Context contexto;

    public Curso2(int id,String idd, String nombre, String descripcion) {
        this.id = id;
        this.idd=idd;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdd() {
        return idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    @Override
    public String toString() {
        return contexto.getResources().getString(R.string.texto_nombre)
                + getId()
                + contexto.getResources().getString(R.string.texto_apellido)
                + getNombre()
                + contexto.getResources().getString(R.string.texto_apellido)
                + getDescripcion();
    }
}
