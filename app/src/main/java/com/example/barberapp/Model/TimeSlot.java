package com.example.barberapp.Model;

public class TimeSlot {

    private String slot;

    public TimeSlot(String slot) {
        this.slot = slot;
    }

    public TimeSlot() {

    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public static final int TOTAL_TIME_SLOT = 18;

    public static String convertTimeSlotToString (int slot){
        switch (slot)
        {
            case 0:
                return "10:00";
            case 1:
                return "10:30";
            case 2:
                return "11:00";
            case 3:
                return "11:30";
            case 4:
                return "12:00";
            case 5:
                return "12:30";
            case 6:
                return "14:00";
            case 7:
                return "14:30";
            case 8:
                return "15:00";
            case 9:
                return "15:30";
            case 10:
                return "16:00";
            case 11:
                return "16:30";
            case 12:
                return "17:00";
            case 13:
                return "17:30";
            case 14:
                return "18:00";
            case 15:
                return "18:30";
            case 16:
                return "19:00";
            case 17:
                return "19:30";
            default:
                return "Closed";
        }
    }
}
