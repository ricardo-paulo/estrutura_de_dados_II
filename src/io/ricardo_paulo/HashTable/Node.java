package io.ricardo_paulo.HashTable;

class Node {
    String chave;
    String valor;
    Node proximo;

    public Node(String chave, String valor) {
        this.chave = chave;
        this.valor = valor;
        this.proximo = null;
    }
}
