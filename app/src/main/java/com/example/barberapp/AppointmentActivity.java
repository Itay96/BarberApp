package com.example.barberapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberapp.Adapter.MyTimeSlotAdapter;
import com.example.barberapp.Interface.ITimeSlotLoadListener;
import com.example.barberapp.Interface.RecyclerViewInterface;
import com.example.barberapp.Model.TimeSlot;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;
import com.harrywhewell.scrolldatepicker.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.github.muddz.styleabletoast.StyleableToast;

public class AppointmentActivity extends AppCompatActivity implements RecyclerViewInterface {


    DayScrollDatePicker dayScrollDatePicker;
    DatabaseReference databaseReference;
    FirebaseAuth userAuth;
    FirebaseDatabase database;
    RecyclerView recyclerView;
    MyTimeSlotAdapter myTimeSlotAdapter;
    ArrayList<TimeSlot> timeSlotList;
    ITimeSlotLoadListener iTimeSlotLoadListener;
    TextView txt_date;
    Context context;
    String selectedDate;

    LocalBroadcastManager localBroadcastManager;
    Calendar selected_date;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users");
        userAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recycler_view);
        myTimeSlotAdapter = new MyTimeSlotAdapter((Context) this, (List<TimeSlot>) myTimeSlotAdapter,this);
        timeSlotList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(myTimeSlotAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));


        getIntent().getStringExtra("name");

        dayScrollDatePicker = findViewById(R.id.scrollCalender);
        dayScrollDatePicker.setStartDate(16,10,2022);
        dayScrollDatePicker.getSelectedDate(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@Nullable Date date) {
                if (date != null) {
                    selectedDate = date.toString();
                }
                StyleableToast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_SHORT,R.style.exampleToast).show();
            }
        });


        //initialize interface
        iTimeSlotLoadListener = new ITimeSlotLoadListener() {
            @Override
            public void onTimeSlotSuccess(List<TimeSlot> timeSlotList) {
            MyTimeSlotAdapter adapter = new MyTimeSlotAdapter(getApplicationContext(),timeSlotList);
            recyclerView.setAdapter(adapter);
            }

            @Override
            public void onTimeSlotFailed(String message) {
                Toast.makeText(AppointmentActivity.this, "error to load", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTimeSlotLoadEmpty() {

            }
        };

    }

    private void loadBarberFromDataBase(String barberId) {

        final DatabaseReference barberRef = database.getReference("Users");

    }
            @Override
            public void onItemClick(int position) {
                StyleableToast.makeText(getApplicationContext(), "position"+position, Toast.LENGTH_SHORT,R.style.exampleToast).show();

            }
        }