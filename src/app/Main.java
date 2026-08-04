package app;

import estruturas.arvore.AVL;
import estruturas.arvore.BST;
import estruturas.arvore.BTree;
import estruturas.fila.Fila;
import estruturas.lista.ListaDupla;
import estruturas.lista.ListaSimples;
import estruturas.pilha.Pilha;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Aplicação principal de console.
 */
public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> menuPilha();
                case 2 -> menuFila();
                case 3 -> menuListaSimples();
                case 4 -> menuListaDupla();
                case 5 -> menuBST();
                case 6 -> menuAVL();
                case 7 -> menuArvoreB();
                case 0 -> System.out.println("Programa encerrado.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n===================================");
        System.out.println("Biblioteca de Estruturas de Dados");
        System.out.println("===================================");
        System.out.println("1 - Pilha");
        System.out.println("2 - Fila");
        System.out.println("3 - Lista Simples");
        System.out.println("4 - Lista Dupla");
        System.out.println("5 - BST");
        System.out.println("6 - AVL");
        System.out.println("7 - Árvore B");
        System.out.println("0 - Encerrar");
    }

    private static void menuPilha() {
        Pilha<Integer> pilha = Pilha.create(5);
        int opcao;

        do {
            System.out.println("\n--- PILHA ---");
            System.out.println("Pilha atual: " + pilha);
            System.out.println("1 - create");
            System.out.println("2 - push");
            System.out.println("3 - pop");
            System.out.println("4 - top");
            System.out.println("5 - isEmpty");
            System.out.println("6 - isFull");
            System.out.println("7 - Demonstração automática");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Opção: ");

            try {
                switch (opcao) {
                    case 1 -> {
                        int capacidade = lerInteiro("Capacidade da nova pilha: ");
                        pilha = Pilha.create(capacidade);
                    }
                    case 2 -> pilha.push(lerInteiro("Valor inteiro: "));
                    case 3 -> System.out.println("Removido: " + pilha.pop());
                    case 4 -> System.out.println("Topo: " + pilha.top());
                    case 5 -> System.out.println("Está vazia? " + pilha.isEmpty());
                    case 6 -> System.out.println("Está cheia? " + pilha.isFull());
                    case 7 -> demonstrarPilha();
                    case 0 -> {
                    }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void demonstrarPilha() {
        Pilha<Integer> numeros = Pilha.create(4);
        numeros.push(10);
        numeros.push(20);
        numeros.push(30);

        System.out.println("Conjunto numérico: " + numeros);
        System.out.println("top(): " + numeros.top());
        System.out.println("pop(): " + numeros.pop());
        System.out.println("Depois do pop: " + numeros);
        System.out.println("isEmpty(): " + numeros.isEmpty());
        System.out.println("isFull(): " + numeros.isFull());

        Pilha<String> palavras = Pilha.create(3);
        palavras.push("Java");
        palavras.push("POO");
        palavras.push("Generics");

        System.out.println("Conjunto de textos: " + palavras);
        System.out.println("isFull(): " + palavras.isFull());
    }

    private static void menuFila() {
        Fila<Integer> fila = Fila.create(5);
        int opcao;

        do {
            System.out.println("\n--- FILA ---");
            System.out.println("Fila atual: " + fila);
            System.out.println("1 - create");
            System.out.println("2 - enqueue");
            System.out.println("3 - dequeue");
            System.out.println("4 - head");
            System.out.println("5 - isEmpty");
            System.out.println("6 - isFull");
            System.out.println("7 - Demonstração automática");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Opção: ");

            try {
                switch (opcao) {
                    case 1 -> {
                        int capacidade = lerInteiro("Capacidade da nova fila: ");
                        fila = Fila.create(capacidade);
                    }
                    case 2 -> fila.enqueue(lerInteiro("Valor inteiro: "));
                    case 3 -> System.out.println("Removido: " + fila.dequeue());
                    case 4 -> System.out.println("Cabeça: " + fila.head());
                    case 5 -> System.out.println("Está vazia? " + fila.isEmpty());
                    case 6 -> System.out.println("Está cheia? " + fila.isFull());
                    case 7 -> demonstrarFila();
                    case 0 -> {
                    }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void demonstrarFila() {
        Fila<Integer> numeros = Fila.create(4);
        numeros.enqueue(5);
        numeros.enqueue(10);
        numeros.enqueue(15);

        System.out.println("Conjunto numérico: " + numeros);
        System.out.println("head(): " + numeros.head());
        System.out.println("dequeue(): " + numeros.dequeue());
        System.out.println("Depois do dequeue: " + numeros);

        Fila<String> atendimento = Fila.create(3);
        atendimento.enqueue("Ana");
        atendimento.enqueue("Bruno");
        atendimento.enqueue("Carlos");

        System.out.println("Conjunto de textos: " + atendimento);
        System.out.println("isFull(): " + atendimento.isFull());
    }
}