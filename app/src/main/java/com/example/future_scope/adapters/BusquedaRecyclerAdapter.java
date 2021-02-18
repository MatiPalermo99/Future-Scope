package com.example.future_scope.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.future_scope.R;
import com.example.future_scope.api.MovieResults;
import com.example.future_scope.api.SearchResults;
import com.example.future_scope.model.Review;
import com.example.future_scope.model.User;
import com.example.future_scope.ui.ReviewActivity;
import com.example.future_scope.ui.SignInActivity;
import com.example.future_scope.ui.SignUpActivity;

import java.util.ArrayList;
import java.util.List;

public class BusquedaRecyclerAdapter extends RecyclerView.Adapter<BusquedaRecyclerAdapter.ResultadoBusquedaViewHolder> {


    private ArrayList<SearchResults.ResultsDTO> mDataset;
    private Activity context;
    private User user;

    public BusquedaRecyclerAdapter(ArrayList<SearchResults.ResultsDTO> myDataset,Activity context,User user) {
        this.user=user;
        this.mDataset = myDataset;
        this.context=context;
    }

    @Override
    public ResultadoBusquedaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_busqueda, parent, false);
        ResultadoBusquedaViewHolder vh = new ResultadoBusquedaViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ResultadoBusquedaViewHolder resultadoBusquedaViewHolder, int position) {

        resultadoBusquedaViewHolder.titulo.setTag(position);

        SearchResults.ResultsDTO resultsDTO = mDataset.get(position);
        resultadoBusquedaViewHolder.titulo.setText(resultsDTO.getTitle());

        resultadoBusquedaViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(resultsDTO);
                Intent i = new Intent(context, ReviewActivity.class);
                i.putExtra("usuario",user);
                i.putExtra("pelicula",resultsDTO);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class ResultadoBusquedaViewHolder extends RecyclerView.ViewHolder {

        CardView card;
        ImageView imgPelicula,fotoPerfil;
        TextView titulo,rating;

        public ResultadoBusquedaViewHolder(@NonNull View v) {
            super(v);
            card = v.findViewById(R.id.cardViewBusqueda);
            titulo = v.findViewById(R.id.titulo_busqueda);
        }
    }
}
