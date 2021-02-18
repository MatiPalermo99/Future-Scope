package com.example.future_scope.model;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.ArrayList;

@Entity
public class User implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private String email;
    private String username;
    private String password;
    private Image fotoPerfil;
    private ArrayList<Review> reviews;
    private String descripcionPerfil;
    private ArrayList<User> seguidores;
    private ArrayList<User> seguidos;
    private ArrayList<Lista> listas;

    public User(String email, String username, String password, Image fotoPerfil, ArrayList<Review> reviews, String descripcionPerfil, ArrayList<User> seguidores, ArrayList<User> seguidos, ArrayList<Lista> listas) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.fotoPerfil = fotoPerfil;
        this.reviews = reviews;
        this.descripcionPerfil = descripcionPerfil;
        this.seguidores = seguidores;
        this.seguidos = seguidos;
        this.listas = listas;
    }


    @Ignore
    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Ignore
    public User(){
    }

    protected User(Parcel in) {
        email = in.readString();
        username = in.readString();
        password = in.readString();
        descripcionPerfil = in.readString();
        seguidores = in.createTypedArrayList(User.CREATOR);
        seguidos = in.createTypedArrayList(User.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(descripcionPerfil);
        dest.writeTypedList(seguidores);
        dest.writeTypedList(seguidos);
    }
}
