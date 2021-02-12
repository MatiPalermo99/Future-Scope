package com.example.future_scope.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.future_scope.MainActivity;
import com.example.future_scope.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText emailInicio,contraseniaInicio;
    private Button registrarse,iniciarSesion;
    int count = 0;
    private FirebaseAuth fAuth;

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

        fAuth = FirebaseAuth.getInstance();

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
                String email = emailInicio.getText().toString().trim();
                String password = contraseniaInicio.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailInicio.setError("Email requerido.");
                    return;
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailInicio.setError("Email inválido.");
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    contraseniaInicio.setError("Contraseña requerida.");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignInActivity.this, "¡Bienvenidos al Himalaya!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(SignInActivity.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
