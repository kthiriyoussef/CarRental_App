package com.example.locationvoiture;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    EditText emailtext;
    FirebaseAuth mAuth;
    EditText passwordtext;
    Button register;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        mAuth=FirebaseAuth.getInstance();
        t1=findViewById(R.id.signintext);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the new activity
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        emailtext=findViewById(R.id.email);
        passwordtext=findViewById(R.id.passwordt);
        register=findViewById(R.id.login_button);
        mAuth= FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;

                email=emailtext.getText().toString();
                password=String.valueOf(passwordtext.getText());
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this,"Email is empty",Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this,"Password is empty",Toast.LENGTH_SHORT).show();
                }
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {


                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String uid = user.getUid();
                                    Toast.makeText(Login.this, "Authentication sucess."+uid,
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, Home.class);
                                    startActivity(intent);
                                } else {


                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }
    }


