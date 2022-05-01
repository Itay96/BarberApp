package com.example.barberapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialButton signBtn, loginBtn;
    private EditText etEmail, etPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //email & password edit text
        etEmail = (EditText) findViewById(R.id.etEmail);//edit text password
        etPassword = (EditText) findViewById(R.id.etPassword);//edit text email

        //button login
        MaterialButton loginBtn = (MaterialButton) findViewById(R.id.BtnLogin);
        MaterialButton signBtn = (MaterialButton) findViewById(R.id.BtnsignUp);

        signBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.BtnsignUp:
               startActivity(new Intent(this, SignUpActivity.class));
               break;

            case R.id.BtnLogin:
               userLogin();
        }
    }

    private void userLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(email.isEmpty()){
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }

      /*  if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return;
        }*/

        if(password.isEmpty()){
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            etPassword.setError("Min password length is 6 Characters");
            etPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, UserActivity.class));
                    Toast.makeText(MainActivity.this, "Hello my friend", Toast.LENGTH_SHORT).show();
               }else{
                    Toast.makeText(MainActivity.this, "Failed ! , Please check your Pass & Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}