package io.ricardo_paulo;

import io.ricardo_paulo.HashTable.enums.HashFunc;
import io.ricardo_paulo.HashTable.HashTable;

public class Main {

    public static void main (String[] args) {

        System.out.println("Inserindo dados...");
        HashTable dictionary50 = new HashTable(HashFunc.MULTIPLICATION, 50);
        HashTable dictionary90 = new HashTable(HashFunc.MULTIPLICATION, 90);
        HashTable dictionary = new HashTable(HashFunc.MULTIPLICATION);

        // Tempo de execução com a tabela 100% preenchida.
        System.out.println("============== HASH TABLE 100% PREENCHIDA ==============");
        System.out.print("Tempo de execução de uma pesquisa: ");
        showExecTime(() -> dictionary.search("Control"));

        System.out.print("Tempo de execução de uma remoção: ");
        showExecTime(() -> dictionary.remove("Calendar"));

        System.out.print("Tempo de execução de uma inserção: ");
        showExecTime(() -> dictionary.insert("Herbaliste", "baba", "poiaeb"));

        System.out.println("Fator de carga: " + dictionary.getLoadFactor());
        System.out.println("Itens adicionados: " + dictionary.getSize());
        System.out.println("Capacidade: " + dictionary.getCapacity());

        System.out.println();

        // Tempo de execução de uma tabela 50% preenchida.
        System.out.println("============== HASH TABLE 50% PREENCHIDA ==============");
        System.out.print("Tempo de execução de uma pesquisa: ");
        showExecTime(() -> dictionary50.search("Control"));

        System.out.print("Tempo de execução de uma remoção: ");
        showExecTime(() -> dictionary50.remove("Calendar"));

        System.out.print("Tempo de execução de uma inserção: ");
        showExecTime(() -> dictionary50.insert("Herbaliste", "baba", "poiaeb"));

        System.out.println("Fator de carga: " + dictionary50.getLoadFactor());
        System.out.println("Itens adicionados: " + dictionary50.getSize());
        System.out.println("Capacidade: " + dictionary50.getCapacity());

        System.out.println();

        // Tempo de execução de uma tabela 90% preenchida.
        System.out.println("============== HASH TABLE 90% PREENCHIDA ==============");
        System.out.print("Tempo de execução de uma pesquisa: ");
        showExecTime(() -> dictionary90.search("Control"));

        System.out.print("Tempo de execução de uma remoção: ");
        showExecTime(() -> dictionary90.remove("Calendar"));

        System.out.print("Tempo de execução de uma inserção: ");
        showExecTime(() -> dictionary90.insert("Herbaliste", "baba", "poiaeb"));

        System.out.println("Fator de carga: " + dictionary90.getLoadFactor());
        System.out.println("Itens adicionados: " + dictionary90.getSize());
        System.out.println("Capacidade: " + dictionary90.getCapacity());

        // ---------------------------------------------------------------------

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

    private static void showExecTime(Runnable tSupplier) {
        long start = System.nanoTime();
        tSupplier.run();
        long finish = System.nanoTime();

        System.out.printf("%d ns\n", finish - start);
    }

}
