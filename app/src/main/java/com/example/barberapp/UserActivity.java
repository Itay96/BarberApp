package com.example.barberapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

import java.util.jar.Attributes;
import android.content.DialogInterface;




public class UserActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;
    private Button logOut,settingsBtn,appointmentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        logOut = (Button) findViewById(R.id.signOut);
        settingsBtn = (Button) findViewById(R.id.settings);
         appointmentBtn = (Button) findViewById(R.id.appointmentBtn);

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
                Intent intent = new Intent(getApplicationContext(),AppointmentActivity.class);
                startActivity(intent);
            }
        });

        //חיבור לדאה בייס לקבלת נתונים על המשתמש שמתחבר והצגתם על המסך
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        final TextView fullNameTextView = (TextView) findViewById(R.id.fullName);
        final TextView emailTextView = (TextView) findViewById(R.id.emailAddress);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 User userProfile = snapshot.getValue(User.class);
                 if(userProfile != null){
                     String fullName = userProfile.getName();
                     String email = userProfile.getEmail();

                     greetingTextView.setText("welcome " + fullName +"!");
                     fullNameTextView.setText(fullName);
                     emailTextView.setText(email);
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

        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
        // Set the message show for the Alert time
        builder.setMessage("אתה בטוח שברצונך לצאת ?");
        // Set Alert Title
        builder.setTitle("BarberApp !");

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                 // When the user click yes button
                 // then app will close
                   finish();
                 }
            });

        // Set the Negative button with No name
        // OnClickListener method is use
        // of DialogInterface interface.
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