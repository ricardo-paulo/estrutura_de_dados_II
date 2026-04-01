package ricardo_paulo.net.BinarySearchTree.Components;

import ricardo_paulo.net.BinarySearchTree.Node;

public class NodeSearchResult {

    public Node parent;
    public Node node;
    public boolean found;
    public int level;

    public NodeSearchResult(Node parent, Node node, int level) {
        this.parent = parent;
        this.node = node;
        this.found = true;
        this.level = level;
    }

    public NodeSearchResult() {
        this.parent = null;
        this.node = null;
        this.found = false;
        this.level = 0;
    }
}
