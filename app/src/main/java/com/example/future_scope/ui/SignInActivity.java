package com.example.future_scope.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.future_scope.MainActivity;
import com.example.future_scope.R;

public class SignInActivity extends AppCompatActivity {

    private EditText emailInicio,contraseniaInicio;
    private Button registrarse,iniciarSesion;
    int count = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signin);

        emailInicio=findViewById(R.id.email_inicio_sesion);
        contraseniaInicio=findViewById(R.id.contrasenia_inicio_sesion);
        registrarse=findViewById(R.id.inicio_sesion_a_registro);
        iniciarSesion=findViewById(R.id.aceptar_inicio);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
