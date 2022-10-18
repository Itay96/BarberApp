package com.example.barberapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberapp.ManagerShowUsers;
import com.example.barberapp.Model.User;
import com.example.barberapp.R;

import java.util.ArrayList;

public class UsersAdapter_M extends RecyclerView.Adapter<UsersAdapter_M.MyViewHolder> {

    ManagerShowUsers context;
    ArrayList<User> list;

    public UsersAdapter_M(ManagerShowUsers context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.firstName.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.phoneNo.setText(user.getPhoneNo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView firstName,email,phoneNo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.first_name);
            email = itemView.findViewById(R.id.email);
            phoneNo = itemView.findViewById(R.id.phone_No);
        }
    }

}
