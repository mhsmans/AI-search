package main.uninformedSearch;

import java.util.*;

public class DFS {

    private Stack<StackItem> leftStack = new Stack<>();
    private Stack<StackItem> rightStack = new Stack<>();
    private Stack<String> pathStack = new Stack<>();
    private int result = 0;
    private int goalNumber;

    public DFS(int goalNumber, int[] numbers) {
        // tempStack is used to reverse the order of the rightStack
        Stack<StackItem> tempStack = new Stack<>();
        for(int i = 0; i < numbers.length; i++) {
            StackItem stackItem = new StackItem(numbers[i]);
            if(i == 0) {
                this.leftStack.push(stackItem);
            } else {
                tempStack.push(stackItem);
            }
        }
        // Pop tempStack items into rightStack (reverse order)
        while (!tempStack.empty()) {
            this.rightStack.push(tempStack.pop());
        }

        this.goalNumber = goalNumber;
        this.result = leftStack.peek().getValue();
    }

    public void startSearch() {
        if(!rightStack.empty()) {
            operate();
        }
    }

    private void operate() {
        // Add calculation step to pathStack. Pop rightStack into leftStack. Change result.
        if(leftStack.peek().getCurrentOperation().equals("+")) {
            String path = result + leftStack.peek().getCurrentOperation() + rightStack.peek().getValue();
            result += rightStack.peek().getValue();
            pathStack.push(path);
            leftStack.push(rightStack.pop());
        }
        System.out.println(pathStack.peek());
        startSearch();
    }

    public static void main(String[] args) {
        int goalNumber = 22;
        int[] numbers = {4, 2, 5};

        DFS dfs = new DFS(goalNumber, numbers);
        dfs.startSearch();
    }


}
