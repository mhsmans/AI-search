package com.company.uninformedSearch;

import com.company.GameRules;
import com.company.SearchStrategy;


public class DepthFirst extends SearchStrategy {

    public DepthFirst(GameRules gameRules) {
        super(gameRules);
        setTravelledPath("DepthFirstSearch initialized");
        print();
    }

    @Override
    public void startSearching() {

    }
}
