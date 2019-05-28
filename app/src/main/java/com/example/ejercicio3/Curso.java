package com.example.ejercicio3;

import android.content.Context;

import com.example.ejercicio3.R;

import java.io.Serializable;

public class Curso implements Serializable {
    private int id;
    private String idd;
    private String nombre;
    private String sede;
    private String fechainic;
    private String fechafin;
    private Context contexto;

    public Curso(int id,String idd, String nombre, String sede,String fechainic,String fechafin) {
        this.id = id;
        this.idd=idd;
        this.nombre = nombre;
        this.sede = sede;
        this.fechainic = fechainic;
        this.fechafin= fechafin;
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

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }


    public String getFechainic() {
        return fechainic;
    }

    public void setFechainic(String fechainic){
        this.fechainic= fechainic;
    }


    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin){
        this.fechafin = fechafin;
    }




    @Override
    public String toString() {
        return contexto.getResources().getString(R.string.texto_nombre)
                + getId()
                + contexto.getResources().getString(R.string.texto_apellido)
                + getNombre()
                + contexto.getResources().getString(R.string.texto_apellido)
                + getSede()
                + contexto.getResources().getString(R.string.texto_apellido)
                + getFechafin()
                + contexto.getResources().getString(R.string.texto_apellido)
                + getFechainic();
    }
}
