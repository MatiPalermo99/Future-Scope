package com.example.future_scope.ui;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.future_scope.MainActivity;
import com.example.future_scope.R;
import com.example.future_scope.adapters.ListaRecyclerAdapter;
import com.example.future_scope.adapters.PeliculaAmigoRecyclerAdapter;
import com.example.future_scope.adapters.PeliculaRecyclerAdapter;
import com.example.future_scope.adapters.ReviewRecyclerAdapter;
import com.example.future_scope.model.Lista;
import com.example.future_scope.model.Pelicula;
import com.example.future_scope.model.Review;
import com.example.future_scope.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class ProfileFragment extends Fragment {

    static final int GALERIA_REQUEST = 2;
    int images[] = {R.drawable.carol};
    int n= Math.abs((new Random().nextInt())%5);

    private RecyclerView rvAmigos,rvReviews,rvListas;
    private List<Pelicula> listaPeliculas =new ArrayList<>();
    private List<Pelicula> lp2 =new ArrayList<>();

    private List<Review> listaReviewsAmigos =new ArrayList<>();
    private List<Lista> listaListas =new ArrayList<>();

    private PeliculaAmigoRecyclerAdapter peliculasAmigosAdapter;
    private ReviewRecyclerAdapter reviewRecyclerAdapter;
    private ListaRecyclerAdapter listaRecyclerAdapter;

    private TextView nombre_usuario, descripcion_usuario;
    private ImageView foto_usuario, btConfig;

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private AlertDialog.Builder config_dialog;
    private LayoutInflater inflaterLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        Pelicula p1 = new Pelicula("Birdman or (the unexpected virtue of ignorance)",2016,120,5.0);
        listaPeliculas.add(p1);
        Pelicula p2 = new Pelicula("John Wick",2014,125,4.0);
        listaPeliculas.add(p2);
        Pelicula p3 = new Pelicula("Mamma Mia",2007,110,3.0);
        listaPeliculas.add(p3);
        Pelicula p4 = new Pelicula("Django Unchained",2014,130,2.0);
        listaPeliculas.add(p4);

        lp2.add(p1);
        lp2.add(p2);
        lp2.add(p3);

        User u1 = new User("mauricio.toso@yahoo.com.ar","mtoso","12345678");
        User u2 = new User("micapierotti@gmail.com","mics","12345678");
        User u3 = new User("matipalermo@gmail.com","mati","12345678");

        Review r1 = new Review (u1,p1,"Muy buena!","Increible pelicula me re gusto!",8.2, Calendar.getInstance());
        Review r2 = new Review (u2,p2,"La mejor pelicula","Es imposible que esta pelicula no haya ganado ningun Oscar!",9.0, Calendar.getInstance());
        Review r3 = new Review (u2,p3,"Malisima","Las 2 peores horas de mi vida",3.5, Calendar.getInstance());
        Review r4 = new Review (u3,p4,"Mi favorita","Esta pelicula la voy a ver un millon de veces seguramente",9.5, Calendar.getInstance());

        listaReviewsAmigos.add(r1);
        listaReviewsAmigos.add(r2);
        listaReviewsAmigos.add(r3);
        listaReviewsAmigos.add(r4);

        rvAmigos = root.findViewById(R.id.rv_vistas_perfil);
        peliculasAmigosAdapter = new PeliculaAmigoRecyclerAdapter(listaReviewsAmigos);
        rvAmigos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvAmigos.setHasFixedSize(true);
        rvAmigos.setAdapter(peliculasAmigosAdapter);

        rvReviews = root.findViewById(R.id.rv_reviews_perfil);
        reviewRecyclerAdapter = new ReviewRecyclerAdapter(listaReviewsAmigos);
        rvReviews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvReviews.setHasFixedSize(true);
        rvReviews.setAdapter(reviewRecyclerAdapter);

        Lista l1 = new Lista(u1, (ArrayList<Pelicula>) listaPeliculas,"","Favoritas");
        Lista l2 = new Lista(u1, (ArrayList<Pelicula>) lp2,"","Otras Favoritas");
        listaListas.add(l1);
        listaListas.add(l2);

        rvListas = root.findViewById(R.id.rv_listas_perfil);
        listaRecyclerAdapter = new ListaRecyclerAdapter(listaListas);
        rvListas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvListas.setHasFixedSize(true);
        rvListas.setAdapter(listaRecyclerAdapter);

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
}
