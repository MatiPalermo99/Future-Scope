package com.example.future_scope.ui.Controlador;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.future_scope.MainActivity;
import com.example.future_scope.MapsActivity;
import com.example.future_scope.R;
import com.example.future_scope.adapters.PeliculaAmigoRecyclerAdapter;
import com.example.future_scope.adapters.PeliculaRecyclerAdapter;
import com.example.future_scope.adapters.ReviewRecyclerAdapter;
import com.example.future_scope.api.AccionesDAO;
import com.example.future_scope.api.Details;
import com.example.future_scope.api.MovieResults;
import com.example.future_scope.api.PeliculaRepositoryRest;
import com.example.future_scope.model.Pelicula;
import com.example.future_scope.model.Review;
import com.example.future_scope.model.User;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PeliculasFragment extends Fragment{

    private RecyclerView rvPopulares,rvAmigos,rvReviews;
    private List<Pelicula> listaPeliculas =new ArrayList<>();
    private List<Review> listaReviewsAmigos =new ArrayList<>();
    private ArrayList<MovieResults.ResultsDTO> populares = new ArrayList<>();
    private ArrayList<Pelicula> popularesFinal = new ArrayList<>();

    private PeliculaRecyclerAdapter peliculasPopularesAdapter;
    private PeliculaAmigoRecyclerAdapter peliculasAmigosAdapter;
    private ReviewRecyclerAdapter reviewRecyclerAdapter;

    private Button btMap;

    private PeliculaRepositoryRest peliculaRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_peliculas, container, false);

        //Persistencia con Retrofit
        peliculaRest=new PeliculaRepositoryRest();

        Pelicula p1 = new Pelicula("Birdman or (the unexpected virtue of ignorance)",2016,120,5.0);
        listaPeliculas.add(p1);
        Pelicula p2 = new Pelicula("John Wick",2014,125,4.0);
        listaPeliculas.add(p2);
        Pelicula p3 = new Pelicula("Mamma Mia",2007,110,3.0);
        listaPeliculas.add(p3);
        Pelicula p4 = new Pelicula("Django Unchained",2014,130,2.0);
        listaPeliculas.add(p4);

        MovieResults.ResultsDTO pp1 = new MovieResults.ResultsDTO("Birdman or (the unexpected virtue of ignorance)","2016",120.0,5.0);
        MovieResults.ResultsDTO pp2 = new MovieResults.ResultsDTO("John Wick","2014",125.0,4.0);
        MovieResults.ResultsDTO pp3 = new MovieResults.ResultsDTO("Mamma Mia","2007",110.0,3.0);
        MovieResults.ResultsDTO pp4 = new MovieResults.ResultsDTO("Django Unchained","2014",130.0,2.0);

        rvPopulares = root.findViewById(R.id.rv_peliculas_populares);

        User u1 = new User("mauricio.toso@yahoo.com.ar","mtoso","12345678");
        User u2 = new User("micapierotti@gmail.com","mics","12345678");
        User u3 = new User("matipalermo@gmail.com","mati","12345678");

        Review r1 = new Review (u1.getUsername(),p1.getTitulo(),"Muy buena!","Increible pelicula me re gusto!",8.2, Calendar.getInstance());
        Review r2 = new Review (u2.getUsername(),p2.getTitulo(),"La mejor pelicula","Es imposible que esta pelicula no haya ganado ningun Oscar!",9.0, Calendar.getInstance());
        Review r3 = new Review (u2.getUsername(),p3.getTitulo(),"Malisima","Las 2 peores horas de mi vida",3.5, Calendar.getInstance());
        Review r4 = new Review (u3.getUsername(),p4.getTitulo(),"Mi favorita","Esta pelicula la voy a ver un millon de veces seguramente",9.5, Calendar.getInstance());

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

        peliculaRest.listarPeliculas(miHandler);
        peliculaRest.buscarPeliculaTitulo("bird",miHandler);

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

    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            AccionesDAO evento = AccionesDAO.valueOf(data.getString("accion"));
            switch (evento){
                case BUSCAR_ID:
                    Details d = data.getParcelable("pelicula");
                    if(d!=null){
                        String genero="";
                        for(Details.GenresDTO g: d.getGenres()) genero=genero+g.getName()+"/";
                        genero=genero.substring(0,genero.length()-1);

                        popularesFinal.add(new Pelicula(d.getTitle(),d.getRuntime(),genero));

                        peliculasPopularesAdapter = new PeliculaRecyclerAdapter(popularesFinal);
                        rvPopulares.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                        rvPopulares.setHasFixedSize(true);
                        rvPopulares.setAdapter(peliculasPopularesAdapter);
                    }
                    break;
                case LISTAR_PELICULAS:
                    ArrayList<MovieResults.ResultsDTO> p = data.getParcelableArrayList("peliculas");
                    if(p!=null)buscarPeliculas(p);
                    break;
                case ERROR:
                    String error = data.getParcelable("error");
                    System.out.println("ERROR: "+error);
                    break;
            }
        }
    };

    private void buscarPeliculas(ArrayList<MovieResults.ResultsDTO> lista){

        for(MovieResults.ResultsDTO p:lista){
            peliculaRest.buscarPeliculaID(p.getId().toString(),miHandler);
        }
    }
}