package calculate.number;

import java.util.Stack;

public class BinaryTree {

    Node root;
    Stack<Integer> stack;

    // create empty stack when initializing tree.
    BinaryTree() {
        this.root = null;
        this.stack = new Stack<>();
    }

    // depth first tree to stack pre-order traversal.
    private void createPreOrderStack(Node node) {
        if (node == null) {
            return;
        } else {
            this.stack.push(node.keyValue);
            createPreOrderStack(node.leftChild);
            createPreOrderStack(node.rightChild);
        }
    }

    // create stack from binary tree.
    Stack<Integer> createPreOrderStack() {
        createPreOrderStack(root);

        System.out.print("Stack created: |");
        this.stack.forEach(i -> {
            System.out.print(" " + i);
        });
        System.out.print(" |" + "\n\n");

        return this.stack;
    }
}
