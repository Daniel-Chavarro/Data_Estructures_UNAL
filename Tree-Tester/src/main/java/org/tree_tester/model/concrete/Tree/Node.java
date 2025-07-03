package org.tree_tester.model.concrete.Tree;

public class Node<T extends Comparable<T>> {
    protected T data;
    protected Node<T> left;
    protected Node<T> right;
    protected Node<T> parent;
    protected int height;

    public Node(T data, Node<T> parent) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = parent;
        this.height = 0;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
