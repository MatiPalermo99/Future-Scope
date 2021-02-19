package com.example.future_scope.adapters;

import android.graphics.drawable.Drawable;
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
    private Drawable foto;

    public PeliculaAmigoRecyclerAdapter(List<Review> myDataset, Drawable foto) {
        mDataset = myDataset;
        this.foto=foto;
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
        peliculaAmigoViewHolder.fav.setTag(position);

        Review review = mDataset.get(position);
        peliculaAmigoViewHolder.fotoPerfil.setImageDrawable(foto);
        peliculaAmigoViewHolder.imgPelicula.setImageResource(R.drawable.carol);
        peliculaAmigoViewHolder.titulo.setText(review.getPelicula());
        peliculaAmigoViewHolder.rating.setText(""+review.getRating());

        if (review.isFavorito()){
            ViewGroup.LayoutParams params = peliculaAmigoViewHolder.rating.getLayoutParams();
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            peliculaAmigoViewHolder.rating.setLayoutParams(params);
        }else{
            ViewGroup.LayoutParams params = peliculaAmigoViewHolder.rating.getLayoutParams();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            peliculaAmigoViewHolder.rating.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class PeliculaAmigoViewHolder extends RecyclerView.ViewHolder {

        CardView card;
        ImageView imgPelicula,fotoPerfil,fav;
        TextView titulo,rating;

        public PeliculaAmigoViewHolder(@NonNull View v) {
            super(v);
            card = v.findViewById(R.id.cardViewPeliculasAmigos);
            fotoPerfil = v.findViewById(R.id.foto_usuario_home);
            imgPelicula = v.findViewById(R.id.imagen_pelicula_amigo);
            titulo = v.findViewById(R.id.titulo_pelicula_amigo);
            rating = v.findViewById(R.id.rating_usuario);
            fav= v.findViewById(R.id.fav_home);
        }
    }
}
