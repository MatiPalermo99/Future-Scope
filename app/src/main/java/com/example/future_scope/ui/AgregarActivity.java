package com.example.future_scope.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.future_scope.R;
import com.example.future_scope.adapters.BusquedaRecyclerAdapter;
import com.example.future_scope.adapters.PeliculaAmigoRecyclerAdapter;
import com.example.future_scope.adapters.PeliculaRecyclerAdapter;
import com.example.future_scope.api.AccionesDAO;
import com.example.future_scope.api.Details;
import com.example.future_scope.api.MovieResults;
import com.example.future_scope.api.PeliculaRepositoryRest;
import com.example.future_scope.api.SearchResults;
import com.example.future_scope.model.Pelicula;
import com.example.future_scope.model.User;

import java.util.ArrayList;

public class AgregarActivity extends AppCompatActivity {

    private RecyclerView rvBusqueda;
    private BusquedaRecyclerAdapter busquedaRecyclerAdapter;
    private ArrayList<SearchResults.ResultsDTO> resultados=new ArrayList<>();
    private PeliculaRepositoryRest peliculaRest;
    private Activity activity;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        activity=this;

        user=getIntent().getParcelableExtra("usuario");

        peliculaRest=new PeliculaRepositoryRest();

        rvBusqueda=findViewById(R.id.rv_busqueda);
    }

    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            AccionesDAO evento = AccionesDAO.valueOf(data.getString("accion"));
            switch (evento){
                case BUSCAR_TITULO:
                    ArrayList<SearchResults.ResultsDTO> p = data.getParcelableArrayList("peliculas_titulo");

                    busquedaRecyclerAdapter = new BusquedaRecyclerAdapter(p,activity,user);
                    rvBusqueda.setLayoutManager(new LinearLayoutManager(activity));
                    rvBusqueda.setHasFixedSize(true);
                    rvBusqueda.setAdapter(busquedaRecyclerAdapter);

                    break;
                case ERROR:
                    String error = data.getParcelable("error");
                    System.out.println("ERROR: "+error);
                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setIconified(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!newText.isEmpty())peliculaRest.buscarPeliculaTitulo(newText,miHandler);
                else{
                    resultados.clear();
                    busquedaRecyclerAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);

    }
}