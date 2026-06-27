package io.ricardo_paulo;

import io.ricardo_paulo.HashTable.enums.HashFunc;
import io.ricardo_paulo.HashTable.HashTable;
import java.util.function.Supplier;

public class Main {

    public static void main (String[] args) {

        System.out.println("Inserindo dados...");
        HashTable dictionary = new HashTable(HashFunc.MULTIPLICATION, 50);

        // Tempo de execução de uma pesquisa.
        System.out.print("Tempo de execução de uma pesquisa: ");
        showExecTime(() -> dictionary.search("Control"));

        System.out.print("Tempo de execução de uma remoção: ");
        showExecTime(() -> dictionary.remove("Calendar"));

        System.out.print("Tempo de execução de uma inserção: ");
        showExecTime(() -> dictionary.remove("Herbalist"));

        // dictionary.printTable();
        // System.out.println(dictionary.getSize());

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
        // dictionary.printTable();

    }

    private static <T> T showExecTime(Supplier<T> op) {
        long start = System.nanoTime();
        T result = op.get();
        long finish = System.nanoTime();

        System.out.printf("%d ns\n", finish - start);

        return result;

    }

}
