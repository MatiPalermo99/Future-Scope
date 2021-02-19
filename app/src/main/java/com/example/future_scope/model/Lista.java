package com.example.future_scope.model;

import java.util.ArrayList;

public class Lista {
    private String propietarioLista;
    private ArrayList<Pelicula> listaPeliculas;
    private String descripcionLista;
    private String nombreLista;

    public Lista(String propietarioLista, ArrayList<Pelicula> listaPeliculas, String descripcionLista, String nombreLista) {
        this.propietarioLista = propietarioLista;
        this.listaPeliculas = listaPeliculas;
        this.descripcionLista = descripcionLista;
        this.nombreLista = nombreLista;
    }

    public String getPropietarioLista() {
        return propietarioLista;
    }

    public void setPropietarioLista(String propietarioLista) {
        this.propietarioLista = propietarioLista;
    }

    public ArrayList<Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }

    public void setListaPeliculas(ArrayList<Pelicula> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }

    public String getDescripcionLista() {
        return descripcionLista;
    }

    public void setDescripcionLista(String descripcionLista) {
        this.descripcionLista = descripcionLista;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }
}
