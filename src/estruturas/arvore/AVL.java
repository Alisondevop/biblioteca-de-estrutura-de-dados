package estruturas.arvore;

import java.util.Objects;

/**
 * Árvore AVL genérica.
 * Reaproveita as operações de consulta e percursos da BST.
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

    public AVL(Class<T> tipo) {
        super(tipo);
    }

    @Override
    public void insert(T element) {
        Objects.requireNonNull(element, "O elemento não pode ser nulo.");

        if (search(element) == null) {
            raiz = inserirAVL(raiz, element);
            quantidade++;
        }
    }

    private BTNode<T> inserirAVL(BTNode<T> no, T element) {
        if (no == null) {
            return new BTNode<>(element);
        }

        int comparacao = element.compareTo(no.valor);

        if (comparacao < 0) {
            no.esquerdo = inserirAVL(no.esquerdo, element);
        } else if (comparacao > 0) {
            no.direito = inserirAVL(no.direito, element);
        } else {
            return no;
        }

        atualizarAltura(no);
        return rebalanceUp(no);
    }

    @Override
    public void remove(T element) {
        Objects.requireNonNull(element, "O elemento não pode ser nulo.");

        if (search(element) != null) {
            raiz = removerAVL(raiz, element);
            quantidade--;
        }
    }

    private BTNode<T> removerAVL(BTNode<T> no, T element) {
        if (no == null) {
            return null;
        }

        int comparacao = element.compareTo(no.valor);

        if (comparacao < 0) {
            no.esquerdo = removerAVL(no.esquerdo, element);
        } else if (comparacao > 0) {
            no.direito = removerAVL(no.direito, element);
        } else {
            if (no.esquerdo == null || no.direito == null) {
                no = no.esquerdo != null ? no.esquerdo : no.direito;
            } else {
                BTNode<T> sucessor = menorNo(no.direito);
                no.valor = sucessor.valor;
                no.direito = removerAVL(no.direito, sucessor.valor);
            }
        }

        if (no == null) {
            return null;
        }

        atualizarAltura(no);
        return rebalanceUp(no);
    }

    /**
     * Fator de balanceamento = altura da esquerda - altura da direita.
     */
    private int calculateBalance(BTNode<T> node) {
        if (node == null) {
            return 0;
        }

        return alturaAVL(node.esquerdo) - alturaAVL(node.direito);
    }

    private BTNode<T> rebalance(BTNode<T> node) {
        int balanceamento = calculateBalance(node);

        // Caso esquerda-esquerda ou esquerda-direita.
        if (balanceamento > 1) {
            if (calculateBalance(node.esquerdo) < 0) {
                node.esquerdo = leftRotation(node.esquerdo);
            }
            return rightRotation(node);
        }

        // Caso direita-direita ou direita-esquerda.
        if (balanceamento < -1) {
            if (calculateBalance(node.direito) > 0) {
                node.direito = rightRotation(node.direito);
            }
            return leftRotation(node);
        }

        return node;
    }

    /**
     * Na versão recursiva, o rebalanceamento "para cima" ocorre durante
     * o retorno das chamadas recursivas.
     */
    private BTNode<T> rebalanceUp(BTNode<T> node) {
        return rebalance(node);
    }

    private BTNode<T> leftRotation(BTNode<T> node) {
        BTNode<T> novaRaiz = node.direito;
        BTNode<T> subArvoreMovida = novaRaiz.esquerdo;

        novaRaiz.esquerdo = node;
        node.direito = subArvoreMovida;

        atualizarAltura(node);
        atualizarAltura(novaRaiz);

        return novaRaiz;
    }

    private BTNode<T> rightRotation(BTNode<T> node) {
        BTNode<T> novaRaiz = node.esquerdo;
        BTNode<T> subArvoreMovida = novaRaiz.direito;

        novaRaiz.direito = node;
        node.esquerdo = subArvoreMovida;

        atualizarAltura(node);
        atualizarAltura(novaRaiz);

        return novaRaiz;
    }

    private void atualizarAltura(BTNode<T> node) {
        node.altura = 1 + Math.max(alturaAVL(node.esquerdo), alturaAVL(node.direito));
    }

    private int alturaAVL(BTNode<T> node) {
        return node == null ? -1 : node.altura;
    }

    @Override
    public int height() {
        return alturaAVL(raiz);
    }
}