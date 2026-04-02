package ricardo_paulo.net;

import ricardo_paulo.net.BinarySearchTree.BST;
import ricardo_paulo.net.BinarySearchTree.Components.BST_FIND_LAST_DIRECTION;
import ricardo_paulo.net.BinarySearchTree.Components.BST_PRINT_ORDER;
import ricardo_paulo.net.BinarySearchTree.Components.NodeSearchResult;
import ricardo_paulo.net.BinarySearchTree.Node;

public class Main {
    public static void main (String[] args) {

        System.out.println("Questão 1");
        recursiveCount(7);

        System.out.println("Questão 2");
        System.out.println(descSum(5));

        System.out.println("Questão 3");
        System.out.println(exp(10, -3));
        System.out.println(exp(2, 5));

        // Questão 4
        System.out.println("Questão 4");
        int[] treeValues = {
                9, 19, 10, 21, 5,
                12, 18, 24, 27, 1,
                4, 8, 23, 26, 29,
                30, 27, 50, 60, 45
        };

        BST myTree  = new BST();

        for (int n : treeValues) {
            myTree.addNode(n);
            System.out.printf("%d adicionado a árvore!\n", n);
        }

        // Questão 5 | A
        // A altura da árvore é de 4 elementos.

        // Questão 5 | B
        // É o nível 3.

        // Questão 5 | C
        // Os nós folha são o 4, 7 e 13.

        // Questão 5 | D
        int[] treeValues2 = {8, 3, 1, 6, 4, 7, 10, 14, 13};
        BST myTree2 = new BST();
        for (int n : treeValues2) {
            myTree2.addNode(n);
        }

        System.out.println("Questão 5 | D");
        System.out.println("EM ORDEM");
        myTree2.print(BST_PRINT_ORDER.IN_ORDER, myTree2.root);
        System.out.println("PRÉ-ORDEM");
        myTree2.print(BST_PRINT_ORDER.PRE_ORDER, myTree2.root);
        System.out.println("PÓS-ORDEM");
        myTree2.print(BST_PRINT_ORDER.POST_ORDER, myTree2.root);

        // Questão 5 | E
        System.out.println("Questão 5 | E");
        int toRemove = 8;
        boolean removed = myTree2.removeNode(myTree2.root, toRemove);
        System.out.printf("O elemento %d foi removido: %b\n", toRemove, removed);
        myTree2.print(BST_PRINT_ORDER.PRE_ORDER, myTree2.root);

        // Questão 5 | F
        System.out.println("Questão 5 | F");
        toRemove = 3;
        removed = myTree2.removeNode(myTree2.root, toRemove);
        System.out.printf("O elemento %d foi removido: %b\n", toRemove, removed);
        myTree2.print(BST_PRINT_ORDER.PRE_ORDER, myTree2.root);

        // Questão 6
        System.out.println("Questão 6");
        System.out.printf("Quantidade de nós: %d\n", myTree2.nodesAmount(myTree2.root));

        // Questão 7
        System.out.println("Questão 7");
        System.out.printf("Menor valor da árvore: %d\n",
            myTree2.findLast(myTree2.root, BST_FIND_LAST_DIRECTION.LEFT).element);

        // Questão 8
        System.out.println("Questão 8");
        int reference = 13;
        NodeSearchResult referencePredecessor = myTree2.findInOrder(reference, false);
        NodeSearchResult referenceSucessor =  myTree2.findInOrder(reference, true);
        System.out.printf("Alvo: %d\n", reference);
        if (referencePredecessor.found)
            System.out.printf("Predecessor: %d\n", referencePredecessor.node.element);
        if (referenceSucessor.found)
            System.out.printf("Sucessor: %d\n", referenceSucessor.node.element);

        System.out.println("Questão 9");
        showInternalNodes(myTree2.root, myTree2);

        System.out.println("Questão 10");
        System.out.printf("É uma árvore binária de pesquisa: %b\n", myTree2.isBST(myTree2.root));

        System.out.println("Questão 11");
        showOdd(myTree2.root);

        myTree2.addNode(30);
        myTree2.addNode(50);
        myTree2.addNode(31);
        myTree2.addNode(26);

        System.out.println("Question 12");
        myTree2.print(BST_PRINT_ORDER.POST_ORDER, myTree2.root);
        showPair(myTree2.root);

        System.out.println("Question 13");
        NodeSearchResult searchResult = myTree2.searchNode(1);
        System.out.printf("Nível do nó %d: %d", searchResult.node.element, searchResult.level);
    }

    // Questão 1
    private static void recursiveCount(int number) {
        if (number > 0) {
            System.out.println(number);
            recursiveCount(number - 1);
        }
    }

    // Questão 2
    private static int descSum(int number) {
        if (number == 0)
            return 0;

        return number + descSum(number - 1);
    }

    // Questão 3
    private static double exp (int base, int exponent) {
        if (exponent > 0) {
            return base * exp(base, exponent - 1);
        } else if (exponent < 0) {
            return exp(base, exponent + 1) / base;
        }

        return 1;
    }

    // Questão 9
    private static void showInternalNodes (Node current, BST tree) {
        if (current != null) {
            boolean isRoot = current.element == tree.root.element;
            boolean hasLeftChild = current.left != null;
            boolean hasRightChild = current.right != null;

            if (!isRoot && (hasLeftChild || hasRightChild)) {
                System.out.printf("%d | Tem nó a direita: %b | Tem nó a esquerda: %b\n",
                        current.element, hasLeftChild, hasRightChild);
            }

            showInternalNodes(current.left, tree);
            showInternalNodes(current.right, tree);
        }
    }

    // Questão 11
    private static void showOdd (Node current) {
        if (current == null)
            return;

        showOdd(current.left);
        if (current.element % 2 != 0 && current.element < 20)
            System.out.println(current.element);
        showOdd(current.right);
    }

    // Questão 12
    private static void showPair (Node current) {
        if (current == null)
            return;

        showPair(current.left);
        showPair(current.right);
        if (current.element % 2 == 0 && current.element > 25)
            System.out.println(current.element);
    }
}