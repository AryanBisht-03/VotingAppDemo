package com.example.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShamoActivityResults extends AppCompatActivity {

    FirebaseDatabase database;
    RecyclerView recyclerViewSport,recyclerViewTechincal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shamo);
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        recyclerViewSport = findViewById(R.id.recyclerViewSport);
        recyclerViewTechincal = findViewById(R.id.recyclerViewTechincal);

        Log.d("Bisht","Layout is setting ");
        LinearLayoutManager layout = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerViewSport.setLayoutManager(layout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerViewTechincal.setLayoutManager(layoutManager);
        Log.d("Bisht","Layout is set ");

        ArrayList<voteCountsModel> arraySport = new ArrayList<>();
        ArrayList<voteCountsModel> arrayTechincal = new ArrayList<>();

        recyclerVoteCount adapterTechincal = new recyclerVoteCount(arrayTechincal);
        recyclerViewTechincal.setAdapter(adapterTechincal);

        recyclerVoteCount adpaterSport = new recyclerVoteCount(arraySport);
        recyclerViewSport.setAdapter(adpaterSport);

        reference.child(getString(R.string.Candidate_key)).child(getString(R.string.Sports_key)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arraySport.clear();
                for(DataSnapshot data:snapshot.getChildren())
                {
                    voteCountsModel v = new voteCountsModel();
                    v.setName(data.getKey());
                    v.setVoteCounts(Integer.parseInt(data.getValue().toString()));
                    arraySport.add(v);
                }
                adpaterSport.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Log.d("Bisht","All are off");

        reference.child(getString(R.string.Candidate_key)).child(getString(R.string.TechincalHead_Key)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayTechincal.clear();
                for(DataSnapshot data: snapshot.getChildren())
                {
                    voteCountsModel v = new voteCountsModel();
                    v.setName(data.getKey());
                    v.setVoteCounts(Integer.parseInt(data.getValue().toString()));
                    arrayTechincal.add(v);
                }
                adapterTechincal.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}