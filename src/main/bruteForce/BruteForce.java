package main.bruteForce;

import java.util.Stack;

public class BruteForce {

    private int goalNumber;
    private Stack<Integer> gameStack;

    public BruteForce(int goalNumber, Stack<Integer> stack) {
        this.goalNumber = goalNumber;
        this.gameStack = stack;
    }

    private void calculate(Stack<Integer> stack, int currentValue, String calculationPath) {
        if (!stack.empty()) {
            int topNumber = stack.pop();
            operate(currentValue, topNumber, stack, calculationPath);
        }
    }

    private void operate(int x, int y, Stack<Integer> stack, String calculationPath) {
        // switch incoming integers if x < y
        if (x < y){
            int t = x;
            x = y;
            y = t;
        }

        // Deep copies of stack
        Stack<Integer> addStack = (Stack<Integer>)stack.clone();
        Stack<Integer> multiplyStack = (Stack<Integer>)stack.clone();
        Stack<Integer> subtractStack = (Stack<Integer>)stack.clone();
        Stack<Integer> divideStack = (Stack<Integer>)stack.clone();

        // Paths
        String addPath = calculationPath + " + " + y;
        System.out.print(addPath);
        System.out.print(" = " + (x + y) + "\n");

        if (x + y == goalNumber) {
            System.out.println("FOUND!!!");
        }

        String multiplyPath = calculationPath + " * " + y;
        System.out.print(multiplyPath);
        System.out.print(" = " + (x * y) + "\n");

        if (x * y == goalNumber) {
            System.out.println("FOUND!!!");
        }

        String subtractPath = calculationPath + " - " + y;
        System.out.print(subtractPath);
        System.out.print(" = " + (x - y) + "\n");

        if (x - y == goalNumber) {
            System.out.println("FOUND!!!");
        }

        // divide by zero is impossible.
        if (y != 0) {
            String dividePath = calculationPath + " / " + y;
            System.out.print(dividePath);
            System.out.print(" = " + (x / y) + "\n");

            if (x / y == goalNumber) {
                System.out.println("FOUND!!!");
            }
            calculate(divideStack, x / y, dividePath);
        }

        // Calculation method calls
        calculate(addStack, x + y, addPath);
        calculate(multiplyStack, x * y, multiplyPath);
        calculate(subtractStack, x - y, subtractPath);
    }

    public void calculate() {
        int startValue = this.gameStack.pop();
        String startPath = "path: ";
        startPath += startValue;
        calculate(this.gameStack, startValue, startPath);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(25);
        stack.push(50);
        stack.push(7);
        stack.push(3);
        stack.push(4);
        stack.push(9);
        int goalNumber = 325;

        BruteForce bruteForce = new BruteForce(goalNumber, stack);
        // Given path is incorrect. This is caused by switching x and y values when x < y.
        bruteForce.calculate();
    }
}
