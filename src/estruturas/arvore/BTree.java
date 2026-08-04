package estruturas.arvore;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Árvore B genérica.
 *
 * O parâmetro "grauMinimo" corresponde ao valor t:
 * - cada nó pode possuir no máximo 2t - 1 chaves;
 * - exceto a raiz, cada nó possui no mínimo t - 1 chaves.
 */
public class BTree<T extends Comparable<T>> {
    private static class BNode<E extends Comparable<E>> {
        private final ArrayList<E> chaves = new ArrayList<>();
        private final ArrayList<BNode<E>> filhos = new ArrayList<>();
        private boolean folha;

        private BNode(boolean folha) {
            this.folha = folha;
        }
    }

    private final int grauMinimo;
    private BNode<T> raiz;
    private int quantidade;

    public BTree(int grauMinimo) {
        if (grauMinimo < 2) {
            throw new IllegalArgumentException("O grau mínimo deve ser pelo menos 2.");
        }

        this.grauMinimo = grauMinimo;
        this.raiz = new BNode<>(true);
    }

    public void insert(T chave) {
        Objects.requireNonNull(chave, "A chave não pode ser nula.");

        if (search(chave)) {
            return; // não insere duplicados
        }

        if (raiz.chaves.size() == maximoDeChaves()) {
            split();
        }

        inserirEmNoNaoCheio(raiz, chave);
        quantidade++;
    }

    public boolean search(T chave) {
        Objects.requireNonNull(chave, "A chave não pode ser nula.");
        return buscarNo(raiz, chave) != null;
    }

    private BNode<T> buscarNo(BNode<T> no, T chave) {
        int indice = 0;

        while (indice < no.chaves.size()
                && chave.compareTo(no.chaves.get(indice)) > 0) {
            indice++;
        }

        if (indice < no.chaves.size()
                && chave.compareTo(no.chaves.get(indice)) == 0) {
            return no;
        }

        if (no.folha) {
            return null;
        }

        return buscarNo(no.filhos.get(indice), chave);
    }

    /**
     * Divide a raiz caso ela esteja cheia.
     * A divisão dos demais nós é feita por splitChild durante a inserção.
     */
    public void split() {
        if (raiz.chaves.size() < maximoDeChaves()) {
            return;
        }

        BNode<T> novaRaiz = new BNode<>(false);
        novaRaiz.filhos.add(raiz);
        splitChild(novaRaiz, 0);
        raiz = novaRaiz;
    }

    private void splitChild(BNode<T> pai, int indiceDoFilho) {
        BNode<T> cheio = pai.filhos.get(indiceDoFilho);
        BNode<T> novo = new BNode<>(cheio.folha);

        T mediana = cheio.chaves.get(grauMinimo - 1);

        // Copia as t - 1 maiores chaves para o novo nó.
        for (int i = grauMinimo; i < cheio.chaves.size(); i++) {
            novo.chaves.add(cheio.chaves.get(i));
        }

        // Remove do nó original a mediana e todas as chaves maiores.
        for (int i = cheio.chaves.size() - 1; i >= grauMinimo - 1; i--) {
            cheio.chaves.remove(i);
        }

        // Se não for folha, move os t filhos maiores para o novo nó.
        if (!cheio.folha) {
            for (int i = grauMinimo; i < cheio.filhos.size(); i++) {
                novo.filhos.add(cheio.filhos.get(i));
            }

            for (int i = cheio.filhos.size() - 1; i >= grauMinimo; i--) {
                cheio.filhos.remove(i);
            }
        }

        pai.filhos.add(indiceDoFilho + 1, novo);
        pai.chaves.add(indiceDoFilho, mediana);
    }

    private void inserirEmNoNaoCheio(BNode<T> no, T chave) {
        int indice = no.chaves.size() - 1;

        if (no.folha) {
            no.chaves.add(null);

            while (indice >= 0 && chave.compareTo(no.chaves.get(indice)) < 0) {
                no.chaves.set(indice + 1, no.chaves.get(indice));
                indice--;
            }

            no.chaves.set(indice + 1, chave);
            return;
        }

        while (indice >= 0 && chave.compareTo(no.chaves.get(indice)) < 0) {
            indice--;
        }

        indice++;

        if (no.filhos.get(indice).chaves.size() == maximoDeChaves()) {
            splitChild(no, indice);

            int comparacaoComMediana = chave.compareTo(no.chaves.get(indice));

            if (comparacaoComMediana > 0) {
                indice++;
            }
        }

        inserirEmNoNaoCheio(no.filhos.get(indice), chave);
    }

    public int height() {
        if (quantidade == 0) {
            return -1;
        }

        int altura = 0;
        BNode<T> atual = raiz;

        while (!atual.folha) {
            atual = atual.filhos.get(0);
            altura++;
        }

        return altura;
    }

    public int size() {
        return quantidade;
    }

    public int getMinimumDegree() {
        return grauMinimo;
    }

    public void printLevels() {
        if (quantidade == 0) {
            System.out.println("Árvore B vazia.");
            return;
        }

        ArrayList<BNode<T>> nivelAtual = new ArrayList<>();
        nivelAtual.add(raiz);
        int nivel = 0;

        while (!nivelAtual.isEmpty()) {
            System.out.print("Nível " + nivel + ": ");

            ArrayList<BNode<T>> proximoNivel = new ArrayList<>();

            for (BNode<T> no : nivelAtual) {
                System.out.print(no.chaves + " ");

                if (!no.folha) {
                    proximoNivel.addAll(no.filhos);
                }
            }

            System.out.println();
            nivelAtual = proximoNivel;
            nivel++;
        }
    }

    private int maximoDeChaves() {
        return 2 * grauMinimo - 1;
    }
}