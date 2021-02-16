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
import com.example.future_scope.model.Review;

import java.util.List;

public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewRecyclerAdapter.ReviewViewHolder>{

    private List<Review> mDataset;

    public ReviewRecyclerAdapter(List<Review> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_review, null, false);
        ReviewViewHolder vh = new ReviewViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder reviewViewHolder, int position) {

        reviewViewHolder.tituloReview.setTag(position);
        reviewViewHolder.tituloPelicula.setTag(position);
        reviewViewHolder.rating.setTag(position);
        reviewViewHolder.descripcion.setTag(position);
        reviewViewHolder.fotoPerfil.setTag(position);
        reviewViewHolder.nombre.setTag(position);

        Review review = mDataset.get(position);
        reviewViewHolder.fotoPerfil.setImageResource(R.drawable.carol);
        reviewViewHolder.tituloPelicula.setText(review.getPelicula().getTitulo());
        reviewViewHolder.tituloReview.setText(review.getTituloReview());
        reviewViewHolder.descripcion.setText(review.getDescripcion());
        reviewViewHolder.rating.setText(""+review.getRating());
        reviewViewHolder.nombre.setText(""+review.getUsuario().getUsername());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        CardView card;
        ImageView fotoPerfil;
        TextView tituloPelicula, tituloReview,descripcion,rating,nombre;

        public ReviewViewHolder(@NonNull View v) {
            super(v);
            card = v.findViewById(R.id.cardViewPeliculasReview);
            fotoPerfil = v.findViewById(R.id.foto_usuario_review);
            tituloPelicula = v.findViewById(R.id.titulo_pelicula_review);
            tituloReview = v.findViewById(R.id.titulo_review);
            descripcion = v.findViewById(R.id.descripcion_review);
            rating = v.findViewById(R.id.rating_usuario_review);
            nombre = v.findViewById(R.id.nombre_review);

        }
    }
}
