package io.ricardo_paulo.HashTable;

public class Node {
    public String key;
    public String pos;
    public String definition;
    public Node next;

    public Node(String key, String pos, String definition) {
        this.key = key;
        this.pos = pos;
        this.definition = definition;
        this.next = null;
    }

    @Override
    public String toString() {
        return "{%s, %s, %s}".formatted(key, pos, definition);
    }
}
