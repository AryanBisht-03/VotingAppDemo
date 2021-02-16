package com.example.votingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


public class AdminFragment extends Fragment {

    public AdminFragment() {
        // Required empty public constructor
    }

    EditText Name,password;

    //----> Here is the main program <----
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_admin, container, false);

        //Linking variable with the view in fragment
        Name = view.findViewById(R.id.UserEmail);
        password = view.findViewById(R.id.UserPassword);

        //Check if the user email and password then go to admin page..
        String NameTxt,passwordTxt;

        NameTxt = Name.getText().toString();
        passwordTxt = password.getText().toString();

        if(NameTxt!=""&&passwordTxt!=""){

            //Check if this to is correct or not
        }
        else
        {
            Toast.makeText(getContext(), "Some fields are empty.Please type again..", Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}