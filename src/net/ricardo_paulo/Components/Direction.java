package net.ricardo_paulo.Components;

import net.ricardo_paulo.BST.Node;

import java.util.function.BiConsumer;
import java.util.function.Function;

public enum Direction {
    LEFT(
            node -> node.left,
            (ref, newNode) -> ref.left = newNode
    ),
    RIGHT(
            node -> node.right,
            (ref, newNode) -> ref.right = newNode
    ),
    BALANCED();

    private final Function<Node, Node> extractor;
    private final BiConsumer<Node, Node> updater;

    Direction(Function<Node, Node> extractor, BiConsumer<Node, Node> updater) {
        this.extractor = extractor;
        this.updater = updater;
    }

    Direction() {
        this.extractor = null;
        this.updater = null;
    }

    public Node getOf (Node node) {
        if (this.extractor == null)
            return null;

        return extractor.apply(node);
    }

    public void setOn (Node reference, Node newNode) {

        if (this.updater == null)
            return;

        updater.accept(reference, newNode);

    }

}
