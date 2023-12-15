package com.example.locationvoiture;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity {
    FirebaseAuth mAuth;
EditText usernametext;
EditText emailtext;
EditText passwordtext;
Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);
        usernametext=findViewById(R.id.username);
        emailtext=findViewById(R.id.Email);
        passwordtext=findViewById(R.id.password);
        register=findViewById(R.id.Register_button);
        mAuth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username,email,password;
                username=usernametext.getText().toString();
                email=emailtext.getText().toString();
                password=String.valueOf(passwordtext.getText());
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this,"Email is empty",Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(Register.this,"Username is empty",Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this,"Password is empty",Toast.LENGTH_SHORT).show();
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(Register.this, Home.class);
                                    startActivity(intent);

                                } else {


                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }
}