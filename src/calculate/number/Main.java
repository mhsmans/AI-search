package calculate.number;

import calculate.number.uninformedSearch.DepthFirstSearch;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();
        tree.root = new Node(6);
        tree.root.leftChild = new Node(10);
        tree.root.rightChild = new Node(25);
        tree.root.leftChild.leftChild = new Node(75);
        tree.root.leftChild.rightChild = new Node(5);
        tree.root.rightChild.leftChild = new Node(50);

        Stack<Integer> stack = tree.createPreOrderStack();
        int goalNumber = 728;

        DepthFirstSearch dfs = new DepthFirstSearch(goalNumber, stack);
        dfs.calculate();
    }

}
