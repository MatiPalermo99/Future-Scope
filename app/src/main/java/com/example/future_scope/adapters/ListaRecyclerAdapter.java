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
import com.example.future_scope.model.Lista;
import com.example.future_scope.model.Review;

import java.util.List;

public class ListaRecyclerAdapter extends RecyclerView.Adapter<ListaRecyclerAdapter.ListaViewHolder>{

    private List<Lista> mDataset;

    public ListaRecyclerAdapter(List<Lista> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_listas, null, false);
        ListaViewHolder vh = new ListaViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ListaViewHolder listaViewHolder, int position) {

        listaViewHolder.tituloLista.setTag(position);
        listaViewHolder.nombreLista.setTag(position);
        listaViewHolder.fotoPerfil.setTag(position);

        listaViewHolder.tituloPelicula1.setTag(position);
        listaViewHolder.tituloPelicula2.setTag(position);
        listaViewHolder.tituloPelicula3.setTag(position);
        listaViewHolder.tituloPelicula4.setTag(position);

        listaViewHolder.fotoPelicula1.setTag(position);
        listaViewHolder.fotoPelicula2.setTag(position);
        listaViewHolder.fotoPelicula3.setTag(position);
        listaViewHolder.fotoPelicula4.setTag(position);

        Lista lista = mDataset.get(position);

        listaViewHolder.fotoPerfil.setImageResource(R.drawable.carol);
        listaViewHolder.tituloLista.setText(lista.getNombreLista());
        listaViewHolder.nombreLista.setText(lista.getPropietarioLista().getUsername());

        if(lista.getListaPeliculas().size()>0) listaViewHolder.tituloPelicula1.setText(lista.getListaPeliculas().get(0).getTitulo());
        if(lista.getListaPeliculas().size()>1) listaViewHolder.tituloPelicula2.setText(lista.getListaPeliculas().get(1).getTitulo());
        if(lista.getListaPeliculas().size()>2) listaViewHolder.tituloPelicula3.setText(lista.getListaPeliculas().get(2).getTitulo());
        if(lista.getListaPeliculas().size()>3) listaViewHolder.tituloPelicula4.setText(lista.getListaPeliculas().get(3).getTitulo());

        if(lista.getListaPeliculas().size()>0) listaViewHolder.fotoPelicula1.setImageResource(R.drawable.carol);
        if(lista.getListaPeliculas().size()>1) listaViewHolder.fotoPelicula2.setImageResource(R.drawable.carol);
        if(lista.getListaPeliculas().size()>2) listaViewHolder.fotoPelicula3.setImageResource(R.drawable.carol);
        if(lista.getListaPeliculas().size()>3) listaViewHolder.fotoPelicula4.setImageResource(R.drawable.carol);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ListaViewHolder extends RecyclerView.ViewHolder {

        CardView card;
        ImageView fotoPerfil,fotoPelicula1,fotoPelicula2,fotoPelicula3,fotoPelicula4;
        TextView tituloLista, tituloPelicula1, tituloPelicula2, tituloPelicula3, tituloPelicula4,nombreLista;

        public ListaViewHolder(@NonNull View v) {
            super(v);
            card = v.findViewById(R.id.cardViewListas);
            fotoPerfil = v.findViewById(R.id.foto_usuario_lista);
            tituloLista = v.findViewById(R.id.titulo_lista_perfil);

            fotoPelicula1 = v.findViewById(R.id.foto_p1_lista_perfil);
            fotoPelicula2 = v.findViewById(R.id.foto_p2_lista_perfil);
            fotoPelicula3 = v.findViewById(R.id.foto_p3_lista_perfil);
            fotoPelicula4 = v.findViewById(R.id.foto_p4_lista_perfil);

            tituloPelicula1 = v.findViewById(R.id.titulo_p1_lista_perfil);
            tituloPelicula2 = v.findViewById(R.id.titulo_p2_lista_perfil);
            tituloPelicula3 = v.findViewById(R.id.titulo_p3_lista_perfil);
            tituloPelicula4 = v.findViewById(R.id.titulo_p4_lista_perfil);

            nombreLista = v.findViewById(R.id.nombre_lista);
        }
    }
}
