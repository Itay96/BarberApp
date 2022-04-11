package com.example.barberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //edit text password && email
        TextView email = (TextView) findViewById(R.id.etEmail);
        TextView password = (TextView) findViewById(R.id.etPassword);

        //button login
        MaterialButton loginBtn = (MaterialButton) findViewById(R.id.BtnLogin);
        MaterialButton signBtn = (MaterialButton) findViewById(R.id.BtnsignUp);

        //action on loginBtn
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("admin")&&password.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity.this,"login succsesful",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"login faild !",Toast.LENGTH_SHORT).show();
                }
            }
        });


        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}