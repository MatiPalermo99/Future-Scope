package com.example.future_scope.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.future_scope.R;
import com.example.future_scope.adapters.ListaRecyclerAdapter;
import com.example.future_scope.adapters.PeliculaAmigoRecyclerAdapter;
import com.example.future_scope.adapters.ReviewRecyclerAdapter;
import com.example.future_scope.model.Lista;
import com.example.future_scope.model.Pelicula;
import com.example.future_scope.model.Review;
import com.example.future_scope.model.User;
import com.example.future_scope.room.AppRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class ProfileFragment extends Fragment implements AppRepository.OnResultCallback {

    static final int GALERIA_REQUEST = 2;
    int images[] = {R.drawable.carol};
    int n= Math.abs((new Random().nextInt())%5);

    private RecyclerView rvPeliculas,rvReviews,rvListas;
    private List<Pelicula> listaPeliculasAux =new ArrayList<>();
    private List<Pelicula> lp2 =new ArrayList<>();

    private List<Review> listaPeliculas =new ArrayList<>();
    private List<Review> listaReviews =new ArrayList<>();
    private List<Lista> listaListas =new ArrayList<>();

    private PeliculaAmigoRecyclerAdapter peliculasAdapter;
    private ReviewRecyclerAdapter reviewAdapter;
    private ListaRecyclerAdapter listaRecyclerAdapter;

    private TextView nombre_usuario, descripcion_usuario;
    private ImageView foto_usuario, btConfig;

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private AlertDialog.Builder config_dialog;
    private LayoutInflater inflaterLayout;
    private AppRepository reviewRoom;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        reviewRoom = new AppRepository(getActivity().getApplication(),this);
        reviewRoom.buscarReviews();

        Pelicula p1 = new Pelicula("Birdman or (the unexpected virtue of ignorance)",2016,120,5.0);
        listaPeliculasAux.add(p1);
        Pelicula p2 = new Pelicula("John Wick",2014,125,4.0);
        listaPeliculasAux.add(p2);
        Pelicula p3 = new Pelicula("Mamma Mia",2007,110,3.0);
        listaPeliculasAux.add(p3);
        Pelicula p4 = new Pelicula("Django Unchained",2014,130,2.0);
        listaPeliculasAux.add(p4);

        lp2.add(p1);
        lp2.add(p2);
        lp2.add(p3);

        User u1 = new User("mauricio.toso@yahoo.com.ar","mtoso","12345678");
        User u2 = new User("micapierotti@gmail.com","mics","12345678");
        User u3 = new User("matipalermo@gmail.com","mati","12345678");

        Review r1 = new Review (u1.getUsername(),p1.getTitulo(),"Muy buena!","Increible pelicula me re gusto!",8.2, Calendar.getInstance());
        Review r2 = new Review (u2.getUsername(),p2.getTitulo(),"La mejor pelicula","Es imposible que esta pelicula no haya ganado ningun Oscar!",9.0, Calendar.getInstance());
        Review r3 = new Review (u2.getUsername(),p3.getTitulo(),"Malisima","Las 2 peores horas de mi vida",3.5, Calendar.getInstance());
        Review r4 = new Review (u3.getUsername(),p4.getTitulo(),"Mi favorita","Esta pelicula la voy a ver un millon de veces seguramente",9.5, Calendar.getInstance());

        listaReviews.add(r1);
        listaReviews.add(r2);
        listaReviews.add(r3);
        listaReviews.add(r4);

        Lista l1 = new Lista(user.getDisplayName(), (ArrayList<Pelicula>) listaPeliculasAux,"","Favoritas");
        Lista l2 = new Lista(user.getDisplayName(), (ArrayList<Pelicula>) lp2,"","Otras Favoritas");
        listaListas.add(l1);
        listaListas.add(l2);

        nombre_usuario = root.findViewById(R.id.nombre_usuario_perfil);
        foto_usuario = root.findViewById(R.id.foto_usuario_perfil);
        descripcion_usuario = root.findViewById(R.id.descripcion_perfil);

        if(checkSelfPermission(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(getActivity(), permissions, 200);
        }
        if(checkSelfPermission(getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(getActivity(), permissions, 200);
        }

        if(user != null){
            nombre_usuario.setText(user.getDisplayName());
            if(user.getPhotoUrl() != null){
                Uri photo = user.getPhotoUrl();
                foto_usuario.setImageURI(photo);
            }
        }

        rvPeliculas = root.findViewById(R.id.rv_vistas_perfil);
        peliculasAdapter = new PeliculaAmigoRecyclerAdapter(listaPeliculas,foto_usuario.getDrawable());
        rvPeliculas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvPeliculas.setHasFixedSize(true);
        rvPeliculas.setAdapter(peliculasAdapter);

        rvReviews = root.findViewById(R.id.rv_reviews_perfil);
        reviewAdapter = new ReviewRecyclerAdapter(listaReviews,foto_usuario.getDrawable());
        rvReviews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvReviews.setHasFixedSize(true);
        rvReviews.setAdapter(reviewAdapter);

        rvListas = root.findViewById(R.id.rv_listas_perfil);
        listaRecyclerAdapter = new ListaRecyclerAdapter(listaListas,foto_usuario.getDrawable());
        rvListas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvListas.setHasFixedSize(true);
        rvListas.setAdapter(listaRecyclerAdapter);

        config_dialog = new AlertDialog.Builder(this.getActivity(), R.style.AlertDialogCustom);
        inflaterLayout = this.getActivity().getLayoutInflater();
        btConfig = root.findViewById(R.id.config_button);

        btConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = inflaterLayout.inflate(R.layout.alert_dialog_config, null);

                Button btLogout = view.findViewById(R.id.bt_logout);
                Button btDescripcion = view.findViewById(R.id.descripcion);
                Button btFotoPerfil = view.findViewById(R.id.fotoPerfil);
                EditText textoDescripcion = view.findViewById(R.id.et_descripcion);

                btLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseAuth.getInstance().signOut();
                        Intent i = new Intent(view.getContext(), SignInActivity.class);
                        startActivity(i);
                        getActivity().finish();
                    }
                });
                btDescripcion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        descripcion_usuario.setText(textoDescripcion.getText());
                    }
                });
                btFotoPerfil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        abrirGaleria();
                    }
                });
                config_dialog
                        .setNegativeButton("Salir", null)
                        .setView(view)
                        .create().show();
            }
        });
        return root;
    }

    private void abrirGaleria() {
        Intent galeriaIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeriaIntent, GALERIA_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALERIA_REQUEST && resultCode == Activity.RESULT_OK) {
                //try {

            Uri imageUri = data.getData();
            foto_usuario.setImageURI(imageUri);

            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setPhotoUri(imageUri).build();
            user.updateProfile(profileUpdates);

            peliculasAdapter = new PeliculaAmigoRecyclerAdapter(listaPeliculas,foto_usuario.getDrawable());
            rvPeliculas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            rvPeliculas.setHasFixedSize(true);
            rvPeliculas.setAdapter(peliculasAdapter);

            reviewAdapter = new ReviewRecyclerAdapter(listaReviews,foto_usuario.getDrawable());
            rvReviews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            rvReviews.setHasFixedSize(true);
            rvReviews.setAdapter(reviewAdapter);

            listaRecyclerAdapter = new ListaRecyclerAdapter(listaListas,foto_usuario.getDrawable());
            rvListas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            rvListas.setHasFixedSize(true);
            rvListas.setAdapter(listaRecyclerAdapter);

                    /*InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    selectedImage.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    foto_usuario.setImageBitmap(selectedImage);*/

                /*} catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/
        }
    }

    @Override
    public void onResultBusqueda(List result) {
        listaPeliculas.clear();
        listaPeliculas.addAll(result);

        ArrayList<Review> lp = new ArrayList<>();
        for(Review r:listaPeliculas) if (r.getDescripcion().compareTo("")!=0) lp.add(r);
        listaReviews.clear();
        listaReviews.addAll(lp);

        Collections.reverse(listaPeliculas);
        Collections.reverse(listaReviews);

        reviewAdapter.notifyDataSetChanged();
        peliculasAdapter.notifyDataSetChanged();
    }
}
