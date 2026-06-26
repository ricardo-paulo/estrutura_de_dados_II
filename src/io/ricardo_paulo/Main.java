package io.ricardo_paulo;

import io.ricardo_paulo.HashTable.HashTable;

public class Main {

    public static void main (String[] args) {

        HashTable dictionary = new HashTable();

        System.out.println("Inserindo dados...");

        // minhaTabela.insert("Ana", "Engenheira");
        //
        // minhaTabela.insert("Carlos", "Professor");
        //
        // minhaTabela.insert("Beatriz", "Designer");
        // minhaTabela.insert("Daniel", "Desenvolvedor");
        // minhaTabela.insert("Eduarda", "Médica");
        // minhaTabela.insert("Walisson", "Professor");
        // minhaTabela.insert("Pedro Paulo", "Médico");

        // Mostra a estrutura com as colisões resolvidas por setas (->)
        // minhaTabela.printTable();

        // Testando a busca
        // System.out.println("Buscando 'Beatriz': " + minhaTabela.search("Beatriz"));
        // System.out.println("Buscando 'Eduarda': " + minhaTabela.search("Eduarda"));
        // System.out.println("Buscando 'Inexistente': " + minhaTabela.search("Lucas"));

        // Testando a remoção
        // System.out.println("\nRemovendo 'Carlos'...");
        // minhaTabela.remove("Carlos");

        // Mostra a tabela após a remoção
        dictionary.printTable();

    }
}
