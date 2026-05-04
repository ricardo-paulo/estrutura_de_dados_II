package net.ricardo_paulo;

import net.ricardo_paulo.AVL.AVL;
import net.ricardo_paulo.BST.BST;
import net.ricardo_paulo.Components.AddNodeOptions;
import net.ricardo_paulo.BST.Node;
import net.ricardo_paulo.Components.RecursionOrder;
import static net.ricardo_paulo.Components.RecursionOrder.*;

import java.util.function.Consumer;

public class Main {
    public static void main (String[] args) {

        // QUESTÃO 1
        BST bst = new BST();
        bst.addNode(50);
        bst.addNode(10);
        bst.addNode(5);
        bst.addNode(1);
        bst.addNode(12);
        bst.addNode(24);
        bst.addNode(20);
        bst.addNode(30);
        bst.addNode(81);
        bst.addNode(80);
        bst.addNode(75);

        System.out.println("QUESTÃO 1");
        forEachNode(bst.root, PRE_ORDER, (current) ->
                System.out.printf("{ Elemento: %d | Fator de Balanceamento: %d}\n",
                current.element,
                AVL.calcBF(current)
                )
        );

        // QUESTÃO 2

        AVL firstAVL = new AVL();
        AVL.rebuildAsAVL(bst.root, firstAVL);

        System.out.println("QUESTÃO 2");
        firstAVL.print(firstAVL.root, IN_ORDER);

        // QUESTÃO 3

        AVL secondAVL = new AVL();
        secondAVL.addNode(new Node(10));
        secondAVL.addNode(new Node(15));
        secondAVL.addNode(new Node(20));
        secondAVL.addNode(new Node(30));
        secondAVL.addNode(new Node(40));
        secondAVL.addNode(new Node(50));
        secondAVL.addNode(new Node(25));
        secondAVL.addNode(new Node(70));
        secondAVL.addNode(new Node(55));
        secondAVL.addNode(new Node(12));
        secondAVL.addNode(new Node(68));

        // QUESTÃO 4
        AVL thirdTree = new AVL();

        System.out.println("QUESTÃO 4");

        thirdTree.addNode(
                new Node(25),
                new AddNodeOptions(true, true));
        thirdTree.addNode(
                new Node(10),
                new AddNodeOptions(true, true));
        thirdTree.addNode(
                new Node(21),
                new AddNodeOptions(true, true));
        thirdTree.addNode(
                new Node(5),
                new AddNodeOptions(true, true));
        thirdTree.addNode(
                new Node(12),
                new AddNodeOptions(true, true));
        thirdTree.addNode(
                new Node(18),
                new AddNodeOptions(true, true));
        thirdTree.addNode(
                new Node(24),
                new AddNodeOptions(true, true));
        thirdTree.addNode(
                new Node(27),
                new AddNodeOptions(true, true));
        thirdTree.addNode(
                new Node(1),
                new AddNodeOptions(true, true));
        thirdTree.addNode(
                new Node(4),
                new AddNodeOptions(true, true));
        thirdTree.addNode(
                new Node(8),
                new AddNodeOptions(true, true));
        thirdTree.addNode(
                new Node(23),
                new AddNodeOptions(true, true));
        thirdTree.addNode(
                new Node(26),
                new AddNodeOptions(true, true));
        thirdTree.addNode(
                new Node(29),
                new AddNodeOptions(true, true));
        thirdTree.addNode(
                new Node(30),
                new AddNodeOptions(true, true));

        // TODO QUESTÃO 5 - Após implementar os tipos genéricos e refatorar o restante do código.
        // AVL nameTree = new AVL();
        // char[] myName = "PauloRicardoRod".toCharArray();
        // for (char c : myName) {
        //
        // }

    }

    private static void forEachNode (Node current, RecursionOrder order, Consumer<Node> lambda) {

        if (current == null)
            return;

        switch (order) {

            case PRE_ORDER -> {
                lambda.accept(current);
                forEachNode(current.left, order, lambda);
                forEachNode(current.right, order, lambda);
            }

            case IN_ORDER -> {
                forEachNode(current.left, order, lambda);
                lambda.accept(current);
                forEachNode(current.right, order, lambda);
            }

            case POST_ORDER -> {
                forEachNode(current.left, order, lambda);
                forEachNode(current.right, order, lambda);
                lambda.accept(current);
            }

        }

    }
}
