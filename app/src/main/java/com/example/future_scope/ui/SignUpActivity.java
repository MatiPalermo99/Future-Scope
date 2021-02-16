package com.example.future_scope.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.future_scope.MainActivity;
import com.example.future_scope.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailRegistro,usuarioRegistro,contraseniaRegistro,repetirContraseniaRegistro;
    private Button registrarse;
    private FirebaseAuth fAuth;
    private Switch terminos, politicas;
    boolean bool_term = false;
    boolean bool_pol = false;

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
        terminos = findViewById(R.id.switch_terminos);
        politicas = findViewById(R.id.switch_politicas);

        fAuth = FirebaseAuth.getInstance();

        terminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    bool_term = true;
                }else{
                    bool_term = false;
                }
            }
        });

        politicas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    bool_pol = true;
                }else{
                    bool_pol = false;
                }
            }
        });

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailRegistro.getText().toString().trim();
                String password = contraseniaRegistro.getText().toString().trim();
                String confirmPassword = repetirContraseniaRegistro.getText().toString().trim();
                String username = usuarioRegistro.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailRegistro.setError("Email obligatorio.");
                    return;
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailRegistro.setError("Email inválido.");
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    contraseniaRegistro.setError("Contraseña obligatoria.");
                    return;
                }else if(password.length() < 8){
                    contraseniaRegistro.setError("Debe tener al menos 8 caracteres");
                    return;
                }

                if(TextUtils.isEmpty(username)){
                    usuarioRegistro.setError("Usuario obligatorio.");
                    return;
                }

                if(TextUtils.isEmpty(confirmPassword) || confirmPassword.compareTo(password) != 0){
                    repetirContraseniaRegistro.setError("Las contraseñas deben coincidir.");
                    return;
                }

                if(!bool_term){
                    terminos.setError("Requerido para continuar");
                    return;
                }
                if(!bool_pol){
                    politicas.setError("Requerido para continuar");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                        user.updateProfile(profileUpdates);
                        Toast.makeText(SignUpActivity.this, "Usuario creado con éxito", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpActivity.this, "Error: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
