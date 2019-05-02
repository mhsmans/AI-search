package com.company;

import java.util.ArrayList;

public class GameRules {

    private int goalNumber;
    private ArrayList<Integer> numberList;

    GameRules(int goalNumber, ArrayList<Integer> numberList) {
        this.goalNumber = goalNumber;
        this.numberList = numberList;
    }

    int getGoalNumber() {
        return goalNumber;
    }

    ArrayList<Integer> getNumberList() {
        return numberList;
    }
}
