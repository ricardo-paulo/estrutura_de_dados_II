package net.ricardo_paulo;

import static net.ricardo_paulo.Components.Direction.*;

import net.ricardo_paulo.Components.AddNodeOptions;
import net.ricardo_paulo.Components.Direction;
import net.ricardo_paulo.Components.Node;
import net.ricardo_paulo.Components.RecursionOrder;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class AVL<T extends Comparable<T>> {

    public Node<T> root;

    public AVL () {
        this.root = null;
    }

    public static void rebuildAsAVL (Node<Integer> reference, AVL<Integer> newTree) {

        if (reference == null)
            return;

        AVL.rebuildAsAVL(reference.left, newTree);
        AVL.rebuildAsAVL(reference.right, newTree);


        Node<Integer> newNode = new Node<>(reference.element);

        newTree.addNode(
                newNode,
                new AddNodeOptions(false, false)
        );

    }

    public static <T extends Comparable<T>> boolean isAVL (Node<T> treeRoot) {

        AtomicBoolean isAVL = new AtomicBoolean(true);

        Main.forEachNode(treeRoot, RecursionOrder.PRE_ORDER, node -> {

            boolean leftMinor = true;
            boolean rightMajor = true;
            boolean balanced = true;

            if (node.left != null)
                leftMinor = node.left.element.compareTo(node.element) <= 0;

            if (node.right != null)
                rightMajor = node.right.element.compareTo(node.element) >= 0;

            if (checkBalancing(node) != BALANCED)
                balanced = false;

            boolean isValid = leftMinor && rightMajor && balanced;

            if (!isValid)
                isAVL.set(false);

        });

        return isAVL.get();

    }

    public int calcDepth (Node<T> reference) {

        if (reference == null) {
            return 0;
        }

        if (reference.parent == null) {
            return 0;
        }

        int count = calcDepth(reference.parent);

        return ++count;

    }

    public static <T extends Comparable<T>> int calcHeight (Node<T> reference) {

        // O valor retornado é −1 para que o resultado seja zero-based.
        if (reference == null) {
            return -1;
        }

        boolean hasChild = reference.left != null || reference.right != null;
        if (!hasChild) {
            return 0;
        }

        int leftResult = AVL.calcHeight(reference.left);
        int rightResult = AVL.calcHeight(reference.right);

        return Math.max(leftResult, rightResult) + 1;

    }

    public static <T extends Comparable<T>> int calcBF (Node<T> reference) {

        int leftHeight = AVL.calcHeight(reference.left);
        int rightHeight = AVL.calcHeight(reference.right);

        return leftHeight - rightHeight;

    }

    public static <T extends Comparable<T>> Direction checkBalancing (Node<T> reference) {

        int balancingFactor = AVL.calcBF(reference);

        if (Math.abs(balancingFactor) <= 1) {
            return BALANCED;
        }

        return balancingFactor > 0 ? LEFT : RIGHT;

    }

    private void balanceUp (Node<T> reference, boolean printRotate) {

        if (reference != null) {

            Direction unbDirection = checkBalancing(reference);

            if (unbDirection != BALANCED)
                rotate(reference, unbDirection, printRotate);

            balanceUp(reference.parent, printRotate);

        }

    }

    private void rotate(Node<T> reference, Direction direction, boolean printRotate) {
        Node<T> childToRotate = direction.getOf(reference);

        if (childToRotate == null)
            return;

        int childBf = AVL.calcBF(childToRotate);
        boolean doubleRotate = false;
        if (childBf > 0 && direction == RIGHT) {
            rotate(childToRotate, LEFT, false);
            childToRotate = direction.getOf(reference);

            if (printRotate) {
                System.out.printf("Rotação dupla a esquerda em %s realizada!\n", reference.element);
                doubleRotate = true;
            }
        } else if (childBf < 0 && direction == LEFT) {
            rotate(childToRotate, RIGHT, false);
            childToRotate = direction.getOf(reference);

            if (printRotate) {
                System.out.printf("Rotação dupla a direita em %s realizada!\n", reference.element);
                doubleRotate = true;
            }
        }

        Direction rotateDirection = direction == LEFT ? RIGHT : LEFT;

        Node<T> orphanSubTree = rotateDirection.getOf(childToRotate);

        Node<T> parent = reference.parent;
        if (childToRotate != null)
            childToRotate.parent = parent;

        if (parent == null) {
            this.root = childToRotate;
        } else {
            if (parent.left == reference) {
                parent.left = childToRotate;
            } else {
                parent.right = childToRotate;
            }
        }

        rotateDirection.setOn(childToRotate, reference);
        reference.parent = childToRotate;

        direction.setOn(reference, orphanSubTree);
        if (orphanSubTree != null) {
            orphanSubTree.parent = reference;
        }

        if (printRotate && !doubleRotate)
            System.out.printf("Rotação simples em %s realizada!\n", reference.element);
    }

    public void addNode (Node<T> newNode, AddNodeOptions options) {

        if (this.root == null) {
            this.root = newNode;
        } else {
            addRecursively(newNode, this.root, options.printRotate());
        }

        if (options.printAdd()) {
            System.out.printf("O elemento %s foi adicionado a árvore!\n", newNode.element);
        }

    }

    public void addNode (Node<T> newNode) {

        if (this.root == null) {
            this.root = newNode;
        } else {
            addRecursively(newNode, this.root, false);
        }

    }

    private void addRecursively (Node<T> newTerm, Node<T> current, boolean printRotate) {

        Direction addDirection = newTerm.element.compareTo(current.element) <= 0? LEFT : RIGHT;
        Node<T> nodeInDirection = addDirection.getOf(current);

        if (nodeInDirection == null) {
            addDirection.setOn(current, newTerm);
            newTerm.parent = current;
            this.balanceUp(current, printRotate);
        } else {
            addRecursively(newTerm, Objects.requireNonNull(addDirection.getOf(current)), printRotate);
        }

    }

    public void print (Node<T> reference, RecursionOrder order) {

        if (reference == null)
            return;

        switch (order) {

            case PRE_ORDER -> {
                System.out.println(reference.element);
                print(reference.left, order);
                print(reference.right, order);
            }

            case IN_ORDER -> {
                print(reference.left, order);
                System.out.println(reference.element);
                print(reference.right, order);
            }

            case POST_ORDER -> {
                print(reference.left, order);
                print(reference.right, order);
                System.out.println(reference.element);
            }

        }

    }

}
