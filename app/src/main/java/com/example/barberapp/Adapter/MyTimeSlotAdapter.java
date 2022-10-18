package com.example.barberapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberapp.Model.TimeSlot;
import com.example.barberapp.R;
import com.example.barberapp.Interface.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class MyTimeSlotAdapter extends RecyclerView.Adapter<MyTimeSlotAdapter.MyViewHolder> {


    private final RecyclerViewInterface recyclerViewInterface;

    TimeSlot timeSlot;
    Context context;
    TextView timeSlotDescription;
    ArrayList<TimeSlot> timeSlotList;

    public MyTimeSlotAdapter(Context context, List<TimeSlot> timeSlotList,RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.timeSlotList = new ArrayList<>();
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public MyTimeSlotAdapter(Context context, TextView timeSlotDescription, ArrayList<TimeSlot> timeSlotList,RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.timeSlotDescription = timeSlotDescription;
        this.timeSlotList = new ArrayList<>();
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public MyTimeSlotAdapter(Context applicationContext, List<TimeSlot> timeSlotList) {

        recyclerViewInterface = null;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_time_slot,time_slot_description;
        ArrayList<String> timeSlotList;
        CardView card_time_slot;

        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            timeSlotList = new ArrayList<>();
            card_time_slot = (CardView)itemView.findViewById(R.id.card_time_slot);
            txt_time_slot = (TextView)itemView.findViewById(R.id.txt_time_slot);
            time_slot_description = (TextView)itemView.findViewById(R.id.time_slot_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null)
                    {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION)
                        {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }

            });
        }
    }

    @NonNull
    @Override
    public MyTimeSlotAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.time_slot,parent,false);
        return new MyViewHolder(itemView,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.txt_time_slot.setText(new StringBuilder(TimeSlot.convertTimeSlotToString(position)).toString());
        if(timeSlotList.size() == 0) // if all position is available,just show list
        {
            myViewHolder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(R.color.white));
            myViewHolder.time_slot_description.setText("Available");
            myViewHolder.time_slot_description.setTextColor(context.getResources().getColor(android.R.color.black));
            myViewHolder.txt_time_slot.setTextColor(context.getResources().getColor(android.R.color.black));
        }
        else
        {
            for(TimeSlot slotValue : timeSlotList)
            {
                int slot = Integer.parseInt(slotValue.getSlot());
                if(slot == position)
                {
                    myViewHolder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
                    myViewHolder.time_slot_description.setText("Full");
                    myViewHolder.time_slot_description.setTextColor(context.getResources()
                            .getColor(android.R.color.white));
                    myViewHolder.txt_time_slot.setTextColor(context.getResources().getColor(android.R.color.white));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return TimeSlot.TOTAL_TIME_SLOT;
    }
}





