package io.ricardo_paulo;

import io.ricardo_paulo.HashTable.enums.CollisionResolver;
import io.ricardo_paulo.HashTable.enums.HashFunc;
import io.ricardo_paulo.HashTable.HashTable;

public class Main {

    public static void main (String[] args) {

        System.out.println("Inserindo dados...");
        HashTable dictionary50 = new HashTable(HashFunc.MULTIPLICATION, CollisionResolver.SEPARATED_CHAINING,50);
        HashTable dictionary90 = new HashTable(HashFunc.MULTIPLICATION, CollisionResolver.SEPARATED_CHAINING, 90);
        HashTable dictionary = new HashTable(HashFunc.MULTIPLICATION, CollisionResolver.SEPARATED_CHAINING);

        System.out.println("============= TESTE DE TESTES DE EXECUÇÃO =============");

        // Tempo de execução com a tabela 100% preenchida.
        System.out.println("==> HASH TABLE 100% PREENCHIDA");
        System.out.print("Tempo de execução de uma pesquisa: ");
        showExecTime(() -> dictionary.search("Control"));

        System.out.print("Tempo de execução de uma remoção: ");
        showExecTime(() -> dictionary.remove("Calendar"));

        System.out.print("Tempo de execução de uma inserção: ");
        showExecTime(() -> dictionary.insert("Herbaliste", "baba", "poiaeb"));

        System.out.println("Fator de carga: " + dictionary.getLoadFactor());
        System.out.println("Itens adicionados: " + dictionary.getSize());
        System.out.println("Capacidade: " + dictionary.getCapacity());
        System.out.println("Número de colisões: " + dictionary.getCollisionsCount());

        System.out.println();

        // Tempo de execução de uma tabela 50% preenchida.
        System.out.println("==> HASH TABLE 50% PREENCHIDA");
        System.out.print("Tempo de execução de uma pesquisa: ");
        showExecTime(() -> dictionary50.search("Control"));

        System.out.print("Tempo de execução de uma remoção: ");
        showExecTime(() -> dictionary50.remove("Calendar"));

        System.out.print("Tempo de execução de uma inserção: ");
        showExecTime(() -> dictionary50.insert("Herbaliste", "baba", "poiaeb"));

        System.out.println("Fator de carga: " + dictionary50.getLoadFactor());
        System.out.println("Itens adicionados: " + dictionary50.getSize());
        System.out.println("Capacidade: " + dictionary50.getCapacity());
        System.out.println("Número de colisões: " + dictionary50.getCollisionsCount());

        System.out.println();

        // Tempo de execução de uma tabela 90% preenchida.
        System.out.println("==> HASH TABLE 90% PREENCHIDA");
        System.out.print("Tempo de execução de uma pesquisa: ");
        showExecTime(() -> dictionary90.search("Control"));

        System.out.print("Tempo de execução de uma remoção: ");
        showExecTime(() -> dictionary90.remove("Calendar"));

        System.out.print("Tempo de execução de uma inserção: ");
        showExecTime(() -> dictionary90.insert("Herbaliste", "baba", "poiaeb"));

        System.out.println("Fator de carga: " + dictionary90.getLoadFactor());
        System.out.println("Itens adicionados: " + dictionary90.getSize());
        System.out.println("Capacidade: " + dictionary90.getCapacity());
        System.out.println("Número de colisões: " + dictionary90.getCollisionsCount());

        System.out.println();

        System.out.println("============= SIMULAÇÃO DE REHASHING =============");
        HashTable reHashing = new HashTable(HashFunc.MULTIPLICATION, CollisionResolver.SEPARATED_CHAINING);
        System.out.println("Fator de carga original: " + reHashing.getLoadFactor());
        reHashing.rehash();
        System.out.println("Fator de carga após redimensionamento: " + reHashing.getLoadFactor());

        System.out.println("============= TESTE DE COLISÕES =============");
        HashTable colMulti = new HashTable(HashFunc.MULTIPLICATION, CollisionResolver.SEPARATED_CHAINING);
        HashTable colDjb2 = new HashTable(HashFunc.DJB2, CollisionResolver.SEPARATED_CHAINING);

        System.out.println("Colisões com o Método de Multiplicação: " + colMulti.getCollisionsCount());
        System.out.println("Colisões com o Algorítmo DJB2: " + colDjb2.getCollisionsCount());

    }

    private static void showExecTime(Runnable tSupplier) {
        long start = System.nanoTime();
        tSupplier.run();
        long finish = System.nanoTime();

        System.out.printf("%d ns\n", finish - start);
    }

}
