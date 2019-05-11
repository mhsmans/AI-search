package calculate.number;

import calculate.number.uninformedSearch.BruteForce;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();
        tree.root = new Node(25);
        tree.root.leftChild = new Node(50);
        tree.root.rightChild = new Node(7);
        tree.root.leftChild.leftChild = new Node(3);
        tree.root.leftChild.rightChild = new Node(4);
        tree.root.rightChild.leftChild = new Node(9);

        Stack<Integer> stack = tree.createPreOrderStack();
        int goalNumber = 330;

        //DepthFirstSearch dfs = new DepthFirstSearch(goalNumber, st);
        //dfs.calculate();

        BruteForce bruteForce = new BruteForce(goalNumber, stack);
        // Given path is incorrect. This is caused by switching x and y values when x < y.
        bruteForce.calculate();
    }

}
