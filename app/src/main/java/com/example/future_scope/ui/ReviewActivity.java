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
import com.example.future_scope.model.Pelicula;
import com.example.future_scope.model.Review;
import com.example.future_scope.model.User;
import com.example.future_scope.room.AppDatabase;
import com.example.future_scope.room.AppRepository;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.Calendar;
import java.util.List;

public class ReviewActivity extends AppCompatActivity  implements AppRepository.OnResultCallback{

    private SearchResults.ResultsDTO pelicula;
    private TextView titulo;
    private LikeButton likeButton;
    private RatingBar ratingBar;
    private Button publicar;
    private User user;
    private MultiAutoCompleteTextView descripcion;
    private AppRepository platoRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        platoRoom = new AppRepository(this.getApplication(),this);

        pelicula=getIntent().getParcelableExtra("pelicula");
        user=getIntent().getParcelableExtra("usuario");

        titulo=findViewById(R.id.titulo_review_activity);
        titulo.setText("Review de "+pelicula.getTitle());

        likeButton=findViewById(R.id.star_button);
        ratingBar=findViewById(R.id.ratingbar);
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                System.out.println("ME GUSTA");
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                System.out.println("NO ME GUSTA");
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                System.out.println(rating);
            }
        });

        descripcion=findViewById(R.id.descripcion);

        publicar=findViewById(R.id.publicar_review);
        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Review r = new Review(user.getUsername(),pelicula.getTitle(),descripcion.getText().toString(),ratingBar.getRating(), Calendar.getInstance(),likeButton.isLiked());
                System.out.println(r);

                platoRoom.insertarReview(r);
                platoRoom.buscarReviews();

                Intent i = new Intent(ReviewActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });


    }

    @Override
    public void onResultBusqueda(List result) {
        System.out.println(result);
    }
}