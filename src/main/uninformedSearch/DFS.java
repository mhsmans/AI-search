package main.uninformedSearch;

import java.util.Random;
import java.util.Stack;

public class DFS {
    // Depth first approach for solving countdown numbers game.
    // Six numbers are selected; they are restricted to numbers up to ten,
    // or multiples of 25 up to 100. There is one three-digit number (100 - 999)
    // that needs to be obtained by performing arithmetic operations on the six
    // numbers.

    private Stack<StackItem> leftStack = new Stack<>();
    private Stack<StackItem> rightStack = new Stack<>();
    private Stack<String> pathStack = new Stack<>();
    private Stack<Double> resultStack = new Stack<>();
    private int goalNumber;
    private int[] numbers;

    public DFS(int goalNumber, int[] numbers) {
        this.numbers = numbers;
        this.goalNumber = goalNumber;
        this.randomizeOrder();

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

        this.resultStack.push(leftStack.peek().getValue());
    }

    public void search() {
        if(!rightStack.empty()) {
            operate();
            // Check if solution is found. If not, go on searching.
            if(!checkResult()) {
                search();
            }
        }
        // If the rightStack is empty, all numbers are used to create results. If we want to go
        // on with calculating possible results, we need to remove the first items from the resultStack
        // and the leftStack.
        else {
            rightStack.push(leftStack.pop());
            resultStack.pop();
            pathStack.pop();

            // if the top item of the leftStack has reached its last operation, we need to pop the
            // item to the rightStack. This is repeated until the top item on the left stack has not
            // yet reached its final operation. Also, the items that go from leftStack to rightStack
            // need to have their operation set to starting position (+). In addition to that, we also
            // need to remove the last item from the resultStack.
            while(!leftStack.empty() && leftStack.peek().getCurrentOperation().equals("*")) {
                rightStack.push(leftStack.pop());
                resultStack.pop();
                rightStack.peek().initializeOperation();
                if (!pathStack.empty()) {
                    pathStack.pop();
                }
            }

            // If the leftStack is not yet empty, we can increment the operation of the top item. If the
            // most left item (the starting number) on the leftStack has reached its final operation, it
            // will be popped to the rightStack. This implies that when all operations are done (every
            // item has reached its final operation), the leftStack will be empty and the program will stop
            // searching.
            if (!leftStack.empty()) {
                leftStack.peek().incrementOperation();
                operate();
                // Check if solution is found. If not, go on searching.
                if(!checkResult()) {
                    search();
                }
            } else {
                System.out.println("No result found.");
            }

        }
    }

    // Check operation of the leftStack top item. Use this operation to create a path
    // (step in calculation process). New path en result are pushed to corresponding
    // stacks. Top item of rightStack gets pushed to the leftStack.
    private void operate() {
        if(leftStack.peek().getCurrentOperation().equals("+")) {
            String path = resultStack.peek() + " " + leftStack.peek().getCurrentOperation() + " " + rightStack.peek().getValue();
            resultStack.push(resultStack.peek() + rightStack.peek().getValue());
            pathStack.push(path);
            leftStack.push(rightStack.pop());
        }
        else if(leftStack.peek().getCurrentOperation().equals("*")) {
            String path = resultStack.peek() + " " + leftStack.peek().getCurrentOperation() + " " + rightStack.peek().getValue();
            resultStack.push(resultStack.peek() * rightStack.peek().getValue());
            pathStack.push(path);
            leftStack.push(rightStack.pop());
        }
        else if(leftStack.peek().getCurrentOperation().equals("-")) {
            // If the resultStack top item value is less than the rightStack top item value,
            // switch the order.
            if(resultStack.peek() < rightStack.peek().getValue()) {
                String path = rightStack.peek().getValue() + " " + leftStack.peek().getCurrentOperation() + " " + resultStack.peek();
                resultStack.push(rightStack.peek().getValue() - resultStack.peek());
                pathStack.push(path);
                leftStack.push(rightStack.pop());
            } else {
                String path = resultStack.peek() + " " + leftStack.peek().getCurrentOperation() + " " + rightStack.peek().getValue();
                resultStack.push(resultStack.peek() - rightStack.peek().getValue());
                pathStack.push(path);
                leftStack.push(rightStack.pop());
            }
        } else if(leftStack.peek().getCurrentOperation().equals("/")) {
            // If the resultStack top item value is less than the rightStack top item value,
            // switch the order. Do not switch order when lowest number equals zero.
            if(resultStack.peek() < rightStack.peek().getValue() && resultStack.peek() != 0) {
                String path = rightStack.peek().getValue() + " " + leftStack.peek().getCurrentOperation() + " " + resultStack.peek();
                resultStack.push(rightStack.peek().getValue() / resultStack.peek());
                pathStack.push(path);
                leftStack.push(rightStack.pop());
            } else {
                String path = resultStack.peek() + " " + leftStack.peek().getCurrentOperation() + " " + rightStack.peek().getValue();
                resultStack.push(resultStack.peek() / rightStack.peek().getValue());
                pathStack.push(path);
                leftStack.push(rightStack.pop());
            }
        }
    }

    // Check if the result is equal to the goalNumber.
    private boolean checkResult() {
        if(resultStack.peek() == goalNumber) {
            System.out.println("Solution found!: " + resultStack.peek() + "\n");

            Stack<String> reversedStack = new Stack<>();

            // Reverse path stack.
            while (!pathStack.empty()) {
                reversedStack.push(pathStack.pop());
            }

            // Print items from path stack.
            while (!reversedStack.empty()) {
                System.out.println(reversedStack.pop());
            }

            return true;
        } else {
            return false;
        }
    }

    private void randomizeOrder()
    {
        int index, temp;
        Random random = new Random();
        for (int i = numbers.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = numbers[index];
            numbers[index] = numbers[i];
            numbers[i] = temp;
        }

        System.out.println("\nCurrent random array: ");
        for (Integer i:numbers) {
            System.out.print(i + " ");
        }
        System.out.print("\n\n");
    }

    public static void main(String[] args) {
        int goalNumber = 251;
        int[] numbers = {50, 25, 5, 8, 1, 6};

        DFS dfs = new DFS(goalNumber, numbers);
        dfs.search();
    }
}
