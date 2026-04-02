package ricardo_paulo.net;

import ricardo_paulo.net.BinarySearchTree.BST;
import ricardo_paulo.net.BinarySearchTree.Components.NodeSearchResult;

public class Tests {
    public static void main (String[] args) {
        BST tree = new BST();
        tree.addNode(8);
        tree.addNode(3);
        tree.addNode(1);
        tree.addNode(6);
        tree.addNode(4);
        tree.addNode(7);
        tree.addNode(10);
        tree.addNode(14);
        tree.addNode(13);

        int reference = 6;
        NodeSearchResult resultPredecessor = tree.findInOrder(reference, false);
        NodeSearchResult resultSucessor = tree.findInOrder(reference, true);

        if (resultPredecessor.found) {
            System.out.println(resultPredecessor.node.element);
        } else {
            System.out.println("Predecessor não encontrado");
        }

        System.out.println(reference);

        if (resultSucessor.found) {
            System.out.println(resultSucessor.node.element);
        } else {
            System.out.println("Sucessor não encontrado.");
        }
    }
}
