package com.example.barberapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberapp.Adapter.UsersAdapter_M;
import com.example.barberapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManagerShowUsers extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    UsersAdapter_M userAdapter;
    ArrayList<User>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_show_users);


        recyclerView = findViewById(R.id.recycler_view_users);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        userAdapter = new UsersAdapter_M(this,list);
        recyclerView.setAdapter(userAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);

                }
                userAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}