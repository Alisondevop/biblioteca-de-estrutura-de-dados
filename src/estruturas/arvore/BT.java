package estruturas.arvore;

/**
 * Interface da Árvore Binária de Busca.
 */
public interface BT<T extends Comparable<T>> {
    BTNode<T> getRoot();

    boolean isEmpty();

    int height();

    BTNode<T> search(T elem);

    void insert(T value);

    void remove(T key);

    T[] preOrder();

    T[] order();

    T[] postOrder();

    int size();
}