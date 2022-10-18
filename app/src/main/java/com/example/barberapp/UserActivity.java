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

import com.example.barberapp.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserActivity extends AppCompatActivity {

    private DatabaseReference reference;
    private FirebaseUser user;
    private String userID;
    private Button logOut;
    private TextView settingsBtn;
    private TextView appointmentBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        logOut = (Button) findViewById(R.id.logOut);
        settingsBtn = (TextView) findViewById(R.id.settingsButton);
         appointmentBtn = (TextView) findViewById(R.id.appointmentButton);
        TextView appointmentList = (TextView) findViewById(R.id.AppointmentList);



       //כפתור יציאה במסך של המשתמש
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                FirebaseAuth.getInstance().signOut();
            }
        });
        //כפתור מעבר למסך הגדרות
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UserSettings.class);
                startActivity(intent);
            }
        });

        //כפתור מעבר קביעת תורים
        appointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),BarberSelected.class);
                startActivity(intent);
            }
        });

        appointmentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UserAppointmentList.class);
                startActivity(intent);
            }
        });

        //חיבור לדאה בייס לקבלת נתונים על המשתמש שמתחבר והצגתם על המסך
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView fullNameTextView = (TextView) findViewById(R.id.title_user);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 User userProfile = snapshot.getValue(User.class);
                 if(userProfile != null){
                     String fullName = userProfile.getName();
                     fullNameTextView.setTextColor(getColor(R.color.black));
                     fullNameTextView.setText(fullName + "!");
                     // System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$44"+userID);
                 }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserActivity.this, "Something wrong happened !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        // AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
        // Set the message show for the Alert time
        builder.setMessage("אתה בטוח שברצונך לצאת ?");
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
                    Intent intent = new Intent(UserActivity.this,MainActivity.class);
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