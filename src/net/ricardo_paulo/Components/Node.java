package net.ricardo_paulo.Components;

public class Node<T extends Comparable<T>> {

    public T element;
    public Node<T> left, right, parent;

    public Node(T element) {
        this.element = element;
    }

}
