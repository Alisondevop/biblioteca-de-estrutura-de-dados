package estruturas.fila;

import java.util.Arrays;

/**
 * Fila circular genérica implementada com vetor.
 * Não utiliza java.util.Queue.
 */
public class Fila<T> {
    private final Object[] elementos;
    private int inicio;
    private int fim;
    private int quantidade;

    private Fila(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser maior que zero.");
        }

        this.elementos = new Object[capacidade];
        this.inicio = 0;
        this.fim = 0;
        this.quantidade = 0;
    }

    /**
     * Cria uma nova fila com capacidade fixa.
     */
    public static <T> Fila<T> create(int capacidade) {
        return new Fila<>(capacidade);
    }

    public void enqueue(T elemento) {
        if (isFull()) {
            throw new IllegalStateException("A fila está cheia.");
        }

        elementos[fim] = elemento;
        fim = (fim + 1) % elementos.length;
        quantidade++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia.");
        }

        T elemento = (T) elementos[inicio];
        elementos[inicio] = null;
        inicio = (inicio + 1) % elementos.length;
        quantidade--;
        return elemento;
    }

    @SuppressWarnings("unchecked")
    public T head() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia.");
        }
        return (T) elementos[inicio];
    }

    public boolean isEmpty() {
        return quantidade == 0;
    }

    public boolean isFull() {
        return quantidade == elementos.length;
    }

    public int size() {
        return quantidade;
    }

    public int capacity() {
        return elementos.length;
    }

    public Object[] toArray() {
        Object[] resultado = new Object[quantidade];

        for (int i = 0; i < quantidade; i++) {
            resultado[i] = elementos[(inicio + i) % elementos.length];
        }

        return resultado;
    }

    @Override
    public String toString() {
        return "início -> " + Arrays.toString(toArray()) + " <- fim";
    }
}