package net.ricardo_paulo.Components;

import static net.ricardo_paulo.Components.Direction.*;

import java.util.function.Function;

@SuppressWarnings({"unchecked", "rawtypes"})
public enum AdjacentInOrder {

    PREDECESSOR(node -> {
        Node child = ((Node) node).left;
        Node parent = ((Node) node).parent;

        if (child != null) {
            return RIGHT.getLastOf(child);
        } else if (parent != null){
            return minorAbove((Node) node, parent);
        }

        return null;

    }),
    SUCESSOR(node -> {
        Node child = ((Node) node).right;
        Node parent = ((Node) node).parent;

        if (child != null) {
            return LEFT.getLastOf(child);
        } else if (parent != null) {
            return majorAbove((Node) node, parent);
        }

        return null;

    });

    private final Function extrator;

    AdjacentInOrder (Function extrator) {
        this.extrator = extrator;
    }

    public <T extends Comparable<T>> Node<T> of (Node<T> node) {

        if (node == null)
            return null;

        return (Node<T>) extrator.apply(node);

    }

    private static <T extends Comparable<T>> Node<T> majorAbove (Node<T> reference, Node<T> parent) {

        if (reference == null || parent == null)
            return null;

        if (parent.element.compareTo(reference.element) > 0)
            return parent;

        return majorAbove(reference, parent.parent);
    }

    private static <T extends Comparable<T>> Node<T> minorAbove (Node<T> reference, Node<T> parent) {

        if (reference == null || parent == null)
            return null;

        if (parent.element.compareTo(reference.element) < 0)
            return parent;

        return minorAbove(reference, parent.parent);
    }

}
