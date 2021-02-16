package com.example.future_scope.api;

import com.example.future_scope.model.Pelicula;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PeliculaDAORest {

    @GET("/3/movie/{category}")
    Call<MovieResults> getPeliculas(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
        );

    @GET("/3/movie/{id}")
    Call<Details> getPeliculaId(
            @Path("id") String id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

}
