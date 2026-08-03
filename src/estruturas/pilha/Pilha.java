package estruturas.pilha;

import java.util.Arrays;

/**
 * Pilha genérica implementada com vetor.
 * Não utiliza java.util.Stack.
 */
public class Pilha<T> {
    private final Object[] elementos;
    private int topo;

    private Pilha(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser maior que zero.");
        }
        this.elementos = new Object[capacidade];
        this.topo = -1;
    }

    /**
     * Cria uma nova pilha com capacidade fixa.
     */
    public static <T> Pilha<T> create(int capacidade) {
        return new Pilha<>(capacidade);
    }

    public void push(T elemento) {
        if (isFull()) {
            throw new IllegalStateException("A pilha está cheia.");
        }
        elementos[++topo] = elemento;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia.");
        }

        T elemento = (T) elementos[topo];
        elementos[topo] = null;
        topo--;
        return elemento;
    }

    @SuppressWarnings("unchecked")
    public T top() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia.");
        }
        return (T) elementos[topo];
    }

    public boolean isEmpty() {
        return topo == -1;
    }

    public boolean isFull() {
        return topo == elementos.length - 1;
    }

    public int size() {
        return topo + 1;
    }

    public int capacity() {
        return elementos.length;
    }

    public Object[] toArray() {
        return Arrays.copyOf(elementos, size());
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray()) + " <- topo";
    }
}