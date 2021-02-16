package com.example.future_scope.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {
    private PeliculaDAORest peliculaService;
    private static ApiBuilder _INSTANCIA;
    private static String BASE_URL = "https://api.themoviedb.org";

    private ApiBuilder(){ }

    public PeliculaDAORest getPeliculaRest() {
        if(peliculaService ==null) this.iniciarRetrofit();
        return peliculaService;
    }

    public static ApiBuilder getInstance(){
        if(_INSTANCIA == null) {
            _INSTANCIA = new ApiBuilder();
        }
        return _INSTANCIA;
    }

    private void iniciarRetrofit(){
        /*
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        peliculaService =retrofit.create(PeliculaDAORest.class);
    }

}
