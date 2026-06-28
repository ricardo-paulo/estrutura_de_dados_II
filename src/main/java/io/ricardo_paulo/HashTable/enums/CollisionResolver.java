package io.ricardo_paulo.HashTable.enums;

import io.ricardo_paulo.HashTable.HashTable;
import io.ricardo_paulo.HashTable.Node;
import io.ricardo_paulo.HashTable.utils.MultiFunction;

public enum CollisionResolver {

    SEPARATED_CHAINING((hashTable, currentNode, index, newNode) -> {

        while (currentNode != null) {
            // Se a chave já existir, atualiza o valor (evita duplicatas)
            if (currentNode.key.equals(newNode.key)) {
                currentNode.pos = newNode.pos;
                currentNode.definition = newNode.definition;
                return false;
            }
            // Se chegou ao último nó, para a execução
            if (currentNode.next == null) {
                break;
            }
            currentNode = currentNode.next;
        }

        assert currentNode != null;

        // Insere o novo nó no final da lista encadeada existente
        currentNode.next = newNode;
        return true;
    }),
    OPEN_ADDRESSING((hashTable,currentNode, index, newNode) -> {

        int currentIndex = index;
        currentNode = hashTable.table[currentIndex];

        while (currentNode != null) {

            currentIndex++;

            if (currentIndex >= hashTable.table.length) {
                currentIndex = 0;
            } else if (currentIndex == index) {
                return false;
            }

            currentNode = hashTable.table[currentIndex];

        }

        hashTable.table[currentIndex] = newNode;
        return true;

    });

    private final MultiFunction<HashTable, Node, Integer, Node, Boolean> collisionResolver;

     CollisionResolver(MultiFunction<HashTable, Node, Integer, Node, Boolean> collisionResolver) {
        this.collisionResolver = collisionResolver;
    }

    public Boolean resolve(HashTable hashTable, Node currentNode, int index, Node newNode) {
        return collisionResolver.apply(hashTable, currentNode, index, newNode);
    }

}
