package io.ricardo_paulo.HashTable.data;

import java.util.ArrayList;

public class DictionaryLists {

    private final ArrayList<String> words = new ArrayList<>();
    private final ArrayList<String> pos = new ArrayList<>();
    private final ArrayList<String> definitions = new ArrayList<>();

    public String[] getWords() {
        return words.toArray(new String[0]);
    }

    public void addWord(String word) {
        this.words.add(word);
    }

    public String[] getPos() {
        return pos.toArray(new String[0]);
    }

    public void addPos(String pos) {
        this.pos.add(pos);
    }

    public String[] getDefinitions() {
        return definitions.toArray(new String[0]);
    }

    public void addDefinition(String definition) {
        this.definitions.add(definition);
    }
}
