package com.example.barberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class UserSettings extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView,backBtn,imageView3,imageView4,
            imageView5,imageView6,imageView7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setttings);

        imageView = findViewById(R.id.change_pass);
        backBtn = findViewById(R.id.back_btn);

        backBtn.setOnClickListener(this);
        imageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_btn:
                startActivity(new Intent(this, UserActivity.class));
                break;
            case R.id.change_pass:
                startActivity(new Intent(this, UserForgotPassword.class));
                break;
        }
    }
}