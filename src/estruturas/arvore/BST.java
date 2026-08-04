package estruturas.arvore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Árvore Binária de Busca genérica.
 */
public class BST<T extends Comparable<T>> implements BT<T> {
    protected final Class<T> tipo;
    protected BTNode<T> raiz;
    protected int quantidade;

    public BST(Class<T> tipo) {
        this.tipo = Objects.requireNonNull(tipo, "O tipo dos elementos é obrigatório.");
    }

    @Override
    public BTNode<T> getRoot() {
        return raiz;
    }

    @Override
    public boolean isEmpty() {
        return raiz == null;
    }

    @Override
    public int height() {
        return altura(raiz);
    }

    protected int altura(BTNode<T> no) {
        if (no == null) {
            return -1;
        }

        return 1 + Math.max(altura(no.esquerdo), altura(no.direito));
    }

    @Override
    public BTNode<T> search(T elem) {
        Objects.requireNonNull(elem, "O elemento não pode ser nulo.");

        BTNode<T> atual = raiz;

        while (atual != null) {
            int comparacao = elem.compareTo(atual.valor);

            if (comparacao == 0) {
                return atual;
            }

            atual = comparacao < 0 ? atual.esquerdo : atual.direito;
        }

        return null;
    }

    @Override
    public void insert(T value) {
        Objects.requireNonNull(value, "O valor não pode ser nulo.");

        if (raiz == null) {
            raiz = new BTNode<>(value);
            quantidade++;
            return;
        }

        BTNode<T> atual = raiz;
        BTNode<T> pai = null;
        int comparacao = 0;

        while (atual != null) {
            pai = atual;
            comparacao = value.compareTo(atual.valor);

            if (comparacao == 0) {
                return; // não insere duplicados
            }

            atual = comparacao < 0 ? atual.esquerdo : atual.direito;
        }

        if (comparacao < 0) {
            pai.esquerdo = new BTNode<>(value);
        } else {
            pai.direito = new BTNode<>(value);
        }

        quantidade++;
    }

    @Override
    public void remove(T key) {
        Objects.requireNonNull(key, "A chave não pode ser nula.");

        if (search(key) != null) {
            raiz = remover(raiz, key);
            quantidade--;
        }
    }

    protected BTNode<T> remover(BTNode<T> no, T key) {
        if (no == null) {
            return null;
        }

        int comparacao = key.compareTo(no.valor);

        if (comparacao < 0) {
            no.esquerdo = remover(no.esquerdo, key);
        } else if (comparacao > 0) {
            no.direito = remover(no.direito, key);
        } else {
            if (no.esquerdo == null) {
                return no.direito;
            }

            if (no.direito == null) {
                return no.esquerdo;
            }

            BTNode<T> sucessor = menorNo(no.direito);
            no.valor = sucessor.valor;
            no.direito = remover(no.direito, sucessor.valor);
        }

        return no;
    }

    protected BTNode<T> menorNo(BTNode<T> no) {
        BTNode<T> atual = no;

        while (atual.esquerdo != null) {
            atual = atual.esquerdo;
        }

        return atual;
    }

    protected BTNode<T> maiorNo(BTNode<T> no) {
        BTNode<T> atual = no;

        while (atual.direito != null) {
            atual = atual.direito;
        }

        return atual;
    }

    public T minimum() {
        if (isEmpty()) {
            throw new IllegalStateException("A árvore está vazia.");
        }
        return menorNo(raiz).valor;
    }

    public T maximum() {
        if (isEmpty()) {
            throw new IllegalStateException("A árvore está vazia.");
        }
        return maiorNo(raiz).valor;
    }

    @Override
    public T[] preOrder() {
        ArrayList<T> valores = new ArrayList<>();
        preOrdem(raiz, valores);
        return converter(valores);
    }

    private void preOrdem(BTNode<T> no, ArrayList<T> valores) {
        if (no == null) {
            return;
        }

        valores.add(no.valor);
        preOrdem(no.esquerdo, valores);
        preOrdem(no.direito, valores);
    }

    @Override
    public T[] order() {
        ArrayList<T> valores = new ArrayList<>();
        emOrdem(raiz, valores);
        return converter(valores);
    }

    private void emOrdem(BTNode<T> no, ArrayList<T> valores) {
        if (no == null) {
            return;
        }

        emOrdem(no.esquerdo, valores);
        valores.add(no.valor);
        emOrdem(no.direito, valores);
    }

    @Override
    public T[] postOrder() {
        ArrayList<T> valores = new ArrayList<>();
        posOrdem(raiz, valores);
        return converter(valores);
    }

    private void posOrdem(BTNode<T> no, ArrayList<T> valores) {
        if (no == null) {
            return;
        }

        posOrdem(no.esquerdo, valores);
        posOrdem(no.direito, valores);
        valores.add(no.valor);
    }

    @SuppressWarnings("unchecked")
    protected T[] converter(ArrayList<T> valores) {
        T[] resultado = (T[]) Array.newInstance(tipo, valores.size());

        for (int i = 0; i < valores.size(); i++) {
            resultado[i] = valores.get(i);
        }

        return resultado;
    }

    @Override
    public int size() {
        return quantidade;
    }

    @Override
    public String toString() {
        return Arrays.toString(order());
    }
}
