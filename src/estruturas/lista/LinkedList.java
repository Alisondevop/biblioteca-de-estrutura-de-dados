package estruturas.lista;

/**
 * Interface para utilizar na lista encadeada simples
 */
public interface LinkedList<T>{
    boolean isEmpty();

    int size();

    T search(T elements);

    void insert(T elements);
    void remove(T elements);

    T[] toArray();

}
