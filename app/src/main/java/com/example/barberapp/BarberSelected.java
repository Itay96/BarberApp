package com.example.barberapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barberapp.Model.Barber;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BarberSelected extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    Button BtnNext;
    Spinner spinner;
    String userID;
    ArrayList<Barber> barbers;
    ArrayList<String> barberName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_selected);

        BtnNext = findViewById(R.id.BtnNextBarber);
        spinner = findViewById(R.id.spinner);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();

        barberName = new ArrayList<>();
        barbers = new ArrayList<>();




        BtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTimeSlotFromBarber();
                onChooseBarber();
            }
        });

        //snapshot for choose a barber for haircut and show times
        databaseReference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if(((long)dataSnapshot.child("userType").getValue()) == 1) {
                        Barber barber = new Barber(dataSnapshot.child("name").getValue(String.class),
                                dataSnapshot.child("email").getValue(String.class),
                                dataSnapshot.getKey());
                        barbers.add(barber);
                        barberName.add(dataSnapshot.child("name").getValue(String.class));
                    }
                     ArrayAdapter<String> adapter = new ArrayAdapter<>(BarberSelected.this,R.layout.style_spinner,barberName);
                     spinner.setAdapter(adapter);
                }

                Intent intent = getIntent();
                getIntent().putExtra("name",userID);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BarberSelected.this,error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getTimeSlotFromBarber(){
        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();


    }

    public void onChooseBarber()
    {
        // AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(BarberSelected.this);
        // Set the message show for the Alert time
        builder.setMessage("לבחירת הספר בחר YES ");
        // Set Alert Title
        builder.setTitle("BarberApp");
        // Set Cancelable false
        builder.setIcon(getDrawable(R.drawable.barberphoto));
        builder.setCancelable(false);
        // Set the positive button with yes name
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // When the user click yes button
                // then app will close
                Intent intent = new Intent(BarberSelected.this,AppointmentActivity.class);
                startActivity(intent);
                finish();

            }
        });
        // Set the Negative button with No name
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // If user click no
                // then dialog box is canceled.
                dialog.cancel();
            }
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }

}