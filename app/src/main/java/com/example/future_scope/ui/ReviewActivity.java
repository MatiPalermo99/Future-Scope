package com.example.future_scope.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.future_scope.MainActivity;
import com.example.future_scope.R;
import com.example.future_scope.api.SearchResults;
import com.example.future_scope.model.Review;
import com.example.future_scope.model.User;
import com.example.future_scope.room.AppRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.like.LikeButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ReviewActivity extends AppCompatActivity  implements AppRepository.OnResultCallback{

    private SearchResults.ResultsDTO pelicula;
    private TextView titulo,fechaReview;
    private LikeButton likeButton;
    private RatingBar ratingBar;
    private Button publicar;
    private User user;
    private MultiAutoCompleteTextView descripcion;
    private AppRepository reviewRoom;
    private Calendar fecha;
    private FirebaseUser userFB = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        reviewRoom = new AppRepository(this.getApplication(),this);
        fecha=Calendar.getInstance();

        pelicula=getIntent().getParcelableExtra("pelicula");
        //user=getIntent().getParcelableExtra("usuario");
        user=new User(userFB);

        titulo=findViewById(R.id.titulo_review_activity);
        titulo.setText("Review de "+pelicula.getTitle());

        likeButton=findViewById(R.id.star_button);
        ratingBar=findViewById(R.id.ratingbar);

        descripcion=findViewById(R.id.descripcion);
        fechaReview=findViewById(R.id.fecha_review);
        fechaReview.setText(new SimpleDateFormat("dd/MM/yyyy").format(fecha.getTime()));

        publicar=findViewById(R.id.publicar_review);
        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Review r = new Review(user.getUsername(),pelicula.getTitle(),descripcion.getText().toString(),ratingBar.getRating(), fecha,likeButton.isLiked());
                System.out.println(r);

                reviewRoom.insertarReview(r);
                reviewRoom.buscarReviews();

                Intent i = new Intent(ReviewActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onResultBusqueda(List result) {
        System.out.println("RESULTADO: "+result);
    }
}