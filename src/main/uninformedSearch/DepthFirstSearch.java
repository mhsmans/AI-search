package main.uninformedSearch;

import java.util.LinkedList;
import java.util.Stack;

public class DepthFirstSearch {

    private int[] numbers;
    private int goalNumber;
    private int currentResult;
    private Stack<StackItem> stack = new Stack<>();

    public DepthFirstSearch(int[] numbers, int goalNumber) {
        this.numbers = numbers;
        this.goalNumber = goalNumber;
    }

    private void calculate(LinkedList<StackItem> itemsList) {
        if (this.stack.empty()) {
            StackItem firstItem = itemsList.removeFirst();
            this.currentResult = firstItem.getValue();
            this.stack.push(firstItem);
        }

        String currentValueOperation = this.stack.peek().getCurrentOperation();

        if (!itemsList.isEmpty()) {
            StackItem nextItem = itemsList.removeFirst();
            operate(nextItem, currentValueOperation, itemsList);
        }
    }

    private void operate(StackItem nextItem, String currentOperation, LinkedList<StackItem> itemsList){
        if (currentOperation.equals("+")) {
            this.currentResult += nextItem.getValue();
        }
        if (currentOperation.equals("*")) {
            this.currentResult *= nextItem.getValue();
        }
        System.out.println(this.currentResult);
        this.stack.push(nextItem);

        // List is empty, result not found yet.
        if (!checkResult() && itemsList.isEmpty()) {
            itemsList.addLast(this.stack.pop());
            this.stack.peek().incrementOperation();
        }

        checkResult();
        calculate(itemsList);
    }

    // Create linked list made of "StackItems".
    public void calculate() {
        LinkedList<StackItem> list = new LinkedList<>();
        for(int i = 0; i < this.numbers.length; i++) {
            StackItem stackItem = new StackItem(this.numbers[i]);
            list.addLast(stackItem);
        }
        calculate(list);
    }

    // Check if goal number equals current result.
    private boolean checkResult(){
        if(this.currentResult == this.goalNumber) {
            System.out.println("Goal number found! -> " + this.currentResult);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] numbers = {2, 4, 6, 2};
        int goalNumber = 8;

        DepthFirstSearch dfs = new DepthFirstSearch(numbers, goalNumber);
        dfs.calculate();
    }
}
