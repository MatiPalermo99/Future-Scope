package com.example.future_scope.model;

import android.media.Image;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.future_scope.api.SearchResults;

@Entity
public class Pelicula {

    @PrimaryKey(autoGenerate = true)
    private int idPelicula;
    private String titulo;
    private Image image;
    private int anio;
    private int duracionMin;
    private double rating;
    private String genero;

    public Pelicula(int idPelicula, String titulo, Image image, int anio, int duracionMin, double rating, String genero) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.image = image;
        this.anio = anio;
        this.duracionMin = duracionMin;
        this.rating = rating;
        this.genero = genero;
    }

    @Ignore
    public Pelicula(String titulo, int anio, int duracionMin, double rating) {
        this.titulo = titulo;
        this.anio = anio;
        this.duracionMin = duracionMin;
        this.rating = rating;
    }

    @Ignore
    public Pelicula(SearchResults.ResultsDTO resultsDTO) {
        this.idPelicula =resultsDTO.getId();
        this.titulo = resultsDTO.getTitle();
    }

    @Ignore
    public Pelicula(String titulo, int duracionMin,String genero) {
        this.titulo = titulo;
        this.anio = anio;
        this.duracionMin = duracionMin;
        this.genero = genero;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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
                ", duracionMin=" + duracionMin +
                ", genero='" + genero + '\'' +
                '}';
    }
}
