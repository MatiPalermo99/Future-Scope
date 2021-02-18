package com.example.future_scope.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity
public class Review {


    @PrimaryKey(autoGenerate = true)
    private Long idReview;
    private User usuario;
    private Pelicula pelicula;
    private String tituloReview;
    private String descripcion;
    private double rating;
    private Calendar fecha;
    private boolean favorito;

    public Review(Long idReview, User usuario, Pelicula pelicula, String tituloReview, String descripcion, double rating, Calendar fecha, boolean favorito) {
        this.idReview = idReview;
        this.usuario = usuario;
        this.pelicula = pelicula;
        this.tituloReview = tituloReview;
        this.descripcion = descripcion;
        this.rating = rating;
        this.fecha = fecha;
        this.favorito = favorito;
    }

    @Ignore
    public Review(User usuario, Pelicula pelicula, String tituloReview, String descripcion, double rating, Calendar fecha) {
        this.usuario = usuario;
        this.pelicula = pelicula;
        this.tituloReview = tituloReview;
        this.descripcion = descripcion;
        this.rating = rating;
        this.fecha = fecha;
    }

    @Ignore
    public Review(User usuario, Pelicula pelicula, String descripcion, double rating, Calendar fecha, boolean favorito) {
        this.usuario = usuario;
        this.pelicula = pelicula;
        this.descripcion = descripcion;
        this.rating = rating;
        this.fecha = fecha;
        this.favorito = favorito;
    }

    public Long getIdReview() {
        return idReview;
    }

    public void setIdReview(Long idReview) {
        this.idReview = idReview;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
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

    @Override
    public String toString() {
        return "Review{" +
                "usuario=" + usuario +
                ", pelicula=" + pelicula +
                ", tituloReview='" + tituloReview + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", rating=" + rating +
                ", fecha=" + fecha +
                ", favorito=" + favorito +
                '}';
    }
}
