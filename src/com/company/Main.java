package com.company;

import com.company.uninformedSearch.DepthFirst;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int goalNumber = 728;

        ArrayList<Integer> numberList = new ArrayList<>();
        numberList.add(6);
        numberList.add(10);
        numberList.add(25);
        numberList.add(75);
        numberList.add(5);
        numberList.add(50);

        GameRules gameRules = new GameRules(goalNumber, numberList);

        DepthFirst depthFirstSearch = new DepthFirst(gameRules);
    }
}
