package estruturas.arvore;

/**
 * Nó utilizado pela BST e pela AVL.
 */
public class BTNode<T> {
    T valor;
    BTNode<T> esquerdo;
    BTNode<T> direito;
    int altura;

    BTNode(T valor) {
        this.valor = valor;
        this.altura = 0;
    }

    public T getValue() {
        return valor;
    }

    public BTNode<T> getLeft() {
        return esquerdo;
    }

    public BTNode<T> getRight() {
        return direito;
    }

    public int getHeight() {
        return altura;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}