package com.example.barberapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class UserForgotPassword extends AppCompatActivity {

    private EditText emailEt;
    private Button resetPasswordBtn;

    FirebaseAuth Passauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_forgot_password);

        emailEt = (EditText) findViewById(R.id.email);
        resetPasswordBtn = (Button) findViewById(R.id.ResetPassword);

        Passauth = FirebaseAuth.getInstance();

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }

            private void resetPassword() {
                String email = emailEt.getText().toString().trim();

                if (email.isEmpty()){
                    emailEt.setError("Email is required");
                    emailEt.requestFocus();
                    return;
                }

               /* if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailEt.setError("Please provide valid email");
                    emailEt.requestFocus();
                    return;
                }*/

                Passauth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(UserForgotPassword.this, "Check your email", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(UserForgotPassword.this, "Try again, Something wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

    }
}