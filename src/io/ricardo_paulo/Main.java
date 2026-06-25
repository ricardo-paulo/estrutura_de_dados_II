package io.ricardo_paulo;

import io.ricardo_paulo.HashTable.TabelaHashLista;

public class Main {

    public static void main (String[] args) {

        // Criamos uma tabela pequena (tamanho 4) para forçar colisões propositalmente
        TabelaHashLista minhaTabela = new TabelaHashLista(4);

        System.out.println("Inserindo dados...");

        minhaTabela.inserir("Ana", "Engenheira");

        minhaTabela.inserir("Carlos", "Professor");

        minhaTabela.inserir("Beatriz", "Designer");
        minhaTabela.inserir("Daniel", "Desenvolvedor");
        minhaTabela.inserir("Eduarda", "Médica");
        minhaTabela.inserir("Walisson", "Professor");
        minhaTabela.inserir("Pedro Paulo", "Médico");

        // Mostra a estrutura com as colisões resolvidas por setas (->)
        minhaTabela.imprimirTabela();

        // Testando a busca
        System.out.println("Buscando 'Beatriz': " + minhaTabela.buscar("Beatriz"));
        System.out.println("Buscando 'Eduarda': " + minhaTabela.buscar("Eduarda"));
        System.out.println("Buscando 'Inexistente': " + minhaTabela.buscar("Lucas"));

        // Testando a remoção
        System.out.println("\nRemovendo 'Carlos'...");
        minhaTabela.remover("Carlos");

        // Mostra a tabela após a remoção
        minhaTabela.imprimirTabela();

    }
}
