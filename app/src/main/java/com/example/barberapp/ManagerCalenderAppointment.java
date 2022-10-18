package com.example.barberapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barberapp.Model.Shifts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;

public class ManagerCalenderAppointment extends AppCompatActivity {

    EditText date_time_start, date_time_in_end,time_start,time_end;
    String dateFromCalendarStart, dateFromCalendarEnd,id,date,hour,min,userID,
            dateOfStart,hourOfStart,minuteOfStart,hourOfEnd,minuteOfEnd;
    Button BtnSendSchedule;
    Shifts shifts;
    Shifts startHour;
    Shifts endHour;

    //firebase
    DatabaseReference managerReference,shiftsReference;
    FirebaseAuth managerAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_appointment);
        date_time_start = findViewById(R.id.time_layout_date);
        date_time_in_end = findViewById(R.id.time_layout_date_end);
        BtnSendSchedule = findViewById(R.id.SendTimesBtn);

        managerAuth = FirebaseAuth.getInstance();
        userID = managerAuth.getCurrentUser().getUid().toString();

        TextView textView = findViewById(R.id.Start);
        TextView textView1 = findViewById(R.id.End);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference shiftReference = database.getReference("Shifts");


        //button send shift from manager activity
        // to firebase && create new appointment
        BtnSendSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ManagerCalenderAppointment.this, "Upload To FireBase", Toast.LENGTH_LONG);
                String shifts = new Shifts(date, hour,min).toString();
                FirebaseDatabase.getInstance().getReference("Shifts").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                        .setValue("start day:" + dateFromCalendarStart).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ManagerCalenderAppointment.this, "Shift updated to firebase", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), ManagerActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Something Wrong, try again !", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        ////////////////// choose start day of week ///////////////////
        date_time_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  showDateTimeDialog(date_time_in);
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                       /* TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
                                dateFromCalendarStart = simpleDateFormat.format(calendar.getTime());
                                textView.setText(dateFromCalendarStart);
                                String[] date = textView.getText().toString().split(" ");
                                String[] hoursStart = (date[1]).split(":");
                                dateOfStart = date[0]; // the date of start of the shift
                                hourOfStart = hoursStart[0];
                                minuteOfStart = hoursStart[1];
                                System.out.println(dateFromCalendarStart);
                                date_time_start.setText(simpleDateFormat.format(calendar.getTime()));

                            }
                        };*/

                    }
                };

                new DatePickerDialog(ManagerCalenderAppointment.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        /////////////////// choose end day of week //////////////////////
        date_time_in_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  showDateTimeDialog(date_time_in);
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                       /* TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
                                dateFromCalendarStart = simpleDateFormat.format(calendar.getTime());
                                textView.setText(dateFromCalendarStart);
                                String[] date = textView.getText().toString().split(" ");
                                String[] hoursStart = (date[1]).split(":");
                                dateOfStart = date[0]; // the date of start of the shift
                                hourOfStart = hoursStart[0];
                                minuteOfStart = hoursStart[1];
                                System.out.println(dateFromCalendarStart);
                                date_time_start.setText(simpleDateFormat.format(calendar.getTime()));

                            }
                        };*/

                    }
                };

                new DatePickerDialog(ManagerCalenderAppointment.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
}





