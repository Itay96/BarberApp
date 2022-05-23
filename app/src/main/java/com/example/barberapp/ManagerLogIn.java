package com.example.barberapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManagerLogIn extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private Button logOut,settingsBtn,AddManagerAppointment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_log_in);

        logOut = (Button) findViewById(R.id.signOut);
        settingsBtn = (Button) findViewById(R.id.settings);
        AddManagerAppointment = (Button) findViewById(R.id.AddAppointment);



        //כפתור יציאה במסך של המשתמש
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ManagerLogIn.this, MainActivity.class));
            }
        });

        //כפתור מעבר קביעת תורים
        AddManagerAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ManagerAppointment.class);
                startActivity(intent);
            }
        });


    }
}