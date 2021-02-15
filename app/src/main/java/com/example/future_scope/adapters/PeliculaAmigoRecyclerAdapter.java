package com.example.future_scope.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.future_scope.R;
import com.example.future_scope.model.Pelicula;
import com.example.future_scope.model.Review;

import java.util.List;

public class PeliculaAmigoRecyclerAdapter extends RecyclerView.Adapter<PeliculaAmigoRecyclerAdapter.PeliculaAmigoViewHolder>{

    private List<Review> mDataset;

    public PeliculaAmigoRecyclerAdapter(List<Review> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public PeliculaAmigoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_pelicula_amigos, null, false);
        PeliculaAmigoViewHolder vh = new PeliculaAmigoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PeliculaAmigoViewHolder peliculaAmigoViewHolder, int position) {

        peliculaAmigoViewHolder.imgPelicula.setTag(position);
        peliculaAmigoViewHolder.titulo.setTag(position);
        peliculaAmigoViewHolder.fotoPerfil.setTag(position);
        peliculaAmigoViewHolder.rating.setTag(position);

        Review review = mDataset.get(position);
        peliculaAmigoViewHolder.fotoPerfil.setImageResource(R.drawable.carol);
        peliculaAmigoViewHolder.imgPelicula.setImageResource(R.drawable.carol);
        peliculaAmigoViewHolder.titulo.setText(review.getPelicula().getTitulo());
        peliculaAmigoViewHolder.rating.setText(""+review.getRating());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class PeliculaAmigoViewHolder extends RecyclerView.ViewHolder {

        CardView card;
        ImageView imgPelicula,fotoPerfil;
        TextView titulo,rating;

        public PeliculaAmigoViewHolder(@NonNull View v) {
            super(v);
            card = v.findViewById(R.id.cardViewPeliculasAmigos);
            fotoPerfil = v.findViewById(R.id.foto_usuario_home);
            imgPelicula = v.findViewById(R.id.imagen_pelicula_amigo);
            titulo = v.findViewById(R.id.titulo_pelicula_amigo);
            rating = v.findViewById(R.id.rating_usuario);
        }
    }
}
