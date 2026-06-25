package io.ricardo_paulo.HashTable;

public class HashTable {

    // Atributos da Tabela Hash
    private Node[] table;
    private int capacity;

    // Construtor
    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new Node[capacity]; // Inicializa o array com posições vazias (null)
    }

    // 2. A Função Hash (Função de Dispersão)
    // Usa o hashCode nativo do Java e aplica o operador resto (%) para caber no array
    private int hashFunc(String key) {
        int hash = key.hashCode();
        return Math.abs(hash) % capacity;
    }

    // 3. Operação de Inserção (Put)
    public void insert(String key, String value) {
        int index = hashFunc(key);
        Node currentNode = table[index];

        // Caso 1: A posição está vazia (Sem colisão)
        if (currentNode == null) {
            table[index] = new Node(key, value);
            return;
        }

        // Caso 2: Há elementos na posição (Colisão!)
        // Vamos percorrer a lista encadeada naquela posição
        while (currentNode != null) {
            // Se a chave já existir, atualiza o valor (evita duplicatas)
            if (currentNode.key.equals(key)) {
                currentNode.value = value;
                return;
            }
            // Se chegou ao último nó, para a execução
            if (currentNode.next == null) {
                break;
            }
            currentNode = currentNode.next;
        }

        // Insere o novo nó no final da lista encadeada existente
        currentNode.next = new Node(key, value);
    }

    // 4. Operação de Busca (Get)
    public String search(String key) {
        int index = hashFunc(key);
        Node currentNode = table[index];

        // Percorre a lista encadeada no índice gerado
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode.value; // Encontrou!
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
                    System.out.print("{" + currentNode.key + " => " + currentNode.value + "}");
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