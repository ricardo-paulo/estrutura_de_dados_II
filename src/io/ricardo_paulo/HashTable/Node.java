package io.ricardo_paulo.HashTable;

public class Node {
    String key;
    String pos;
    String definition;
    Node next;

    public Node(String key, String pos, String definition) {
        this.key = key;
        this.pos = pos;
        this.definition = definition;
        this.next = null;
    }
}
