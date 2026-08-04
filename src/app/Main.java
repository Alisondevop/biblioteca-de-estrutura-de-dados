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
 *
 * Este programa funciona como um "menu de testes" interativo para uma
 * biblioteca de estruturas de dados (Pilha, Fila, Listas, BST, AVL e Árvore B).
 * Cada estrutura possui seu próprio submenu, permitindo ao usuário chamar
 * manualmente os métodos (insert, remove, search, etc.) ou rodar uma
 * demonstração automática pré-programada.
 */
public class Main {
    // Scanner único e estático, compartilhado por toda a aplicação,
    // usado para ler as entradas do usuário via console.
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        // Loop principal do programa: exibe o menu, lê a opção escolhida
        // e direciona para o submenu correspondente até o usuário digitar 0.
        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opção: ");

            // switch com "arrow syntax" (Java 14+): cada case chama o
            // submenu da estrutura de dados selecionada.
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

    // Imprime o menu principal com as opções de estruturas de dados disponíveis.
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

    // ===================== PILHA =====================

    /**
     * Submenu interativo da Pilha.
     * Permite criar uma nova pilha, empilhar/desempilhar valores,
     * consultar o topo e verificar se está vazia/cheia.
     */
    private static void menuPilha() {
        // Pilha inicial com capacidade fixa de 5 elementos.
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

            // Operações de pilha podem lançar exceções (ex.: pop em pilha
            // vazia ou push em pilha cheia), por isso o bloco try/catch.
            try {
                switch (opcao) {
                    case 1 -> {
                        // Recria a pilha com uma nova capacidade definida pelo usuário.
                        int capacidade = lerInteiro("Capacidade da nova pilha: ");
                        pilha = Pilha.create(capacidade);
                    }
                    case 2 -> pilha.push(lerInteiro("Valor inteiro: "));
                    case 3 -> System.out.println("Removido: " + pilha.pop());
                    case 4 -> System.out.println("Topo: " + pilha.top());
                    case 5 -> System.out.println("Está vazia? " + pilha.isEmpty());
                    case 6 -> System.out.println("Está cheia? " + pilha.isFull());
                    case 7 -> demonstrarPilha();
                    case 0 -> { } // Não faz nada, apenas sai do loop na condição do "while".
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                // Captura erros de estado inválido (pilha cheia/vazia) ou
                // argumentos inválidos, exibindo a mensagem ao usuário.
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    /**
     * Demonstração automática do uso da Pilha, mostrando as principais
     * operações com um conjunto de números e um conjunto de textos.
     */
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

        // Demonstra que a Pilha também funciona com tipo genérico String.
        Pilha<String> palavras = Pilha.create(3);
        palavras.push("Java");
        palavras.push("POO");
        palavras.push("Generics");

        System.out.println("Conjunto de textos: " + palavras);
        System.out.println("isFull(): " + palavras.isFull());
    }

    // ===================== FILA =====================

    /**
     * Submenu interativo da Fila.
     * Permite criar uma nova fila, enfileirar/desenfileirar valores,
     * consultar a cabeça e verificar se está vazia/cheia.
     */
    private static void menuFila() {
        // Fila inicial com capacidade fixa de 5 elementos.
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

            // Assim como na pilha, operações de fila podem lançar exceções
            // de estado inválido (fila cheia/vazia).
            try {
                switch (opcao) {
                    case 1 -> {
                        // Recria a fila com uma nova capacidade definida pelo usuário.
                        int capacidade = lerInteiro("Capacidade da nova fila: ");
                        fila = Fila.create(capacidade);
                    }
                    case 2 -> fila.enqueue(lerInteiro("Valor inteiro: "));
                    case 3 -> System.out.println("Removido: " + fila.dequeue());
                    case 4 -> System.out.println("Cabeça: " + fila.head());
                    case 5 -> System.out.println("Está vazia? " + fila.isEmpty());
                    case 6 -> System.out.println("Está cheia? " + fila.isFull());
                    case 7 -> demonstrarFila();
                    case 0 -> { }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    /**
     * Demonstração automática do uso da Fila, mostrando as principais
     * operações com um conjunto de números e um conjunto de textos.
     */
    private static void demonstrarFila() {
        Fila<Integer> numeros = Fila.create(4);
        numeros.enqueue(5);
        numeros.enqueue(10);
        numeros.enqueue(15);

        System.out.println("Conjunto numérico: " + numeros);
        System.out.println("head(): " + numeros.head());
        System.out.println("dequeue(): " + numeros.dequeue());
        System.out.println("Depois do dequeue: " + numeros);

        // Demonstra o uso da Fila com o tipo genérico String, simulando
        // uma fila de atendimento.
        Fila<String> atendimento = Fila.create(3);
        atendimento.enqueue("Ana");
        atendimento.enqueue("Bruno");
        atendimento.enqueue("Carlos");

        System.out.println("Conjunto de textos: " + atendimento);
        System.out.println("isFull(): " + atendimento.isFull());
    }

    // ===================== LISTA SIMPLESMENTE ENCADEADA =====================

    /**
     * Submenu interativo da Lista Simplesmente Encadeada.
     * Permite inserir, remover, buscar elementos e consultar
     * tamanho/estado da lista.
     */
    private static void menuListaSimples() {
        // Lista genérica configurada para trabalhar com Integer.
        ListaSimples<Integer> lista = new ListaSimples<>(Integer.class);
        int opcao;

        do {
            System.out.println("\n--- LISTA ENCADEADA SIMPLES ---");
            System.out.println("Lista atual: " + lista);
            System.out.println("1 - insert");
            System.out.println("2 - remove");
            System.out.println("3 - search");
            System.out.println("4 - isEmpty");
            System.out.println("5 - size");
            System.out.println("6 - toArray");
            System.out.println("7 - Limpar/criar nova lista");
            System.out.println("8 - Demonstração automática");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Opção: ");

            // Diferente da Pilha/Fila, este submenu não usa try/catch,
            // pois as operações da lista não lançam exceções de estado aqui.
            switch (opcao) {
                case 1 -> lista.insert(lerInteiro("Valor inteiro: "));
                case 2 -> lista.remove(lerInteiro("Valor a remover: "));
                case 3 -> {
                    // Busca um valor e informa se foi encontrado (retorno null = não encontrado).
                    int valor = lerInteiro("Valor a pesquisar: ");
                    Integer encontrado = lista.search(valor);
                    System.out.println(encontrado == null ? "Não encontrado." : "Encontrado: " + encontrado);
                }
                case 4 -> System.out.println("Está vazia? " + lista.isEmpty());
                case 5 -> System.out.println("Tamanho: " + lista.size());
                case 6 -> System.out.println("Vetor: " + Arrays.toString(lista.toArray()));
                case 7 -> lista = new ListaSimples<>(Integer.class); // Reinicia a lista, descartando a anterior.
                case 8 -> demonstrarListaSimples();
                case 0 -> { }
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    /**
     * Demonstração automática do uso da Lista Simples, mostrando inserção,
     * busca, remoção e conversão para array, com números e textos.
     */
    private static void demonstrarListaSimples() {
        ListaSimples<Integer> numeros = new ListaSimples<>(Integer.class);
        numeros.insert(12);
        numeros.insert(7);
        numeros.insert(30);

        System.out.println("Números: " + numeros);
        System.out.println("search(7): " + numeros.search(7));
        numeros.remove(12);
        System.out.println("Após remove(12): " + numeros);
        System.out.println("size(): " + numeros.size());

        // Demonstra o uso da lista com o tipo genérico String.
        ListaSimples<String> disciplinas = new ListaSimples<>(String.class);
        disciplinas.insert("Estruturas de Dados");
        disciplinas.insert("POO");
        disciplinas.insert("Banco de Dados");

        System.out.println("Textos: " + disciplinas);
        System.out.println("toArray(): " + Arrays.toString(disciplinas.toArray()));
    }

    // ===================== LISTA DUPLAMENTE ENCADEADA =====================

    /**
     * Submenu interativo da Lista Duplamente Encadeada.
     * Além das operações básicas de inserção/remoção/busca, oferece
     * inserção/remoção específicas no início e no fim da lista.
     */
    private static void menuListaDupla() {
        ListaDupla<Integer> lista = new ListaDupla<>(Integer.class);
        int opcao;

        do {
            System.out.println("\n--- LISTA DUPLAMENTE ENCADEADA ---");
            System.out.println("Lista atual: " + lista);
            System.out.println("1 - insert (no final)");
            System.out.println("2 - insertFirst");
            System.out.println("3 - remove(elemento)");
            System.out.println("4 - removeFirst");
            System.out.println("5 - removeLast");
            System.out.println("6 - search");
            System.out.println("7 - isEmpty");
            System.out.println("8 - size");
            System.out.println("9 - toArray");
            System.out.println("10 - Limpar/criar nova lista");
            System.out.println("11 - Demonstração automática");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Opção: ");

            // Operações como removeFirst/removeLast podem lançar exceção
            // de estado inválido caso a lista esteja vazia.
            try {
                switch (opcao) {
                    case 1 -> lista.insert(lerInteiro("Valor inteiro: "));
                    case 2 -> lista.insertFirst(lerInteiro("Valor inteiro: "));
                    case 3 -> lista.remove(lerInteiro("Valor a remover: "));
                    case 4 -> lista.removeFirst();
                    case 5 -> lista.removeLast();
                    case 6 -> {
                        int valor = lerInteiro("Valor a pesquisar: ");
                        Integer encontrado = lista.search(valor);
                        System.out.println(encontrado == null ? "Não encontrado." : "Encontrado: " + encontrado);
                    }
                    case 7 -> System.out.println("Está vazia? " + lista.isEmpty());
                    case 8 -> System.out.println("Tamanho: " + lista.size());
                    case 9 -> System.out.println("Vetor: " + Arrays.toString(lista.toArray()));
                    case 10 -> lista = new ListaDupla<>(Integer.class);
                    case 11 -> demonstrarListaDupla();
                    case 0 -> { }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    /**
     * Demonstração automática do uso da Lista Dupla, mostrando inserção
     * no início/fim, remoção no início/fim e busca, com números e textos.
     */
    private static void demonstrarListaDupla() {
        ListaDupla<Integer> numeros = new ListaDupla<>(Integer.class);
        numeros.insert(20);
        numeros.insert(30);
        numeros.insertFirst(10); // Insere no início, ficando: 10, 20, 30.

        System.out.println("Números: " + numeros);
        numeros.removeFirst();
        System.out.println("Após removeFirst(): " + numeros);
        numeros.removeLast();
        System.out.println("Após removeLast(): " + numeros);

        // Demonstra o uso da lista dupla com o tipo genérico String.
        ListaDupla<String> nomes = new ListaDupla<>(String.class);
        nomes.insert("Bia");
        nomes.insert("Caio");
        nomes.insertFirst("Ana");

        System.out.println("Textos: " + nomes);
        System.out.println("search(\"Caio\"): " + nomes.search("Caio"));
    }

    // ===================== ÁRVORE BINÁRIA DE BUSCA (BST) =====================

    /**
     * Submenu interativo da BST (Binary Search Tree).
     * Permite inserir, remover, buscar valores e consultar propriedades
     * da árvore como altura, tamanho, raiz, mínimo e máximo, além dos
     * percursos pré-ordem, em ordem e pós-ordem.
     */
    private static void menuBST() {
        BST<Integer> arvore = new BST<>(Integer.class);
        int opcao;

        do {
            System.out.println("\n--- ÁRVORE BINÁRIA DE BUSCA (BST) ---");
            System.out.println("Em ordem: " + Arrays.toString(arvore.order()));
            System.out.println("1 - insert");
            System.out.println("2 - remove");
            System.out.println("3 - search");
            System.out.println("4 - getRoot");
            System.out.println("5 - isEmpty");
            System.out.println("6 - height");
            System.out.println("7 - preOrder");
            System.out.println("8 - order");
            System.out.println("9 - postOrder");
            System.out.println("10 - size");
            System.out.println("11 - minimum e maximum");
            System.out.println("12 - Limpar/criar nova BST");
            System.out.println("13 - Demonstração automática");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Opção: ");

            // Operações como minimum()/maximum() em árvore vazia podem
            // lançar IllegalStateException.
            try {
                switch (opcao) {
                    case 1 -> arvore.insert(lerInteiro("Valor inteiro: "));
                    case 2 -> arvore.remove(lerInteiro("Valor a remover: "));
                    case 3 -> {
                        int valor = lerInteiro("Valor a pesquisar: ");
                        System.out.println(arvore.search(valor) == null ? "Não encontrado." : "Encontrado.");
                    }
                    case 4 -> System.out.println("Raiz: " + arvore.getRoot());
                    case 5 -> System.out.println("Está vazia? " + arvore.isEmpty());
                    case 6 -> System.out.println("Altura: " + arvore.height());
                    case 7 -> System.out.println(Arrays.toString(arvore.preOrder()));
                    case 8 -> System.out.println(Arrays.toString(arvore.order()));
                    case 9 -> System.out.println(Arrays.toString(arvore.postOrder()));
                    case 10 -> System.out.println("Tamanho: " + arvore.size());
                    case 11 -> System.out.println("Mínimo: " + arvore.minimum()
                            + " | Máximo: " + arvore.maximum());
                    case 12 -> arvore = new BST<>(Integer.class);
                    case 13 -> demonstrarBST();
                    case 0 -> { }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    /**
     * Demonstração automática do uso da BST, mostrando inserção,
     * os três tipos de percurso, altura e remoção, com números e textos.
     */
    private static void demonstrarBST() {
        BST<Integer> numeros = new BST<>(Integer.class);

        // Insere valores formando uma árvore balanceada de exemplo.
        for (int valor : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            numeros.insert(valor);
        }

        System.out.println("Conjunto numérico:");
        System.out.println("Pré-ordem: " + Arrays.toString(numeros.preOrder()));
        System.out.println("Em ordem: " + Arrays.toString(numeros.order()));
        System.out.println("Pós-ordem: " + Arrays.toString(numeros.postOrder()));
        System.out.println("Altura: " + numeros.height());
        numeros.remove(30);
        System.out.println("Depois de remover 30: " + Arrays.toString(numeros.order()));

        // Demonstra que a BST também funciona com o tipo genérico String,
        // ordenando os textos alfabeticamente.
        BST<String> palavras = new BST<>(String.class);
        palavras.insert("Manga");
        palavras.insert("Banana");
        palavras.insert("Uva");

        System.out.println("Conjunto de textos em ordem: " + Arrays.toString(palavras.order()));
    }

    //  ÁRVORE AVL

    /**
     * Submenu interativo da árvore AVL (BST auto-balanceada).
     * Possui as mesmas operações da BST, já que a AVL mantém o balanceamento
     * automaticamente a cada inserção/remoção.
     */
    private static void menuAVL() {
        AVL<Integer> arvore = new AVL<>(Integer.class);
        int opcao;

        do {
            System.out.println("\n--- ÁRVORE AVL ---");
            System.out.println("Em ordem: " + Arrays.toString(arvore.order()));
            System.out.println("1 - insert");
            System.out.println("2 - remove");
            System.out.println("3 - search");
            System.out.println("4 - height");
            System.out.println("5 - preOrder");
            System.out.println("6 - order");
            System.out.println("7 - postOrder");
            System.out.println("8 - size");
            System.out.println("9 - minimum e maximum");
            System.out.println("10 - Limpar/criar nova AVL");
            System.out.println("11 - Demonstração automática");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Opção: ");

            try {
                switch (opcao) {
                    case 1 -> arvore.insert(lerInteiro("Valor inteiro: "));
                    case 2 -> arvore.remove(lerInteiro("Valor a remover: "));
                    case 3 -> {
                        int valor = lerInteiro("Valor a pesquisar: ");
                        System.out.println(arvore.search(valor) == null ? "Não encontrado." : "Encontrado.");
                    }
                    case 4 -> System.out.println("Altura: " + arvore.height());
                    case 5 -> System.out.println(Arrays.toString(arvore.preOrder()));
                    case 6 -> System.out.println(Arrays.toString(arvore.order()));
                    case 7 -> System.out.println(Arrays.toString(arvore.postOrder()));
                    case 8 -> System.out.println("Tamanho: " + arvore.size());
                    case 9 -> System.out.println("Mínimo: " + arvore.minimum()
                            + " | Máximo: " + arvore.maximum());
                    case 10 -> arvore = new AVL<>(Integer.class);
                    case 11 -> demonstrarAVL();
                    case 0 -> { }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    /**
     * Demonstração automática do uso da AVL, evidenciando o balanceamento
     * automático da árvore (por exemplo, ao inserir "C", "B", "A" em
     * sequência, o que forçaria uma rotação para manter a altura balanceada).
     */
    private static void demonstrarAVL() {
        AVL<Integer> numeros = new AVL<>(Integer.class);

        for (int valor : new int[]{30, 20, 10, 25, 28, 40, 50}) {
            numeros.insert(valor);
        }

        System.out.println("Conjunto numérico balanceado:");
        System.out.println("Pré-ordem: " + Arrays.toString(numeros.preOrder()));
        System.out.println("Em ordem: " + Arrays.toString(numeros.order()));
        System.out.println("Altura: " + numeros.height());
        numeros.remove(20);
        System.out.println("Depois de remover 20: " + Arrays.toString(numeros.preOrder()));

        // Inserção sequencial "C", "B", "A" tende a forçar uma rotação de
        // balanceamento na AVL, diferente do que ocorreria numa BST comum.
        AVL<String> palavras = new AVL<>(String.class);
        palavras.insert("C");
        palavras.insert("B");
        palavras.insert("A");

        System.out.println("Conjunto de textos após rotação: " + Arrays.toString(palavras.preOrder()));
    }

    // ÁRVORE B

    /**
     * Submenu interativo da Árvore B.
     * Diferente das árvores binárias, a Árvore B é definida por um grau
     * mínimo (número mínimo de filhos por nó interno, exceto a raiz) e
     * pode armazenar múltiplas chaves por nó.
     */
    private static void menuArvoreB() {
        // Cria a árvore B com grau mínimo 2 (equivalente a uma árvore 2-3-4).
        BTree<Integer> arvore = new BTree<>(2);
        int opcao;

        do {
            System.out.println("\n--- ÁRVORE B ---");
            System.out.println("Grau mínimo atual: " + arvore.getMinimumDegree());
            System.out.println("1 - insert");
            System.out.println("2 - search");
            System.out.println("3 - split da raiz, se estiver cheia");
            System.out.println("4 - height");
            System.out.println("5 - size");
            System.out.println("6 - printLevels");
            System.out.println("7 - Criar nova Árvore B");
            System.out.println("8 - Demonstração automática");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Opção: ");

            // Ex.: criar a árvore com grau mínimo inválido (< 2) pode lançar
            // IllegalArgumentException.
            try {
                switch (opcao) {
                    case 1 -> arvore.insert(lerInteiro("Valor inteiro: "));
                    case 2 -> {
                        int valor = lerInteiro("Valor a pesquisar: ");
                        System.out.println("Encontrado? " + arvore.search(valor));
                    }
                    case 3 -> {
                        // Força a divisão (split) da raiz caso ela esteja cheia,
                        // operação típica de manutenção da Árvore B.
                        arvore.split();
                        System.out.println("Operação split executada.");
                    }
                    case 4 -> System.out.println("Altura: " + arvore.height());
                    case 5 -> System.out.println("Tamanho: " + arvore.size());
                    case 6 -> arvore.printLevels(); // Imprime a árvore nível a nível.
                    case 7 -> {
                        // Recria a árvore com um novo grau mínimo escolhido pelo usuário.
                        int grau = lerInteiro("Grau mínimo (pelo menos 2): ");
                        arvore = new BTree<>(grau);
                    }
                    case 8 -> demonstrarArvoreB();
                    case 0 -> { }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    /**
     * Demonstração automática do uso da Árvore B, inserindo uma sequência
     * de valores/textos e exibindo a estrutura nível a nível, além de
     * altura, tamanho e busca.
     */
    private static void demonstrarArvoreB() {
        BTree<Integer> numeros = new BTree<>(2);

        for (int valor : new int[]{10, 20, 5, 6, 12, 30, 7, 17}) {
            numeros.insert(valor);
        }
        System.out.println("Conjunto numérico:");
        numeros.printLevels();
        System.out.println("search(12): " + numeros.search(12));
        System.out.println("height(): " + numeros.height());
        System.out.println("size(): " + numeros.size());

        // Demonstra a Árvore B com grau mínimo 3, usando o tipo genérico String.
        BTree<String> palavras = new BTree<>(3);

        for (String palavra : new String[]{"Java", "Árvore", "Fila", "Pilha", "Lista", "AVL"}) {
            palavras.insert(palavra);
        }

        System.out.println("Conjunto de textos:");
        palavras.printLevels();
    }
    //UTILITÁRIO DE LEITURA :
    /**
     * Lê um número inteiro do console de forma segura.
     * Fica em loop pedindo a entrada até que o usuário digite um valor
     * numérico válido, evitando que erros de digitação quebrem o programa.
     *
     * @param mensagem texto exibido ao usuário antes de ler a entrada.
     * @return o número inteiro digitado pelo usuário.
     */
    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = SCANNER.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                // Entrada não numérica: informa o erro e volta a pedir a entrada.
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }
}