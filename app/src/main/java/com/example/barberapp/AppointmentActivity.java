package com.example.barberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AppointmentActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    String s1[],s2[];
    int images[] = {R.drawable.backarrow,R.drawable.barber,R.drawable.barber2jpg,R.drawable.barber3,R.drawable.barberphoto,
            R.drawable.email,R.drawable.facebook,R.drawable.instegram};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        recyclerView = findViewById(R.id.recycleview);

        s1 = getResources().getStringArray(R.array.barber_time);
        s2 = getResources().getStringArray(R.array.description);


        MyAdapter myAdapter = new MyAdapter(this,s1,s2,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}