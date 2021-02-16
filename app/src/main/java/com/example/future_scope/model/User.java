package com.example.future_scope.model;

import android.media.Image;

import java.sql.Array;
import java.util.ArrayList;

public class User {
    private String email;
    private String username;
    private String password;
    private Image fotoPerfil;
    private ArrayList<Review> reviews;
    private String descripcionPerfil;
    private ArrayList<User> seguidores;
    private ArrayList<User> seguidos;
    private ArrayList<Lista> listas;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Image getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(Image fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public User(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescripcionPerfil() {
        return descripcionPerfil;
    }

    public void setDescripcionPerfil(String descripcionPerfil) {
        this.descripcionPerfil = descripcionPerfil;
    }

    public ArrayList<User> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(ArrayList<User> seguidores) {
        this.seguidores = seguidores;
    }

    public ArrayList<User> getSeguidos() {
        return seguidos;
    }

    public void setSeguidos(ArrayList<User> seguidos) {
        this.seguidos = seguidos;
    }

    public ArrayList<Lista> getListas() {
        return listas;
    }

    public void setListas(ArrayList<Lista> listas) {
        this.listas = listas;
    }
}
