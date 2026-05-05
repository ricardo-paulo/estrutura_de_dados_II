package net.ricardo_paulo.BST;

import net.ricardo_paulo.Components.Node;
import net.ricardo_paulo.Components.FindLastDirection;
import net.ricardo_paulo.Components.RecursionOrder;

import java.util.ArrayList;

public class BST<T extends Comparable<T>> {

    public Node<T> root;

    public BST() {
        this.root = null;
    }

    public void addNode(Node<T> newNode) {
        if (root == null) {
            root = newNode;
        } else {
            addNodeRecursively(newNode, root);
        }
    }

    public void addNode(T newNodeElement) {
        Node<T> newNode = new Node<>(newNodeElement);
        addNode(newNode);
    }

    private void addNodeRecursively(Node<T> newNode, Node<T> current) {
        // compareTo < 0 significa que o elemento é menor
        boolean newNodeToLeft = newNode.element.compareTo(current.element) < 0;

        if (newNodeToLeft) {
            if (current.left == null) {
                current.left = newNode;
                newNode.parent = current; // Mantendo a referência de pai
            } else {
                addNodeRecursively(newNode, current.left);
            }
        } else {
            if (current.right == null) {
                current.right = newNode;
                newNode.parent = current;
            } else {
                addNodeRecursively(newNode, current.right);
            }
        }
    }

    public NodeSearchResult<T> searchNode(T target) {
        return searchRecursively(root, target, 0);
    }

    private NodeSearchResult<T> searchRecursively(Node<T> current, T target, int level) {
        if (current == null) return new NodeSearchResult<>();
        level += 1;

        // Comparação de igualdade usando compareTo
        if (current.element.compareTo(target) == 0) {
            return new NodeSearchResult<>(current.parent, current, level);
        }

        if (target.compareTo(current.element) < 0) {
            return searchRecursively(current.left, target, level);
        } else {
            return searchRecursively(current.right, target, level);
        }
    }

    public boolean removeNode(T target) {
        NodeSearchResult<T> result = searchNode(target);

        if (result.found) {
            Node<T> substitute = null;
            Node<T> lastFromSubstitute;

            if (result.node.left != null) {
                substitute = result.node.left;
                lastFromSubstitute = findLast(substitute, FindLastDirection.RIGHT);
                lastFromSubstitute.right = result.node.right;
                if (result.node.right != null) result.node.right.parent = lastFromSubstitute;
            } else if (result.node.right != null) {
                substitute = result.node.right;
                lastFromSubstitute = findLast(substitute, FindLastDirection.LEFT);
                lastFromSubstitute.left = result.node.left;
                if (result.node.left != null) result.node.left.parent = lastFromSubstitute;
            }

            if (result.node == root) {
                this.root = substitute;
                if (substitute != null) substitute.parent = null;
            } else {
                if (result.node.element.compareTo(result.parent.element) < 0) {
                    result.parent.left = substitute;
                } else {
                    result.parent.right = substitute;
                }
                if (substitute != null) substitute.parent = result.parent;
            }
            return true;
        }
        return false;
    }

    public Node<T> findLast(Node<T> current, FindLastDirection direction) {
        if (current == null) return null;

        if (direction == FindLastDirection.LEFT) {
            return (current.left != null) ? findLast(current.left, direction) : current;
        } else {
            return (current.right != null) ? findLast(current.right, direction) : current;
        }
    }

    public NodeSearchResult<T> findInOrder(T reference, boolean sucessor) {
        NodeSearchResult<T> refNode = searchNode(reference);
        ArrayList<T> numbersList = new ArrayList<>();

        if (refNode.found) {
            listInOrderRecursively(numbersList, root);
            int referenceIndex = numbersList.indexOf(reference);

            if (numbersList.size() <= 1) return new NodeSearchResult<>();

            int targetIndex = sucessor ? referenceIndex + 1 : referenceIndex - 1;

            if (targetIndex < 0 || targetIndex >= numbersList.size()) {
                return new NodeSearchResult<>();
            }

            return searchNode(numbersList.get(targetIndex));
        }
        return new NodeSearchResult<>();
    }

    private void listInOrderRecursively(ArrayList<T> array, Node<T> current) {
        if (current == null) return;
        listInOrderRecursively(array, current.left);
        array.add(current.element);
        listInOrderRecursively(array, current.right);
    }

    public int nodesAmount(Node<T> current) {
        if (current == null) return 0;
        return nodesAmount(current.left) + nodesAmount(current.right) + 1;
    }

    public void print(RecursionOrder order, Node<T> node) {
        if (node == null) return;

        switch (order) {
            case PRE_ORDER -> {
                System.out.println(node.element);
                print(order, node.left);
                print(order, node.right);
            }
            case IN_ORDER -> {
                print(order, node.left);
                System.out.println(node.element);
                print(order, node.right);
            }
            case POST_ORDER -> {
                print(order, node.left);
                print(order, node.right);
                System.out.println(node.element);
            }
        }
    }

    public boolean isBST(Node<T> current) {
        if (current == null) return true;

        boolean leftOk = (current.left == null) || (current.left.element.compareTo(current.element) <= 0);
        boolean rightOk = (current.right == null) || (current.right.element.compareTo(current.element) > 0);

        return leftOk && rightOk && isBST(current.left) && isBST(current.right);
    }
}