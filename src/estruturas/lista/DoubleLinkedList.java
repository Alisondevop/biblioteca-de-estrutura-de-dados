package estruturas.lista;

public interface DoubleLinkedList <T> {
    boolean isEmpty();
     int size();

     T search(T elements);

     void insert(T elements);
     void remove(T elements);

    T[] toArray();

    void insertFirst(T elements);

    void removeFirst(T elements);
    void removeLast();
}
