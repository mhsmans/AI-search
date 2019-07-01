package main.uninformedSearch;

public class StackItem {

    private double value;
    private static final String[] OPERATIONS = {"+", "/", "-", "*"};
    private String currentOperation;
    private int operationIndex = 0;

    // Assign value and starting operation to StackItem.
    public StackItem(int value) {
        this.value = value;
        this.currentOperation = OPERATIONS[this.operationIndex];
    }

    public double getValue() {
        return value;
    }

    public String getCurrentOperation() {
        return currentOperation;
    }

    // If current operation is not the last possible option, assign next operation.
    public void incrementOperation() {
        if (this.operationIndex != 3) {
            this.currentOperation = OPERATIONS[this.operationIndex + 1];
            this.operationIndex += 1;
        }
    }

    public void initializeOperation() {
        this.operationIndex = 0;
        this.currentOperation = OPERATIONS[this.operationIndex];
    }
}
