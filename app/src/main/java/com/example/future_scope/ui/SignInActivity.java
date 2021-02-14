package com.example.future_scope.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.future_scope.MainActivity;
import com.example.future_scope.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    private EditText emailInicio,contraseniaInicio;
    private Button registrarse,iniciarSesion;
    private TextView olvidoContrasenia;
    int count = 0;
    private FirebaseAuth fAuth;
    private AlertDialog.Builder reset_alert;
    private LayoutInflater inflater;

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
        olvidoContrasenia=findViewById(R.id.olvidar_contrasenia);

        fAuth = FirebaseAuth.getInstance();

        reset_alert = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        inflater = this.getLayoutInflater();

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

                fAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(SignInActivity.this, "¡Bienvenidos al Himalaya!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignInActivity.this, "Error: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        olvidoContrasenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = inflater.inflate(R.layout.alert_dialog_forgot_password, null);
                reset_alert.setTitle("¿Desea recuperar su contraseña?")
                        .setMessage("Ingrese su email para obtener el link de recuperación.")
                        .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText emailResetPas = view.findViewById(R.id.editTextTextEmailAddress);
                                if(emailResetPas.getText().toString().isEmpty()){
                                    emailResetPas.setError("Email requerido.");
                                    return;
                                }
                                fAuth.sendPasswordResetEmail(emailResetPas.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(SignInActivity.this, "Email de recuperación enviado", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SignInActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).setNegativeButton("Cancelar", null)
                        .setView(view)
                        .create().show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*
        * DESCOMENTAR UNA VEZ ESTÉ IMPLEMENTADO EL BOTÓN DE CERRAR SESIÓN / LOGOUT
        *
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent i = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        *
        */
    }
}
