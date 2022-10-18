package com.example.barberapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barberapp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout tlfullname,tlemail,tlpassword,tlphoneNo;
    Button Register,back;
    private FirebaseAuth mAuth;
    DatabaseReference myRef;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tlfullname = findViewById(R.id.fullName);
        tlemail = findViewById(R.id.email);
        tlpassword = findViewById(R.id.password);
        tlphoneNo = findViewById(R.id.phoneNo);
        Register = findViewById(R.id.register);
        back = findViewById(R.id.back);

                   ///////////חיבור ל-FireBase/////
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        myRef = database.getReference("Users");

        //בעת לחיצה על כפתור ה-SignUp מתבצעת קריאה לפונקציית RegisterUser
        Register.setOnClickListener(view -> RegisterUser());
        //כפתור חזרה למסך הראשי ממסך ההרשמה
        back.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
    //מתודה שמבצעת התחברות ושמירת נתונים לדאטה בייס
    private void RegisterUser() {

       final String name = tlfullname.getEditText().getText().toString();
       final String email = tlemail.getEditText().getText().toString();
       final String password = tlpassword.getEditText().getText().toString();
       final String phoneNo = tlphoneNo.getEditText().getText().toString();
       final String userType;

        //בדיקות על השם משתמש,סיסמא ואימייל
        if(name.isEmpty()){
            tlfullname.setError("Require Full Name !");
            tlfullname.requestFocus();
            return;
        }

        if (email.isEmpty()){
            tlemail.setError("Email valid is require !");
            tlemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            tlemail.setError("Please provide valid email");
            tlemail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            tlpassword.setError("Password is required");
            tlpassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            tlpassword.setError("Password must have 6 Characters");
            tlpassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                myRef = database.getReference("Users");
                if (task.isSuccessful()) {
                    User user = new User(name, email,phoneNo,0);
                    FirebaseDatabase.getInstance().getReference("Users").
                            child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "User Add !", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Something Wrong, try again !", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    }else{
                        Toast.makeText(getApplicationContext(), "Something Wrong, try again !", Toast.LENGTH_LONG).show();
                }
            }
        });

      }
   }
