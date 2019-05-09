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

    public void calculate(Stack<Integer> stack, int startValue) {

        // Deep copies of game stack
        Stack<Integer> addStack = (Stack<Integer>)stack.clone();
        Stack<Integer> multiplyStack = (Stack<Integer>)stack.clone();
        Stack<Integer> subtractStack = (Stack<Integer>)stack.clone();

        calculateAdd(addStack, startValue);
        calculateMultiply(multiplyStack, startValue);
        //calculateSubtract(subtractStack, startValue);
    }

    private void calculateAdd(Stack<Integer> stack, int currentValue) {
        // Last remaining value is currentValue
        if (stack.empty()) { }
        // currentValue is not starting value of 0
        else if (currentValue != 0) {
            int topItem = stack.pop();

            System.out.println(currentValue + " + " + topItem + " = " + (currentValue + topItem) + " ADD");

            calculate(stack, currentValue + topItem);
        }
        // currentValue equals 0
        else {
            int topItem = stack.pop();

            calculate(stack, topItem);
        }
    }

    private void calculateMultiply(Stack<Integer> stack, int currentValue) {
        // Last remaining value is currentValue
        if (stack.empty()) { }
        // currentValue is not starting value of 0
        else if (currentValue != 0) {
            int topItem = stack.pop();

            System.out.println(currentValue + " * " + topItem + " = " + (currentValue * topItem) + " MULTIPLY");

            calculate(stack, currentValue * topItem);
        }
        // currentValue equals 0
        else {
            int topItem = stack.pop();
            calculate(stack, topItem);
        }
    }

    private void calculateSubtract(Stack<Integer> stack, int currentValue) {
        // Last remaining value is currentValue
        if (stack.empty()) { }
        // currentValue is not starting value of 0
        else if (currentValue != 0) {
            int topItem = stack.pop();

            int[] switchedValues = operate(currentValue, topItem);
            System.out.println(switchedValues[0] + " * " + switchedValues[1] + " = " + (switchedValues[0] * switchedValues[1]));

            calculateSubtract(stack, currentValue - topItem);
        }
        // currentValue equals 0
        else {
            int topItem = stack.pop();
            calculateSubtract(stack, topItem);
        }
    }

    private int[] operate(int x, int y) {
        // switch incoming integers if x < y
        if (x < y){
            int t = x;
            x = y;
            y = t;
        }
        int[] array = {x, y};
        return array;
/*        System.out.println(x + " + " + y + " = " + (x + y));
        System.out.println(x + " - " + y + " = " + (x - y));
        //System.out.println(x + " / " + y + " = " + (x / y));
        System.out.println(x + " * " + y + " = " + (x * y));*/
    }

    public void calculate() {
        calculate(this.gameStack, START_VALUE);
    }

}
