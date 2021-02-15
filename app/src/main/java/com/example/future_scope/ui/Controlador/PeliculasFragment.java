package com.example.future_scope.ui.Controlador;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.future_scope.MapsActivity;
import com.example.future_scope.R;
import com.example.future_scope.adapters.PeliculaAmigoRecyclerAdapter;
import com.example.future_scope.adapters.PeliculaRecyclerAdapter;
import com.example.future_scope.adapters.ReviewRecyclerAdapter;
import com.example.future_scope.model.Pelicula;
import com.example.future_scope.model.Review;
import com.example.future_scope.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PeliculasFragment extends Fragment {

    private RecyclerView rvPopulares,rvAmigos,rvReviews;
    private List<Pelicula> listaPeliculas =new ArrayList<>();
    private List<Review> listaReviewsAmigos =new ArrayList<>();

    private PeliculaRecyclerAdapter peliculasPopularesAdapter;
    private PeliculaAmigoRecyclerAdapter peliculasAmigosAdapter;
    private ReviewRecyclerAdapter reviewRecyclerAdapter;

    private Button btMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_peliculas, container, false);

        Pelicula p1 = new Pelicula("Birdman or (the unexpected virtue of ignorance)",2016,120,5.0);
        listaPeliculas.add(p1);
        Pelicula p2 = new Pelicula("John Wick",2014,125,4.0);
        listaPeliculas.add(p2);
        Pelicula p3 = new Pelicula("Mamma Mia",2007,110,3.0);
        listaPeliculas.add(p3);
        Pelicula p4 = new Pelicula("Django Unchained",2014,130,2.0);
        listaPeliculas.add(p4);

        rvPopulares = root.findViewById(R.id.rv_peliculas_populares);
        peliculasPopularesAdapter = new PeliculaRecyclerAdapter(listaPeliculas);
        rvPopulares.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvPopulares.setHasFixedSize(true);
        rvPopulares.setAdapter(peliculasPopularesAdapter);

        User u1 = new User("mauricio.toso@yahoo.com.ar","mtoso","12345678");
        User u2 = new User("micapierotti@gmail.com","mics","12345678");
        User u3 = new User("matipalermo@gmail.com","mati","12345678");

        Review r1 = new Review (u1,p1,"Muy buena!","Increible pelicula me re gusto!",8.2, Calendar.getInstance());
        Review r2 = new Review (u2,p2,"La mejor pelicula","Es imposible que esta pelicula no haya ganado ningun Oscar!",9.0, Calendar.getInstance());
        Review r3 = new Review (u2,p3,"Malisima","Las 2 peores horas de mi vida",3.5, Calendar.getInstance());
        Review r4 = new Review (u3,p4,"Mi favorita","Esta pelicula la voy a ver un millon de veces seguramente",9.5, Calendar.getInstance());

        listaReviewsAmigos.add(r1);
        listaReviewsAmigos.add(r2);
        listaReviewsAmigos.add(r3);
        listaReviewsAmigos.add(r4);

        rvAmigos = root.findViewById(R.id.rv_peliculas_amigos);
        peliculasAmigosAdapter = new PeliculaAmigoRecyclerAdapter(listaReviewsAmigos);
        rvAmigos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvAmigos.setHasFixedSize(true);
        rvAmigos.setAdapter(peliculasAmigosAdapter);

        rvReviews = root.findViewById(R.id.rv_reviews);
        reviewRecyclerAdapter = new ReviewRecyclerAdapter(listaReviewsAmigos);
        rvReviews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvReviews.setHasFixedSize(true);
        rvReviews.setAdapter(reviewRecyclerAdapter);

        btMap= root.findViewById(R.id.boton_maps);
        btMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MapsActivity.class);
                startActivity(i);
            }
        });

        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public PeliculasFragment() {
    }

    public static PeliculasFragment newInstance() {
        PeliculasFragment fragment = new PeliculasFragment();

        return fragment;
    }
}