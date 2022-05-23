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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView forgotPassword;
    private MaterialButton signBtn, loginBtn, ButtonManager;
    private EditText etEmail, etPassword;
    private FirebaseAuth mAuth;
    DatabaseReference managerReferance;
    FirebaseAuth managerAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //email & password edit text
        etEmail = (EditText) findViewById(R.id.etEmail);//edit text password
        etPassword = (EditText) findViewById(R.id.etPassword);//edit text email
        forgotPassword = (TextView) findViewById(R.id.forgotpass);
        getSupportActionBar().setTitle(" ");
        //button login
        MaterialButton loginBtn = (MaterialButton) findViewById(R.id.BtnLogin);
        MaterialButton signBtn = (MaterialButton) findViewById(R.id.BtnsignUp);
        MaterialButton ButtonManager = (MaterialButton) findViewById(R.id.BtnManager);

        signBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        ButtonManager.setOnClickListener(this);


        // FirebaseDatabase database = FirebaseDatabase.getInstance();
        // DatabaseReference managerReferance = database.getReference("Managers");

        mAuth = FirebaseAuth.getInstance();
        managerAuth = FirebaseAuth.getInstance();
        managerReferance = FirebaseDatabase.getInstance().getReference("Managers");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BtnsignUp:
                startActivity(new Intent(this, SignUpActivity.class));
                break;

            case R.id.BtnLogin:
                userLogin();
                break;

            case R.id.forgotpass:
                startActivity(new Intent(this, UserForgotPassword.class));
                break;

            case R.id.BtnManager:
                managerLogin();
                //  startActivity(new Intent(this,ManagerLogIn.class));
                break;
        }
    }

    private void managerLogin() {

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }

      /*  if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return;
        }*/

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

        managerAuth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            managerReferance.child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).
                                    addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                Toast.makeText(MainActivity.this, "Login Manager", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(MainActivity.this, ManagerLogIn.class);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(MainActivity.this, "Manager Not Exist", Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                        }
                    }
                });

    }


    private void userLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }

      /*  if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return;
        }*/

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


        mAuth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            if (user.isEmailVerified()) {
                                startActivity(new Intent(MainActivity.this, UserActivity.class));
                                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                            } else {
                                user.sendEmailVerification();
                                Toast.makeText(MainActivity.this, "Check your email to verify your account", Toast.LENGTH_SHORT).show();

                    }
                  }else{
                    Toast.makeText(MainActivity.this, "Failed ! , Please check your Pass & Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

