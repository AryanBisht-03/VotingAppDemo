package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class VoterFragment extends Fragment {

    FirebaseAuth mAuth;
    public VoterFragment() {
        // Required empty public constructor
    }

    EditText rollNumber,password;
    Button login;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_voter, container, false);
        rollNumber = view.findViewById(R.id.voterRollNumber);
        password = view.findViewById(R.id.voterPassword);
        login = view.findViewById(R.id.LoginButton_Voter);

        Log.d("Aryan","fragment is created ");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Aryan"," login clicked");
                mAuth = FirebaseAuth.getInstance();
                String rollNumberText, passwordText;
                rollNumberText = rollNumber.getText().toString();
                passwordText = password.getText().toString();

                Log.d("Aryan","roll "+rollNumberText+" passqword "+passwordText);
                if(!TextUtils.isEmpty(rollNumberText)&&!TextUtils.isEmpty(passwordText))
                {
                    mAuth.signInWithEmailAndPassword(rollNumberText,passwordText).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            Log.d("Aryan","Successfull login ");
                            startActivity(new Intent(getActivity(),CandidateListActivity.class));
                        }
                    });
                }
            }
        });
        return view;
    }
}