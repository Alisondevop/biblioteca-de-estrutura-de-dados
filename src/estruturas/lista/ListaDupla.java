package estruturas.lista;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

/**
 * Lista duplamente encadeada genérica.
 * Não utiliza java.util.LinkedList.
 */
public class ListaDupla<T> implements estruturas.lista.DoubleLinkedList<T> {
    private static class No<E> {
        private E valor;
        private No<E> anterior;
        private No<E> proximo;

        private No(E valor) {
            this.valor = valor;
        }
    }

    private final Class<T> tipo;
    private No<T> primeiro;
    private No<T> ultimo;
    private int quantidade;

    public ListaDupla(Class<T> tipo) {
        this.tipo = Objects.requireNonNull(tipo, "O tipo dos elementos é obrigatório.");
    }

    @Override
    public boolean isEmpty() {
        return quantidade == 0;
    }

    @Override
    public int size() {
        return quantidade;
    }

    @Override
    public T search(T element) {
        No<T> atual = primeiro;

        while (atual != null) {
            if (Objects.equals(atual.valor, element)) {
                return atual.valor;
            }
            atual = atual.proximo;
        }

        return null;
    }

    /**
     * Insere no final da lista.
     */
    @Override
    public void insert(T element) {
        No<T> novo = new No<>(element);

        if (isEmpty()) {
            primeiro = novo;
            ultimo = novo;
        } else {
            novo.anterior = ultimo;
            ultimo.proximo = novo;
            ultimo = novo;
        }

        quantidade++;
    }

    @Override
    public void insertFirst(T element) {
        No<T> novo = new No<>(element);

        if (isEmpty()) {
            primeiro = novo;
            ultimo = novo;
        } else {
            novo.proximo = primeiro;
            primeiro.anterior = novo;
            primeiro = novo;
        }

        quantidade++;
    }

    /**
     * Remove a primeira ocorrência do elemento.
     */
    @Override
    public void remove(T element) {
        No<T> atual = primeiro;

        while (atual != null && !Objects.equals(atual.valor, element)) {
            atual = atual.proximo;
        }

        if (atual == null) {
            return;
        }

        desconectar(atual);
    }

    @Override
    public void removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("A lista está vazia.");
        }

        desconectar(primeiro);
    }

    @Override
    public void removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("A lista está vazia.");
        }

        desconectar(ultimo);
    }

    private void desconectar(No<T> no) {
        if (no.anterior == null) {
            primeiro = no.proximo;
        } else {
            no.anterior.proximo = no.proximo;
        }

        if (no.proximo == null) {
            ultimo = no.anterior;
        } else {
            no.proximo.anterior = no.anterior;
        }

        no.anterior = null;
        no.proximo = null;
        quantidade--;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] resultado = (T[]) Array.newInstance(tipo, quantidade);
        No<T> atual = primeiro;
        int indice = 0;

        while (atual != null) {
            resultado[indice++] = atual.valor;
            atual = atual.proximo;
        }

        return resultado;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}