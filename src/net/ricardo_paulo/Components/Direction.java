package net.ricardo_paulo.Components;

import java.util.function.BiConsumer;
import java.util.function.Function;

@SuppressWarnings({"unchecked", "rawtypes"})
public enum Direction {
    LEFT(
            node -> ((Node) node).left,
            (ref, newNode) -> ((Node) ref).left = (Node) newNode
    ),
    RIGHT(
            node -> ((Node) node).right,
            (ref, newNode) -> ((Node) ref).right = (Node) newNode
    ),
    BALANCED();

    private final Function extractor;
    private final BiConsumer updater;

    Direction(Function extractor, BiConsumer updater) {
        this.extractor = extractor;
        this.updater = updater;
    }

    Direction() {
        this.extractor = null;
        this.updater = null;
    }

    public <T extends Comparable<T>> Node<T> getOf (Node<T> node) {
        if (this.extractor == null || node == null)
            return null;

        return (Node<T>) extractor.apply(node);
    }

    public <T extends Comparable<T>> void setOn (Node<T> reference, Node<T> newNode) {

        if (this.updater == null || reference == null)
            return;

        updater.accept(reference, newNode);

    }

    public <T extends Comparable<T>> Node<T> getLastOf (Node<T> reference) {

        if (this.extractor == null || reference == null)
            return null;

        Node<T> nextNode = (Node<T>) extractor.apply(reference);

        if (nextNode == null)
            return reference;

        return getLastOf(nextNode);

    }

}
