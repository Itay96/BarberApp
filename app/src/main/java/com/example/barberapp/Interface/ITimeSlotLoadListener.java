package com.example.barberapp.Interface;

import com.example.barberapp.Model.TimeSlot;

import java.util.List;

public interface ITimeSlotLoadListener {

    void onTimeSlotSuccess(List<TimeSlot> timeSlotList);
    void onTimeSlotFailed(String message);
    void onTimeSlotLoadEmpty();
}
