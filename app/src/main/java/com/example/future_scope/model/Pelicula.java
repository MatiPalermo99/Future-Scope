package com.example.future_scope.model;

import android.media.Image;

public class Pelicula {
    private String titulo;
    private Image image;
    private int anio;
    private int duracionMin;
    private double rating;

    public Pelicula(String titulo, int anio, int duracionMin, double rating) {
        this.titulo = titulo;
        this.anio = anio;
        this.duracionMin = duracionMin;
        this.rating = rating;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "titulo='" + titulo + '\'' +
                ", image=" + image +
                ", anio=" + anio +
                ", duracionMin=" + duracionMin +
                ", rating=" + rating +
                '}';
    }
}
