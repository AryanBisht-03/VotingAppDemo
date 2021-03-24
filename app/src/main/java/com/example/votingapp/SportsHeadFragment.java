package com.example.votingapp;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class SportsHeadFragment extends Fragment implements View.OnClickListener  {

    public SportsHeadFragment() {
        // Required empty public constructor
    }
    String text;
    RadioGroup radioGroup;
    Button selected;
    Integer votesCount;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sports_head, container, false);
        radioGroup = view.findViewById(R.id.radioGroupSports);
        selected = view.findViewById(R.id.selectedBtnSports);
        votesCount=0;

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child(getString(R.string.Candidate_key)).child(getString(R.string.Sports_key));

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot data: snapshot.getChildren())
                {
                    RadioButton radioButton1 = new RadioButton(getContext());
                    radioButton1.setText(data.getKey());
                    radioButton1.setTextSize(24);
                    radioButton1.setId(View.generateViewId());
                    radioButton1.setPadding(0,20,0,20);
                    radioButton1.setOnClickListener(SportsHeadFragment.this::onClick);
                    radioGroup.addView(radioButton1);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        int idRadio = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = getView().findViewById(idRadio);
        text = selectedRadioButton.getText().toString();

        Log.d("Aryan",text);

        selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Aryan","selected clicked");
                Log.d("Aryan", "hey! "+FirebaseDatabase.getInstance().getReference().child(getString(R.string.Candidate_key)).child(getString(R.string.Sports_key)).child(text).getKey());

                FirebaseDatabase.getInstance().getReference().child(getString(R.string.Candidate_key)).child(getString(R.string.Sports_key)).child(text).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Log.d("Aryan",snapshot.getKey() + " "+ snapshot.getValue());

                        votesCount = Integer.parseInt(snapshot.getValue().toString());
                        votesCount++;
                        snapshot.getRef().setValue(votesCount).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Log.d("Aryan","Successfull ");
                                TechincalHeadFragment techical = new TechincalHeadFragment();
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.FragmentFrame,techical);
                                transaction.commit();
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}