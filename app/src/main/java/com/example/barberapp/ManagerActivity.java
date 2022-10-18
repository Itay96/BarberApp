package com.example.barberapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barberapp.Model.Barber;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManagerActivity extends AppCompatActivity {

    private FirebaseUser manager;
    private DatabaseReference reference;
    private String MangerId;
    private Button settingsBtn,AddManagerAppointment,usersList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_log_in);


        Button logOut = (Button) findViewById(R.id.signOut);
        settingsBtn = (Button) findViewById(R.id.settings);
        AddManagerAppointment = (Button) findViewById(R.id.AddAppointment);
        usersList = (Button) findViewById(R.id.BtnShowUsers);


        //כפתור יציאה במסך של המנהל
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                FirebaseAuth.getInstance().signOut();
            }
        });

        //כפתור מעבר קביעת תורים
        AddManagerAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManagerCalenderAppointment.class);
                startActivity(intent);
            }
        });

        //כפתור מעבר להגדרות
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ManagerSettings.class);
                startActivity(intent);
            }
        });

        usersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ManagerShowUsers.class);
                startActivity(intent);
            }
        });




        //חיבור לדאטה בייס לקבלת נתונים על המשתמש שמתחבר והצגתם על המסך
        manager = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        MangerId = manager.getUid();

        final TextView fullNameTextView = (TextView) findViewById(R.id.fullName);

       reference.child(MangerId).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Barber managerProfile = snapshot.getValue(Barber.class);
                if(managerProfile != null){
                    String fullName = managerProfile.getName();
                    fullNameTextView.setTextColor(getColor(R.color.black));
                    fullNameTextView.setText(fullName + "!");

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ManagerActivity.this, "Something wrong happened !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed()
    {

        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(ManagerActivity.this);
        // Set the message show for the Alert time
        builder.setMessage("אתה בטוח שברצונך לצאת ?");
        // Set Alert Title
        builder.setTitle("BarberApp");
        // Set Cancelable false
        builder.setCancelable(false);
        // Set the positive button with yes name
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // When the user click yes button
                // then app will close
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