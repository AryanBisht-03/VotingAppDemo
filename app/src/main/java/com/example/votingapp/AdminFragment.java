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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class AdminFragment extends Fragment {

    public AdminFragment() {
        // Required empty public constructor
    }

    EditText Name,password;
    FirebaseAuth mAuth;
    Button login;

    //----> Here is the main program <----
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_admin, container, false);

        //Linking variable with the view in fragment
        Name = view.findViewById(R.id.UserEmail);
        password = view.findViewById(R.id.UserPassword);
        mAuth = FirebaseAuth.getInstance();
        login = view.findViewById(R.id.LoginButton_Admin);

        //Check if the user email and password then go to admin page..

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NameTxt,passwordTxt;
                NameTxt = Name.getText().toString();
                passwordTxt = password.getText().toString();
                Log.d("Aryan","Login Clicked");
                Log.d("Aryan",NameTxt+" and "+passwordTxt);
                if(!TextUtils.isEmpty(NameTxt)&&!TextUtils.isEmpty(passwordTxt)){
                    Log.d("Aryan","Started working ");
                    mAuth.createUserWithEmailAndPassword(NameTxt,passwordTxt).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Log.d("Aryan","Succesfully logUp");
                        }
                    });
                    //Check if this to is correct or not
                }
                else
                {
                    Log.d("Aryan","fields are empty");
                    Toast.makeText(getContext(), "Some fields are empty.Please type again..", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}