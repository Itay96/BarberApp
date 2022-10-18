package com.example.barberapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.github.muddz.styleabletoast.StyleableToast;

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout etEmail, etPassword;
    private FirebaseAuth userAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //email & password edit text
        etEmail =  findViewById(R.id.etEmail);///edit text email
        etPassword =  findViewById(R.id.etPassword);//edit text password
        Button forgotPassword = (Button) findViewById(R.id.BtnFPass);
        getSupportActionBar().setTitle("");

        //button login
        Button loginBtn = findViewById(R.id.BtnSignIn);
        Button signBtn =  findViewById(R.id.BtnSignUp);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users");
        userAuth = FirebaseAuth.getInstance();

        //onClickListener
        loginBtn.setOnClickListener(this);
        signBtn.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BtnSignUp:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.BtnSignIn:
                userLogin();
                break;
            case R.id.BtnFPass:
                startActivity(new Intent(this, UserForgotPassword.class));
                break;
        }
    }



    private void userLogin() {
        String email = etEmail.getEditText().getText().toString().trim();
        String password = etPassword.getEditText().getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            etPassword.setError("Min password length is 6 Characters");
            etPassword.requestFocus();
            return;
        }



        userAuth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        System.out.println(currentUser);
                        database.getReference().child("Users").child(currentUser.getUid()).child("userType").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                int userType = snapshot.getValue(Integer.class);
                                if (userType == 0 && currentUser.isEmailVerified()) {
                                    startActivity(new Intent(MainActivity.this, UserActivity.class));
                                }
                               else if (userType == 1) {
                                    startActivity(new Intent(MainActivity.this, ManagerActivity.class));
                                } else {
                                    currentUser.sendEmailVerification();
                                    StyleableToast.makeText(MainActivity.this, "Check your email to verify your account", Toast.LENGTH_SHORT,R.style.exampleToast).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
             }
          }

