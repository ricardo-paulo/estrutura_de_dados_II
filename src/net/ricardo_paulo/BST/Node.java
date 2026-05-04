package net.ricardo_paulo.BST;

public class Node {

    public int element;
    public Node left;
    public Node right;
    public Node parent;

    public Node(int element) {
        this.element = element;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

}
