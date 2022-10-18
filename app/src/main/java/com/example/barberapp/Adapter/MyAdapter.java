package com.example.barberapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberapp.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String data1,data2;
    String s3;
    Context context;


    public MyAdapter(Context ct, String s1, String s2, String s3){
        context = ct;
        data1 = s1;
        data2 = s2;
        s3 = s3;



    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(data1);
        holder.myText2.setText(data2);


    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1,myText2,myText3;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.my_txt1);
            myText2 = itemView.findViewById(R.id.my_txt2);




        }
    }
}
