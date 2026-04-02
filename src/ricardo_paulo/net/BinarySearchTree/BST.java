package ricardo_paulo.net.BinarySearchTree;

import ricardo_paulo.net.BinarySearchTree.Components.BST_FIND_LAST_DIRECTION;
import ricardo_paulo.net.BinarySearchTree.Components.BST_PRINT_ORDER;
import ricardo_paulo.net.BinarySearchTree.Components.NodeSearchResult;

import java.util.ArrayList;

public class BST {

    public Node root;

    public BST () {
        this.root = null;
    }

    public void addNode (Node newNode) {
        if (root == null) {
            root = newNode;
        } else {
            addNodeRecursively(newNode, root);
        }
    }

    public void addNode (int newNodeElement) {
        Node newNode = new Node(newNodeElement);

        if (root == null) {
            root = newNode;
        } else {
            addNodeRecursively(newNode, root);
        }
    }

    private void addNodeRecursively (Node newNode, Node current) {
        boolean newNodeToLeft = newNode.element < current.element;

        if (newNodeToLeft) {
            if (current.left == null) {
                current.left = newNode;
            } else {
                addNodeRecursively(newNode, current.left);
            }
        } else {
            if (current.right == null) {
                current.right = newNode;
            } else {
                addNodeRecursively(newNode, current.right);
            }
        }

    }

    public NodeSearchResult searchNode (int target) {
        return searchRecursively(root, target, 0);
    }

    private NodeSearchResult searchRecursively (Node current, int target, int level) {
        level += 1;

        if (current.element == root.element && current.element == target)
            return new NodeSearchResult(current, current, level);

        if (current.left != null) {
            if (current.left.element == target)
                return new NodeSearchResult(current, current.left, level);

            NodeSearchResult result = searchRecursively(current.left, target, level);
            if (result.found)
                return result;
        }

        if (current.right != null) {
            if (current.right.element == target) {
                return new NodeSearchResult(current, current.right, level);
            }

            NodeSearchResult result = searchRecursively(current.right, target, level);
            if (result.found)
                return result;
        }

        return new NodeSearchResult();
    }

    public boolean removeNode (Node root, int target) {
        NodeSearchResult result = searchNode(target);

        if (result.found) {

            Node substitute = null;
            Node lastFromSubstitute;

            if (result.node.left != null) {
                substitute = result.node.left;
                lastFromSubstitute = findLast(substitute, BST_FIND_LAST_DIRECTION.RIGHT);
                lastFromSubstitute.right = result.node.right;;
            } else if (result.node.right != null) {
                substitute = result.node.right;
                lastFromSubstitute = findLast(substitute, BST_FIND_LAST_DIRECTION.LEFT);
                lastFromSubstitute.left = result.node.left;
            }


            if (result.node.element == root.element) {
                this.root = substitute;
            }

            // Se o elemento a ser removido for menor que o elemento do seu pai, o substituto estará a esquerda.
            // Caso contrário, estará a direita.
            // Se houver um substituto ele será colocado no lugar. Caso não haja, então a referência ficará nula.
            if (result.node.element < result.parent.element) {
                result.parent.left = substitute;
            } else {
                result.parent.right = substitute;
            }

            return true;
        }

        return false;
    }

    public Node findLast (Node current, BST_FIND_LAST_DIRECTION direction) {
        if (direction == BST_FIND_LAST_DIRECTION.LEFT) {
            if (current.left != null) {
                return findLast(current.left, BST_FIND_LAST_DIRECTION.LEFT);
            } else {
                return current;
            }
        } else {
            if (current.right != null) {
                return findLast(current.right, BST_FIND_LAST_DIRECTION.RIGHT);
            } else {
                return current;
            }
        }
    }

    public NodeSearchResult findPredecessor (int target) {
        ArrayList<Integer> numbersList = new ArrayList<>();
        NodeSearchResult refNode = searchNode(target);

        if (refNode.found) {
            findPredecessorRecursively(numbersList, root, refNode.node.element);

            for(int n : numbersList) {
                System.out.println("LISTA: " + n);
            }

            int predecessorIndex = numbersList.indexOf(target) - 1;
            Integer predecessor = numbersList.get(predecessorIndex);
            return searchNode(predecessor);
        }

        return new NodeSearchResult();
    }

    private void findPredecessorRecursively (ArrayList<Integer> array, Node current, int target) {
        if (current == null || array.contains(target))
            return;

        findPredecessorRecursively(array, current.left, target);
        array.add(current.element);
        findPredecessorRecursively(array, current.right, target);
    }

    // Questão 6
    public int nodesAmount (Node current) {
        if (current == null)
            return 0;

        return nodesAmount(current.left) + nodesAmount(current.right) + 1;
    }

    public void print (BST_PRINT_ORDER order, Node node) {
        if (node == null)
            return;

        switch (order) {
            case PRE_ORDER:
                System.out.println(node.element);
                print(order, node.left);
                print(order, node.right);
                break;

            case IN_ORDER:
                print(order, node.left);
                System.out.println(node.element);
                print(order, node.right);
                break;

            case POST_ORDER:
                print(order, node.left);
                print(order, node.right);
                System.out.println(node.element);
                break;

        }
    }

    // Questão 10
    public boolean isBST (Node current) {
        if (current == null)
            return true;

        boolean leftIsMinor = true;
        boolean rightIsMajor = true;

        if (current.left != null)
            leftIsMinor = current.left.element <= current.element;

        if (current.right != null)
            rightIsMajor = current.right.element > current.element;

        if (leftIsMinor && rightIsMajor) {
            return isBST(current.left) && isBST(current.right);
        }

        return false;
    }

}