package ru.academits.findyurov.tree.binarySearchTree;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;
    private int size;

    private class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public void insert(T value) {
        Node<T> newNode = new Node<>(value);

        if (root == null) {
            root = newNode;
            size++;
            return;
        }

        Node<T> current = root;

        while (true) {
            if (newNode.value.compareTo(current.value) < 0) {
                if (current.left == null) {
                    current.left = newNode;
                    size++;
                    return;
                }
                current = current.left;
            } else if (newNode.value.compareTo(current.value) > 0) {
                if (current.right == null) {
                    current.right = newNode;
                    size++;
                    return;
                }

                current = current.right;
            } else {
                return;
            }
        }
    }

    public boolean contains(T value) {
        Node<T> current = root;

        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            } else if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    public void remove(T value) {
        Node<T> parent = null;
        Node<T> current = root;
        boolean isLeftChild = false;

        while (current != null) {
            int comparison = value.compareTo(current.value);

            if (comparison < 0) {
                parent = current;
                current = current.left;
                isLeftChild = true;
            } else if (comparison > 0) {
                parent = current;
                current = current.right;
                isLeftChild = false;
            } else {
                if (current.left == null && current.right == null) {
                    if (current == root) {
                        root = null;
                    } else if (isLeftChild) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                } else if (current.left == null) {
                    if (current == root) {
                        root = current.right;
                    } else if (isLeftChild) {
                        parent.left = current.right;
                    } else {
                        parent.right = current.right;
                    }
                } else if (current.right == null) {
                    if (current == root) {
                        root = current.left;
                    } else if (isLeftChild) {
                        parent.left = current.left;
                    } else {
                        parent.right = current.left;
                    }
                } else {
                    Node<T> successor = getSuccessor(current);

                    if (current == root) {
                        root = successor;
                    } else if (isLeftChild) {
                        parent.left = successor;
                    } else {
                        parent.right = successor;
                    }

                    successor.left = current.left;
                }

                size--;
                return;
            }
        }
    }

    private Node<T> getSuccessor(Node<T> node) {
        Node<T> parent = node;
        Node<T> current = node.right;

        while (current.left != null) {
            parent = current;
            current = current.left;
        }

        if (current != node.right) {
            parent.left = current.right;
            current.right = node.right;
        }

        return current;
    }

    public int size() {
        return size;
    }

    public List<T> breadthFirstTraversal() {
        List<T> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            result.add(current.value);

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }

    public List<T> preOrderTraversal() {
        List<T> result = new ArrayList<>();
        preOrderTraversal(root, result);
        return result;
    }

    private void preOrderTraversal(Node<T> node, List<T> result) {
        if (node == null) {
            return;
        }

        result.add(node.value);
        preOrderTraversal(node.left, result);
        preOrderTraversal(node.right, result);
    }

    public List<T> preOrderTraversalWithoutRecursion() {
        List<T> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> current = stack.pop();
            result.add(current.value);

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return result;
    }

    public List<T> inOrderTraversal() {
        List<T> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    private void inOrderTraversal(Node<T> node, List<T> result) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left, result);
        result.add(node.value);
        inOrderTraversal(node.right, result);
    }

    public List<T> inOrderTraversalWithoutRecursion() {
        List<T> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Stack<Node<T>> stack = new Stack<>();
        Node<T> current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            result.add(current.value);
            current = current.right;
        }

        return result;
    }

    public List<T> postOrderTraversal() {
        List<T> result = new ArrayList<>();
        postOrderTraversal(root, result);
        return result;
    }

    private void postOrderTraversal(Node<T> node, List<T> result) {
        if (node == null) {
            return;
        }

        postOrderTraversal(node.left, result);
        postOrderTraversal(node.right, result);
        result.add(node.value);
    }

    public List<T> postOrderTraversalWithoutRecursion() {
        List<T> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Stack<Node<T>> stack1 = new Stack<>();
        Stack<Node<T>> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            Node<T> current = stack1.pop();
            stack2.push(current);

            if (current.left != null) {
                stack1.push(current.left);
            }

            if (current.right != null) {
                stack1.push(current.right);
            }
        }

        while (!stack2.isEmpty()) {
            result.add(stack2.pop().value);
        }

        return result;
    }
}