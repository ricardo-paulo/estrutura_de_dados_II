package net.ricardo_paulo.BST;

public class NodeSearchResult {

    public Node parent;
    public Node node;
    public boolean found;
    public int level;

    public NodeSearchResult(Node parent, Node node, int level) {
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
