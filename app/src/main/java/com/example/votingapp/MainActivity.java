package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button adminBtn,userBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //Linking variable with the buttons
        adminBtn = findViewById(R.id.AdminBtn);
        userBtn = findViewById(R.id.VoterBtn);


        adminBtn.setOnClickListener(this);
        userBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int Frame;
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        if(v.getId()==R.id.AdminBtn)
            Frame = 1;
        else
            Frame = 2;
        intent.putExtra("Frame Number",Frame);
        startActivity(intent);
    }
}