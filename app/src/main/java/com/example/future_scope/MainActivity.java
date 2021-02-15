package com.example.future_scope;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.future_scope.api.ApiInterface;
import com.example.future_scope.api.MovieResults;
import com.example.future_scope.ui.AgregarFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private NavController navController;
    private Activity activity;
    private static String BASE_URL = "https://api.themoviedb.org";
    private static int PAGE = 1;
    private static String API_KEY= "dcf1eb4da460836cee190519fd363a2f";
    private static String LANGUAGE = "en-US";
    private static String CATEGORY = "popular";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<MovieResults> call =apiInterface.getPeliculas(CATEGORY,API_KEY,LANGUAGE,PAGE);

        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults results=response.body();
                List<MovieResults.ResultsDTO> listaPeliculas = results.getResults();
                MovieResults.ResultsDTO primerPelicula = listaPeliculas.get(0);

                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                System.out.println(primerPelicula.getTitle());
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.printStackTrace();
            }
        });



        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_notifications,R.id.navigation_agregar, R.id.navigation_watchlist, R.id.navigation_profile)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}