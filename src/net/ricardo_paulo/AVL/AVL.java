package net.ricardo_paulo.AVL;

import static net.ricardo_paulo.Components.Direction.*;

import net.ricardo_paulo.Components.AddNodeOptions;
import net.ricardo_paulo.BST.Node;
import net.ricardo_paulo.Components.Direction;
import net.ricardo_paulo.Components.RecursionOrder;

import java.util.Objects;

public class AVL {

    public Node root;

    public AVL () {
        this.root = null;
    }

    public static void rebuildAsAVL (Node reference, AVL newTree) {

        if (reference == null)
            return;

        AVL.rebuildAsAVL(reference.left, newTree);
        AVL.rebuildAsAVL(reference.right, newTree);

        reference.parent = null;
        reference.left = null;
        reference.right = null;

        newTree.addNode(
                reference,
                new AddNodeOptions(false, false)
        );

    }

    public int calcDepth (Node reference) {

        if (reference == null) {
            return 0;
        }

        if (reference.parent == null) {
            return 0;
        }

        int count = calcDepth(reference.parent);

        return ++count;

    }

    public static int calcHeight (Node reference) {

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

    public static int calcBF (Node reference) {

        int leftHeight = AVL.calcHeight(reference.left);
        int rightHeight = AVL.calcHeight(reference.right);

        return leftHeight - rightHeight;

    }

    public Direction checkBalancing (Node reference) {

        int balancingFactor = AVL.calcBF(reference);

        if (Math.abs(balancingFactor) <= 1) {
            return BALANCED;
        }

        return balancingFactor > 0 ? LEFT : RIGHT;

    }

    private void balanceUp (Node reference, boolean printRotate) {

        if (reference != null) {

            Direction unbDirection = checkBalancing(reference);

            if (unbDirection != BALANCED)
                rotate(reference, unbDirection, printRotate);

            balanceUp(reference.parent, printRotate);

        }

    }

    private void rotate(Node reference, Direction direction, boolean printRotate) {
        Node childToRotate = direction.getOf(reference);

        if (childToRotate == null)
            return;

        int childBf = AVL.calcBF(childToRotate);
        boolean doubleRotate = false;
        if (childBf > 0 && direction == RIGHT) {
            rotate(childToRotate, LEFT, false);
            childToRotate = direction.getOf(reference);

            if (printRotate) {
                System.out.printf("Rotação dupla a esquerda em %d realizada!\n", reference.element);
                doubleRotate = true;
            }
        } else if (childBf < 0 && direction == LEFT) {
            rotate(childToRotate, RIGHT, false);
            childToRotate = direction.getOf(reference);

            if (printRotate) {
                System.out.printf("Rotação dupla a direita em %d realizada!\n", reference.element);
                doubleRotate = true;
            }
        }

        Direction rotateDirection = direction == LEFT ? RIGHT : LEFT;

        Node orphanSubTree = rotateDirection.getOf(childToRotate);

        Node parent = reference.parent;
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
            System.out.printf("Rotação simples em %d realizada!\n", reference.element);
    }

    public void addNode (Node newNode, AddNodeOptions options) {

        if (this.root == null) {
            this.root = newNode;
        } else {
            addRecursively(newNode, this.root, options.printRotate());
        }

        if (options.printAdd()) {
            System.out.printf("O elemento %d foi adicionado a árvore!\n", newNode.element);
        }

    }

    public void addNode (Node newNode) {

        if (this.root == null) {
            this.root = newNode;
        } else {
            addRecursively(newNode, this.root, false);
        }

    }

    private void addRecursively (Node newTerm, Node current, boolean printRotate) {

        Direction addDirection = newTerm.element < current.element ? LEFT : RIGHT;
        Node nodeInDirection = addDirection.getOf(current);

        if (nodeInDirection == null) {
            addDirection.setOn(current, newTerm);
            newTerm.parent = current;
            this.balanceUp(current, printRotate);
        } else {
            addRecursively(newTerm, Objects.requireNonNull(addDirection.getOf(current)), printRotate);
        }

    }

    public void print (Node reference, RecursionOrder order) {

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
