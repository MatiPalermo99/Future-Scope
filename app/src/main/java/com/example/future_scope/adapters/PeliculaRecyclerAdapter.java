package com.example.future_scope.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.future_scope.R;
import com.example.future_scope.model.Pelicula;
import java.util.List;

public class PeliculaRecyclerAdapter extends RecyclerView.Adapter<PeliculaRecyclerAdapter.PeliculaViewHolder>{

    private List<Pelicula> mDataset;
    private AppCompatActivity activity;

    public PeliculaRecyclerAdapter(List<Pelicula> myDataset, AppCompatActivity act) {
        mDataset = myDataset;
        activity = act;
    }

    public class PeliculaViewHolder extends RecyclerView.ViewHolder {

        CardView card;
        ImageView imgPelicula;
        TextView titulo,rating,anio,duracion;

        public PeliculaViewHolder(@NonNull View v) {
            super(v);
            card = v.findViewById(R.id.cardView);
            imgPelicula = v.findViewById(R.id.imagen_pelicula);
            titulo = v.findViewById(R.id.titulo_pelicula);
            rating = v.findViewById(R.id.rating_pelicula);
            anio = v.findViewById(R.id.anio_pelicula);
            duracion = v.findViewById(R.id.duracion_pelicula);
        }
    }

    @NonNull
    @Override
    public PeliculaRecyclerAdapter.PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_pelicula, parent, false);
        PeliculaViewHolder  vh = new PeliculaViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaRecyclerAdapter.PeliculaViewHolder peliculaViewHolder, int position) {

        peliculaViewHolder.imgPelicula.setTag(position);
        peliculaViewHolder.titulo.setTag(position);
        peliculaViewHolder.rating.setTag(position);
        peliculaViewHolder.anio.setTag(position);
        peliculaViewHolder.duracion.setTag(position);


        Pelicula pelicula = mDataset.get(position);
            peliculaViewHolder.imgPelicula.setImageResource(R.drawable.carol);
            peliculaViewHolder.titulo.setText(pelicula.getTitulo());
            peliculaViewHolder.rating.setText("✰"+pelicula.getRating());
            peliculaViewHolder.anio.setText(pelicula.getAnio());
            peliculaViewHolder.duracion.setText(pelicula.getDuracionMin()+ "min.");
       }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }
