package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.LinearLayout;

public class LoginActivity extends AppCompatActivity {

    AdminFragment FragmentAdmin;
    LinearLayout Frame;
    VoterFragment FragmentVoter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Linking variable with the view in activity
        FragmentAdmin = new AdminFragment();
        Frame = findViewById(R.id.Frame);
        FragmentVoter = new VoterFragment();

        //Displaying the corresponding fragment which button is clicked in last activity
        Bundle bundle = getIntent().getExtras();
        int FrameNumber = bundle.getInt("Frame Number",-1);
        FragmentTransaction fragment = getSupportFragmentManager().beginTransaction();

        if(FrameNumber==1)
        {
            fragment.replace(R.id.Frame,FragmentAdmin);
        }
        else
        {
            fragment.replace(R.id.Frame,FragmentVoter);
        }
        fragment.commit();

    }
}