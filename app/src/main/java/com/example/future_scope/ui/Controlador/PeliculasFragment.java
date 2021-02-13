package com.example.future_scope.ui.Controlador;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.future_scope.MainActivity;
import com.example.future_scope.R;
import com.example.future_scope.adapters.PeliculaRecyclerAdapter;
import com.example.future_scope.model.Pelicula;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class PeliculasFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Pelicula> listaPeliculas =new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_peliculas, container, false);

        recyclerView = root.findViewById(R.id.recylcer_view);

        Pelicula pelicula = new Pelicula("La la land",2016,120,5.0);
        listaPeliculas.add(pelicula);
        pelicula = new Pelicula("John Wick",2014,125,4.0);
        listaPeliculas.add(pelicula);
        pelicula = new Pelicula("Mamma mia",2007,110,3.0);
        listaPeliculas.add(pelicula);
        pelicula = new Pelicula("Django",2014,130,2.0);
        listaPeliculas.add(pelicula);

        mAdapter = new PeliculaRecyclerAdapter(listaPeliculas,new MainActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        return inflater.inflate(R.layout.fragment_peliculas, container, false);
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