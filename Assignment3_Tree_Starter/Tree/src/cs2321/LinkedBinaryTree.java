package cs2321;

import net.datastructures.*;
import java.util.Iterator;
import cs2321util.ArrayList;

public class LinkedBinaryTree<E> implements BinaryTree<E> {
    // Inner class for the node
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            parent = p;
            left = l;
            right = r;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setLeft(Node<E> l) {
            left = l;
        }

        public void setRight(Node<E> r) {
            right = r;
        }
    }

    private Node<E> root; // Root node
    private int size; // Number of nodes in the tree

    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Position<E> root() {
        return root;
    }

    public Position<E> parent(Position<E> p) {
        Node<E> node = validate(p);
        return node.getParent();
    }

    public Iterable<Position<E>> children(Position<E> p) {
        Node<E> node = validate(p);
        ArrayList<Position<E>> children = new ArrayList<>(); // Use ArrayList directly
        if (node.getLeft() != null) {
            children.add(children.size(), node.getLeft()); // Use children.size() for the index
        }
        if (node.getRight() != null) {
            children.add(children.size(), node.getRight()); // Use children.size() for the index
        }
        return children;
    }

    public boolean isInternal(Position<E> p) {
        Node<E> node = validate(p);
        return (node.getLeft() != null || node.getRight() != null);
    }

    public boolean isExternal(Position<E> p) {
        Node<E> node = validate(p);
        return (node.getLeft() == null && node.getRight() == null);
    }

    public boolean isRoot(Position<E> p) {
        return p == root;
    }

    public Iterator<Position<E>> iterator() {
        return positions().iterator();
    }

    public Iterable<Position<E>> positions() {
        ArrayList<Position<E>> snapshot = new ArrayList<>(); // Use ArrayList directly
        if (!isEmpty()) {
            inorderSubtree(root, snapshot); // Use inorder traversal for the snapshot
        }
        return snapshot;
    }

    private void inorderSubtree(Position<E> p, ArrayList<Position<E>> snapshot) { // Changed List to ArrayList
        if (isInternal(p)) {
            inorderSubtree(left(p), snapshot); // Traverse left subtree
        }
        snapshot.add(snapshot.size(), p); // Use snapshot.size() for the index
        if (isInternal(p)) {
            inorderSubtree(right(p), snapshot); // Traverse right subtree
        }
    }

    private Node<E> validate(Position<E> p) {
        if (!(p instanceof Node<?>))
            throw new IllegalArgumentException("Not valid position");
        return (Node<E>) p;
    }

    public Position<E> left(Position<E> p) {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    public Position<E> right(Position<E> p) {
        Node<E> node = validate(p);
        return node.getRight();
    }

    // Add a root node
    public Position<E> addRoot(E e) {
        if (!isEmpty())
            throw new IllegalStateException("Tree is not empty");
        root = new Node<>(e, null, null, null);
        size++;
        return root;
    }

    // Add a left child
    public Position<E> addLeft(Position<E> p, E e) {
        Node<E> node = validate(p);
        if (node.getLeft() != null)
            throw new IllegalStateException("Left child already exists");
        Node<E> child = new Node<>(e, node, null, null);
        node.setLeft(child);
        size++;
        return child;
    }

    // Add a right child
    public Position<E> addRight(Position<E> p, E e) {
        Node<E> node = validate(p);
        if (node.getRight() != null)
            throw new IllegalStateException("Right child already exists");
        Node<E> child = new Node<>(e, node, null, null);
        node.setRight(child);
        size++;
        return child;
    }

    // Preorder traversal
    public ArrayList<E> preorder() { // Changed List to ArrayList
        ArrayList<E> result = new ArrayList<>();
        if (!isEmpty()) {
            preorder(root, result);
        }
        return result;
    }

    private void preorder(Position<E> p, ArrayList<E> result) { // Changed List to ArrayList
        result.add(result.size(), p.getElement()); // Use result.size() for the index
        for (Position<E> child : children(p)) {
            preorder(child, result);
        }
    }

    // Postorder traversal
    public ArrayList<E> postorder() { // Changed List to ArrayList
        ArrayList<E> result = new ArrayList<>();
        if (!isEmpty()) {
            postorder(root, result);
        }
        return result;
    }

    private void postorder(Position<E> p, ArrayList<E> result) { // Changed List to ArrayList
        for (Position<E> child : children(p)) {
            postorder(child, result);
        }
        result.add(result.size(), p.getElement()); // Use result.size() for the index
    }

    // Inorder traversal
    public ArrayList<E> inorder() { // Changed List to ArrayList
        ArrayList<E> result = new ArrayList<>();
        if (!isEmpty()) {
            inorder(root, result);
        }
        return result;
    }

    private void inorder(Position<E> p, ArrayList<E> result) { // Changed List to ArrayList
        if (isInternal(p)) {
            inorder(left(p), result);
        }
        result.add(result.size(), p.getElement()); // Use result.size() for the index
        if (isInternal(p)) {
            inorder(right(p), result);
        }
    }

    // Level order traversal
    public ArrayList<E> levelOrder() {
        ArrayList<E> result = new ArrayList<>();
        int initialCapacity = size(); // Use the size of the tree for initial capacity
        ArrayList<Position<E>> queue = new ArrayList<>(initialCapacity); // Initialize queue with the size of the tree

        if (isEmpty())
            return result;

        queue.add(queue.size(), root); // Enqueue the root element

        while (!queue.isEmpty()) {
            Position<E> p = queue.remove(0); // Dequeue the first element
            result.add(result.size(), p.getElement()); // Add the element to the result

            for (Position<E> child : children(p)) {
                queue.add(queue.size(), child); // Enqueue each child, ensure they are of type Position<E>
            }
        }
        return result;
    }
}