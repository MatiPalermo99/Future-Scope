package com.example.future_scope.model;

import java.util.Calendar;

public class Review {

    private User usuario;
    private Pelicula pelicula;
    private String tituloReview;
    private String descripcion;
    private double rating;
    private Calendar fecha;

    public Review(User usuario, Pelicula pelicula, String tituloReview, String descripcion, double rating, Calendar fecha) {
        this.usuario = usuario;
        this.pelicula = pelicula;
        this.tituloReview = tituloReview;
        this.descripcion = descripcion;
        this.rating = rating;
        this.fecha = fecha;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public String getTituloReview() {
        return tituloReview;
    }

    public void setTituloReview(String tituloReview) {
        this.tituloReview = tituloReview;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
}
