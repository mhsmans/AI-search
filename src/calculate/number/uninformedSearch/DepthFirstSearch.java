package calculate.number.uninformedSearch;

import java.util.Stack;

public class DepthFirstSearch {

    private int goalNumber;
    private Stack<Integer> gameStack;
    private static final int START_VALUE = 0;

    public DepthFirstSearch(int goalNumber, Stack<Integer> stack) {
        this.goalNumber = goalNumber;
        this.gameStack = stack;
    }

    private void calculate(Stack<Integer> stack, int currentValue) {
        // Last remaining value is currentValue
        if (stack.empty()) { }
        // currentValue is not starting value of 0
        else if (currentValue != 0) {
            int topItem = stack.pop();
            operate(currentValue, topItem);
            calculate(stack, currentValue + topItem);
            ////////////////////////////////////////////////////
            //calculate(stack, currentValue * topItem);
            ////////////////////////////////////////////////////
        }
        // currentValue equals 0
        else {
            int topItem = stack.pop();
            calculate(stack, topItem);
        }
    }

    private void operate(int x, int y) {
        // switch incoming integers if x < y
        if (x < y){
            int t = x;
            x = y;
            y = t;
        }
        System.out.println(x + " + " + y + " = " + (x + y));
        System.out.println(x + " - " + y + " = " + (x - y));
        System.out.println(x + " / " + y + " = " + (x / y));
        System.out.println(x + " * " + y + " = " + (x * y));
    }

    public void calculate() {
        calculate(this.gameStack, START_VALUE);
    }
}
