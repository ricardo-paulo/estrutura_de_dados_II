package net.ricardo_paulo.BST;

import net.ricardo_paulo.Components.Node;

public class NodeSearchResult<T extends Comparable<T>> {

    public Node<T> parent;
    public Node<T> node;
    public boolean found;
    public int level;

    public NodeSearchResult(Node<T> parent, Node<T> node, int level) {
        this.parent = parent;
        this.node = node;
        this.level = level;
        this.found = true;
    }

    public NodeSearchResult() {
        this.parent = null;
        this.node = null;
        this.level = -1;
        this.found = false;
    }
}