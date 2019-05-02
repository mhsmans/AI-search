package com.company;

import java.util.ArrayList;

public abstract class SearchStrategy {

    private int goalNumber;
    private ArrayList<Integer> numberList;
    private String travelledPath;

    public SearchStrategy(GameRules gameRules) {
        this.goalNumber = gameRules.getGoalNumber();
        this.numberList = gameRules.getNumberList();
        this.travelledPath = "Init";
    }

    public abstract void startSearching();

    public void print() {
        System.out.println(this.travelledPath);
    }

    public ArrayList<Integer> getNumberList() {
        return numberList;
    }

    public int getGoalNumber() {
        return goalNumber;
    }

    public String getTravelledPath() {
        return travelledPath;
    }

    public void setTravelledPath(String travelledPath) {
        this.travelledPath = travelledPath;
    }
}
