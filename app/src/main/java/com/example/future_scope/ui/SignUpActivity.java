package com.example.future_scope.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.future_scope.MainActivity;
import com.example.future_scope.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailRegistro,usuarioRegistro,contraseniaRegistro,repetirContraseniaRegistro;
    private Button registrarse;

    @SuppressLint({"ClickableViewAccessibility", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        emailRegistro = findViewById(R.id.email_registro);
        usuarioRegistro = findViewById(R.id.usuario_registro);
        contraseniaRegistro = findViewById(R.id.contrasenia_registro);
        repetirContraseniaRegistro = findViewById(R.id.repetir_contrasenia_registro);
        registrarse=findViewById(R.id.aceptar_registro);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });
    }
}
