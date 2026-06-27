package io.ricardo_paulo.HashTable;

import io.ricardo_paulo.HashTable.data.Data;
import io.ricardo_paulo.HashTable.data.DictionaryLists;
import io.ricardo_paulo.HashTable.enums.CollisionResolver;
import io.ricardo_paulo.HashTable.enums.HashFunc;

public class HashTable {

    public Node[] table;
    private int capacity;
    private final HashFunc hashFunc;
    private int size = 0;
    private int readPercentage = 100;

    public HashTable(int capacity, HashFunc hashFunc) {

        this.capacity = capacity;
        this.hashFunc = hashFunc;
        this.table = new Node[this.capacity];
        this.readPercentage = 0;
    }

    public HashTable(HashFunc hashFunc) {

        DictionaryLists rawLists = new Data().getDictionary(100);
        String[] words = rawLists.getWords();
        String[] pos = rawLists.getPos();
        String[] definitions = rawLists.getDefinitions();

        this.hashFunc = hashFunc;
        this.capacity = words.length;
        this.table = new Node[this.capacity];

        for (int w = 0; w < words.length; w++) {

            this.insert(words[w], pos[w], definitions[w]);

        }

    }

    public HashTable(HashFunc hashFunc, int percent) {

        if (percent <= 0 || percent > 100) {
            System.out.println("O percentual de carregamento dos dados passado é inválido. Ele deve ser: 0 < p ≤ 100");
        }

        this.readPercentage = percent;
        this.hashFunc = hashFunc;
        DictionaryLists rawLists = new Data().getDictionary(percent);
        String[] words = rawLists.getWords();
        String[] pos = rawLists.getPos();
        String[] definitions = rawLists.getDefinitions();

        this.capacity = rawLists.getCapacity();
        this.table = new Node[capacity];

        for (int w = 0; w < words.length; w++) {

            this.insert(words[w], pos[w], definitions[w]);

        }

    }

    // 3. Operação de Inserção (Put)
    public void insert(String key, String pos, String definition) {
        int index = hashFunc.hash(key, capacity);
        Node currentNode = table[index];
        Node newNode = new Node(key, pos, definition);
        boolean addedNode;

        // Caso 1: A posição está vazia (Sem colisão)
        if (currentNode == null) {
            table[index] = new Node(key, pos, definition);
            addedNode = true;
        } else {
            addedNode = CollisionResolver.SEPARATED_CHAINING.resolve(this, currentNode, index, newNode);
        }

        if (addedNode)
            size++;

        if (getLoadFactor() >= 0.75)
            rehash();

    }

    // 4. Operação de Busca (Get)
    public Node search(String key) {
        int index = hashFunc.hash(key, capacity);
        Node currentNode = table[index];

        // Percorre a lista encadeada no índice gerado
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode; // Encontrou!
            }
            currentNode = currentNode.next;
        }
        return null; // Não encontrou
    }

    // 5. Operação de Remoção (Delete)
    public boolean remove(String key) {
        int index = hashFunc.hash(key, capacity);
        Node currentNode = table[index];
        Node previousNode = null;

        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                // Se for o primeiro nó da lista daquela posição
                if (previousNode == null) {
                    table[index] = currentNode.next;
                } else {
                    // Se estiver no meio ou fim, "pula" o nó atual
                    previousNode.next = currentNode.next;
                }
                size--;
                return true; // Removido com sucesso
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        return false; // Chave não encontrada
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public double getLoadFactor() {
        return (double) size/capacity;
    }

    public void rehash() {
        int newCapacity = this.capacity * 2;
        HashTable newHashTable = new HashTable(newCapacity, this.hashFunc);

        for (Node n : this.table) {
            if (n != null) {
                insertRecursively(newHashTable, n);
            }
        }

        this.table = newHashTable.table.clone();
        this.capacity = newCapacity;
        this.size = newHashTable.size;
    }

    private void insertRecursively(HashTable newHashTable, Node current) {

        if (current == null)
            return;

        insertRecursively(newHashTable, current.next);
        newHashTable.insert(current.key, current.pos, current.definition);

    }

    // Método auxiliar para exibir a tabela na aula
    public void printTable() {

        System.out.println("\n=== ESTRUTURA ATUAL DA TABELA HASH ===");

        for (int i = 0; i < capacity; i++) {
            System.out.print("Posição [" + i + "]: ");
            Node currentNode = table[i];
            if (currentNode == null) {
                System.out.print("NULL");
            } else {
                while (currentNode != null) {
                    System.out.printf("{ %s => %s, %s }", currentNode.key, currentNode.pos, currentNode.definition);
                    if (currentNode.next != null) {
                        System.out.print(" -> ");
                    }
                    currentNode = currentNode.next;
                }
            }
            System.out.println();
        }

        System.out.println("======================================\n");

    }

}