package com.example.votingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerVoteCount extends RecyclerView.Adapter<recyclerVoteCount.voteCountViewHolder> {

    ArrayList<voteCountsModel> totalVotes;

    public recyclerVoteCount() {
    }

    public recyclerVoteCount(ArrayList<voteCountsModel> totalVotes) {
        this.totalVotes = totalVotes;
    }

    @NonNull
    @Override
    public voteCountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerforvotecount,parent,false);
        return  new voteCountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull voteCountViewHolder holder, int position) {

        holder.name.setText(totalVotes.get(position).getName().toString());
        holder.votes.setText(totalVotes.get(position).getVoteCounts().toString());
    }

    @Override
    public int getItemCount() {
        return totalVotes.size();
    }

    public class voteCountViewHolder extends RecyclerView.ViewHolder{

        TextView votes, name;
        public voteCountViewHolder(@NonNull View itemView) {
            super(itemView);
            votes =  itemView.findViewById(R.id.votesVoteCount);
            name = itemView.findViewById(R.id.nameVoteCount);

        }
    }
}
