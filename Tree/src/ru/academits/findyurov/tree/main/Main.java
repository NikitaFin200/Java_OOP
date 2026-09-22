package ru.academits.findyurov.tree.main;

import ru.academits.findyurov.tree.binarySearchTree.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(15);
        tree.insert(25);
        tree.insert(35);

        System.out.println("Breadth-First Traversal:");
        System.out.println(tree.breadthFirstTraversal());
        System.out.println("Pre-Order Traversal:");
        System.out.println(tree.preOrderTraversal());
        System.out.println("Pre-Order Traversal Without Recursion:");
        System.out.println(tree.preOrderTraversalWithoutRecursion());
        System.out.println("In-Order Traversal:");
        System.out.println(tree.inOrderTraversal());
        System.out.println("In-Order Traversal Without Recursion:");
        System.out.println(tree.inOrderTraversalWithoutRecursion());
        System.out.println("Post-Order Traversal:");
        System.out.println(tree.postOrderTraversal());
        System.out.println("Post-Order Traversal Without Recursion:");
        System.out.println(tree.postOrderTraversalWithoutRecursion());

        System.out.println("Contains 30: " + tree.contains(30));
        System.out.println("Contains 50: " + tree.contains(50));

        System.out.println("Size: " + tree.size());

        tree.remove(10);
        tree.remove(35);

        System.out.println("Breadth-First Traversal after removing 10 and 35:");
        System.out.println(tree.breadthFirstTraversal());
    }
}