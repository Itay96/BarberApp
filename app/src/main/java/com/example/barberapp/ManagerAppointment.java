package com.example.barberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ManagerAppointment extends AppCompatActivity {

    EditText date_time_in;
    EditText date_time_in_end;
//    SimpleDateFormat startTime;
//    SimpleDateFormat endTime;
    String dateFromCalendarStart;
    String dateFromCalendarEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_appointment);
        date_time_in=findViewById(R.id.time_layout_date);
        date_time_in_end = findViewById(R.id.time_layout_date_end);
        ////////////////// start time set event ///////////////////
        date_time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDateTimeDialog(date_time_in);
                    final Calendar calendar = Calendar.getInstance();
                    DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            calendar.set(Calendar.YEAR,year);
                            calendar.set(Calendar.MONTH,month);
                            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                            TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                    calendar.set(Calendar.MINUTE,minute);
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
                                    dateFromCalendarStart = simpleDateFormat.format(calendar.getTime());
                                    //System.out.println(dateFromCalendarStart);
                                    date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                                }
                            };

                            new TimePickerDialog(ManagerAppointment.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
                        }
                    };

                    new DatePickerDialog(ManagerAppointment.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                }


        });
            /////////////////// end time set event //////////////////////
            date_time_in_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDateTimeDialog(date_time_in_end);
                    final Calendar calendar = Calendar.getInstance();
                    DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            calendar.set(Calendar.YEAR,year);
                            calendar.set(Calendar.MONTH,month);
                            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                            TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                    calendar.set(Calendar.MINUTE,minute);
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
                                    dateFromCalendarEnd = simpleDateFormat.format(calendar.getTime());
                                    //System.out.println(dateFromCalendarEnd);
                                    date_time_in_end.setText(simpleDateFormat.format(calendar.getTime()));
                                }
                            };

                            new TimePickerDialog(ManagerAppointment.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
                        }
                    };

                    new DatePickerDialog(ManagerAppointment.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                }

        });


    }

//    private void showDateTimeDialog(EditText date_time_in) {
//        final Calendar calendar = Calendar.getInstance();
//        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                calendar.set(Calendar.YEAR,year);
//                calendar.set(Calendar.MONTH,month);
//                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
//
//                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
//                        calendar.set(Calendar.MINUTE,minute);
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
//                        dateFromCalendar = simpleDateFormat.format(calendar.getTime());
//                        //System.out.println(dateFromCalendar);
//                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
//                    }
//                };
//
//                new TimePickerDialog(ManagerAppointment.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
//            }
//        };
//
//        new DatePickerDialog(ManagerAppointment.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
//    }

}