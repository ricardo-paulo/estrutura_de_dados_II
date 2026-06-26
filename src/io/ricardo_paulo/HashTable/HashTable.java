package io.ricardo_paulo.HashTable;

import io.ricardo_paulo.HashTable.DictionaryLists.Data;
import io.ricardo_paulo.HashTable.DictionaryLists.DictionaryLists;

public class HashTable {

    // Atributos da Tabela Hash
    private final Node[] table;
    private final int capacity;

    // Construtor
    public HashTable() {

        DictionaryLists rawLists = new Data().getDictionary(100);
        String[] words = rawLists.getWords();
        String[] pos = rawLists.getPos();
        String[] definitions = rawLists.getDefinitions();

        this.capacity = words.length;
        this.table = new Node[capacity];

        for (int w = 0; w < capacity - 1; w++) {

            this.insert(words[w], pos[w], definitions[w]);

        }

    }

    public HashTable(int percent) {

        if (percent <= 0 || percent > 100) {
            System.out.println("O percentual de carregamento dos dados passado é inválido. Ele deve ser: 0 < p ≤ 100");
        }

        DictionaryLists rawLists = new Data().getDictionary(percent);
        String[] words = rawLists.getWords();
        String[] pos = rawLists.getPos();
        String[] definitions = rawLists.getDefinitions();

        this.capacity = words.length;
        this.table = new Node[capacity];

        for (int w = 0; w < capacity - 1; w++) {

            this.insert(words[w], pos[w], definitions[w]);

        }

    }

    // 2. A Função Hash (Função de Dispersão)
    // Usa o hashCode nativo do Java e aplica o operador resto (%) para caber no array
    private int hashFunc(String key) {
        int hash = key.hashCode();
        return Math.abs(hash) % capacity;
    }

    // 3. Operação de Inserção (Put)
    public void insert(String key, String pos, String definition) {
        int index = hashFunc(key);
        Node currentNode = table[index];

        // Caso 1: A posição está vazia (Sem colisão)
        if (currentNode == null) {
            table[index] = new Node(key, pos, definition);
            return;
        }

        // Caso 2: Há elementos na posição (Colisão!)
        // Vamos percorrer a lista encadeada naquela posição
        while (currentNode != null) {
            // Se a chave já existir, atualiza o valor (evita duplicatas)
            if (currentNode.key.equals(key)) {
                currentNode.pos = pos;
                currentNode.definition = definition;
                return;
            }
            // Se chegou ao último nó, para a execução
            if (currentNode.next == null) {
                break;
            }
            currentNode = currentNode.next;
        }

        // Insere o novo nó no final da lista encadeada existente
        currentNode.next = new Node(key, pos, definition);
    }

    // 4. Operação de Busca (Get)
    public Node search(String key) {
        int index = hashFunc(key);
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
        int index = hashFunc(key);
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
                return true; // Removido com sucesso
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        return false; // Chave não encontrada
    }

    public int getSize() {
        return capacity;
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
                    // System.out.print("{" + currentNode.key + " => " + currentNode.value + "}");
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