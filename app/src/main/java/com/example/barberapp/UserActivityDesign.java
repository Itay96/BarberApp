package com.example.barberapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivityDesign extends AppCompatActivity {


    TextView s1,s2,s3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_design);


/*        s1 = findViewById(R.id.appointmentButton);

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UserActivity.class);
                startActivity(intent);
            }
        });*/

    }
}