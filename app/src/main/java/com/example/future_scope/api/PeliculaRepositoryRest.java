package com.example.future_scope.api;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import com.example.future_scope.model.Pelicula;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeliculaRepositoryRest {

    PeliculaDAORest peliculaDAORest;

    private static int PAGE = 1;
    private static String API_KEY= "dcf1eb4da460836cee190519fd363a2f";
    private static String LANGUAGE = "en-US";
    private static String CATEGORY = "popular";
    private static Boolean INCLUDE_ADULT = false;

    public PeliculaRepositoryRest(){
        peliculaDAORest = ApiBuilder.getInstance().getPeliculaRest();
    }

    public void listarPeliculas(final Handler h){
        Call<MovieResults> callPeliculas = peliculaDAORest.getPeliculas(CATEGORY,API_KEY,LANGUAGE,PAGE);

        callPeliculas.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                Log.d("API REST","EJECUTO LISTAR");

                MovieResults results=response.body();

                final Message msg = h.obtainMessage();
                final Bundle datos = new Bundle();

                List<MovieResults.ResultsDTO> listaPeliculas = results.getResults();

                datos.putParcelableArrayList("peliculas", (ArrayList<? extends Parcelable>) listaPeliculas);
                datos.putString("accion", AccionesDAO.LISTAR_PELICULAS.toString());
                msg.setData(datos);
                h.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                Log.d("API REST","ERROR "+t.getMessage());
                final Message msg = h.obtainMessage();
                final Bundle datos = new Bundle();
                datos.putString("accion", AccionesDAO.ERROR.toString());
                datos.putString("error", t.getMessage());
                msg.setData(datos);
                h.sendMessage(msg);
            }
        });
    }

    public void buscarPeliculaID(String id,final Handler h){
        Call<Details> callPeliculaId = peliculaDAORest.getPeliculaId(id,API_KEY,LANGUAGE);

        callPeliculaId.enqueue(new Callback<Details>() {
            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {
                Log.d("API REST","EJECUTO BUSCAR ID");

                Details results=response.body();

                final Message msg = h.obtainMessage();
                final Bundle datos = new Bundle();

                datos.putParcelable("pelicula", results);
                datos.putString("accion", AccionesDAO.BUSCAR_ID.toString());
                msg.setData(datos);
                h.sendMessage(msg);

            }

            @Override
            public void onFailure(Call<Details> call, Throwable t) {
                Log.d("API REST","ERROR "+t.getMessage());
                final Message msg = h.obtainMessage();
                final Bundle datos = new Bundle();
                datos.putString("accion", AccionesDAO.ERROR.toString());
                datos.putString("error", t.getMessage());
                msg.setData(datos);
                h.sendMessage(msg);
            }
        });
    }

    public void buscarPeliculaTitulo(String titulo,final Handler h){
        Call<SearchResults> callPeliculaTitulo = peliculaDAORest.getPeliculaTitulo(titulo,API_KEY,LANGUAGE,INCLUDE_ADULT);

        callPeliculaTitulo.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                Log.d("API REST","EJECUTO BUSCAR TITULO");

                SearchResults results=response.body();

                ArrayList<SearchResults.ResultsDTO> resultados = (ArrayList<SearchResults.ResultsDTO>) results.getResults();
                System.out.println(resultados);

                final Message msg = h.obtainMessage();
                final Bundle datos = new Bundle();

                datos.putParcelableArrayList("peliculas_titulo", resultados);
                datos.putString("accion", AccionesDAO.BUSCAR_TITULO.toString());
                msg.setData(datos);
                h.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                Log.d("API REST","ERROR "+t.getMessage());
                final Message msg = h.obtainMessage();
                final Bundle datos = new Bundle();
                datos.putString("accion", AccionesDAO.ERROR.toString());
                datos.putString("error", t.getMessage());
                msg.setData(datos);
                h.sendMessage(msg);
            }
        });

    }

}
