package io.ricardo_paulo.HashTable;

class Node {
    String key;
    String value;
    Node next;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
