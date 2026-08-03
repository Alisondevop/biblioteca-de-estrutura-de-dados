package estruturas.lista;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

/**
 * Lista encadeada simples genérica.
 */
public class ListaSimples<T> implements LinkedList<T> {
    private static class No<E> {
        private E valor;
        private No<E> proximo;

        private No(E valor) {
            this.valor = valor;
        }
    }

    private final Class<T> tipo;
    private No<T> primeiro;
    private No<T> ultimo;
    private int quantidade;

    public ListaSimples(Class<T> tipo) {
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
            ultimo.proximo = novo;
            ultimo = novo;
        }

        quantidade++;
    }

    /**
     * Remove a primeira ocorrência do elemento.
     */
    @Override
    public void remove(T element) {
        No<T> anterior = null;
        No<T> atual = primeiro;

        while (atual != null && !Objects.equals(atual.valor, element)) {
            anterior = atual;
            atual = atual.proximo;
        }

        if (atual == null) {
            return;
        }

        if (anterior == null) {
            primeiro = atual.proximo;
        } else {
            anterior.proximo = atual.proximo;
        }

        if (atual == ultimo) {
            ultimo = anterior;
        }

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