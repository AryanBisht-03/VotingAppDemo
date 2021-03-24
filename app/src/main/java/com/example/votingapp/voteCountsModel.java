package com.example.votingapp;

import java.sql.Struct;

public class voteCountsModel {

    String name;
    Integer voteCounts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVoteCounts() {
        return voteCounts;
    }

    public void setVoteCounts(Integer voteCounts) {
        this.voteCounts = voteCounts;
    }

    public voteCountsModel()
    {

    }

    public voteCountsModel(String name,Integer votes)
    {
        this.name = name;
        voteCounts = votes;
    }

}
