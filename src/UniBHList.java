public class UniBHList<T extends Comparable<T>> {
    private Node<T> firstNode;
    private int totalElements;

    // Insere no início
    public void insertAtBeginning(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.setNext(firstNode);
        firstNode = newNode;
        totalElements++;
    }

    // Insere no fim
    public void insertAtEnd(T value) {
        Node<T> newNode = new Node<>(value);
        if (firstNode == null) {
            firstNode = newNode;
        } else {
            Node<T> currentNode = firstNode;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }
        totalElements++;
    }

    // Insere ordenadamente
    public void insertInOrder(T value) {
        Node<T> newNode = new Node<>(value);
        if (firstNode == null || firstNode.getValue().compareTo(value) >= 0) {
            newNode.setNext(firstNode);
            firstNode = newNode;
        } else {
            Node<T> currentNode = firstNode;
            while (currentNode.getNext() != null && currentNode.getNext().getValue().compareTo(value) < 0) {
                currentNode = currentNode.getNext();
            }
            newNode.setNext(currentNode.getNext());
            currentNode.setNext(newNode);
        }
        totalElements++;
    }

    // Remove do início
    public Node<T> removeAtBeginning() {
        if (firstNode == null) return null;
        Node<T> aux = firstNode;
        firstNode = firstNode.getNext();
        totalElements--;
        return aux;
    }

    // Remove do fim
    public Node<T> removeAtEnd() {
        if (firstNode == null) return null;
        if (firstNode.getNext() == null) {
            Node<T> aux = firstNode;
            firstNode = null;
            totalElements--;
            return aux;
        }
        Node<T> currentNode = firstNode;
        while (currentNode.getNext().getNext() != null) {
            currentNode = currentNode.getNext();
        }
        Node<T> aux = currentNode.getNext();
        currentNode.setNext(null);
        totalElements--;
        return aux;
    }

    // Organiza a lista (insertion sort)
    public void organize() {
        if (firstNode == null || firstNode.getNext() == null) return;

        Node<T> sortedList = null;

        while (firstNode != null) {
            Node<T> currentNode = firstNode;
            firstNode = firstNode.getNext();

            if (sortedList == null || sortedList.getValue().compareTo(currentNode.getValue()) >= 0) {
                currentNode.setNext(sortedList);
                sortedList = currentNode;
            } else {
                Node<T> temp = sortedList;
                while (temp.getNext() != null && temp.getNext().getValue().compareTo(currentNode.getValue()) < 0) {
                    temp = temp.getNext();
                }
                currentNode.setNext(temp.getNext());
                temp.setNext(currentNode);
            }
        }

        firstNode = sortedList;
    }

    // Verifica se está ordenada
    public boolean isSorted() {
        if (firstNode == null || firstNode.getNext() == null) return true;

        Node<T> currentNode = firstNode;
        while (currentNode.getNext() != null) {
            if (currentNode.getValue().compareTo(currentNode.getNext().getValue()) > 0) return false;
            currentNode = currentNode.getNext();
        }
        return true;
    }

    // Pesquisa se valor existe
    public boolean contains(T value) {
        Node<T> current = firstNode;
        while (current != null) {
            if (current.getValue().equals(value)) return true;
            current = current.getNext();
        }
        return false;
    }

    // Retorna o índice de um valor
    public int indexOf(T value) {
        Node<T> current = firstNode;
        int index = 0;
        while (current != null) {
            if (current.getValue().equals(value)) return index;
            current = current.getNext();
            index++;
        }
        throw new IllegalArgumentException("Elemento não encontrado na lista: " + value);
    }

    // Remove valor específico
    public void removeByValue(T value) {
        if (firstNode == null) throw new IllegalArgumentException("Lista vazia. Não é possível remover: " + value);

        if (firstNode.getValue().equals(value)) {
            firstNode = firstNode.getNext();
            totalElements--;
            return;
        }

        Node<T> current = firstNode;
        while (current.getNext() != null && !current.getNext().getValue().equals(value)) {
            current = current.getNext();
        }

        if (current.getNext() == null) throw new IllegalArgumentException("Valor não encontrado na lista: " + value);

        current.setNext(current.getNext().getNext());
        totalElements--;
    }

    // Verifica se está vazia
    public boolean isEmpty() {
        return totalElements == 0;
    }

    // Insere após o índice especificado
    public void insertAfter(int index, T value) {
        if (index < 0 || index >= totalElements)
            throw new IndexOutOfBoundsException("Índice inválido: " + index);

        Node<T> current = firstNode;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        Node<T> newNode = new Node<>(value);
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        totalElements++;
    }

    // Remove elemento do índice
    public void removeAt(int index) {
        if (index < 0 || index >= totalElements)
            throw new IndexOutOfBoundsException("Índice inválido: " + index);

        if (index == 0) {
            firstNode = firstNode.getNext();
        } else {
            Node<T> current = firstNode;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
        }

        totalElements--;
    }

    // Modifica elemento da posição
    public void set(int index, T newValue) {
        if (index < 0 || index >= totalElements)
            throw new IndexOutOfBoundsException("Índice inválido: " + index);

        Node<T> current = firstNode;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        current.value = newValue;
    }

    // Retorna tamanho da lista
    public int size() {
        return totalElements;
    }

    // toString da lista
    @Override
    public String toString() {
        if (this.totalElements == 0) return "[ ]";

        Node<T> currentNode = firstNode;
        StringBuilder builder = new StringBuilder("[");

        while (currentNode != null) {
            builder.append(currentNode.getValue());
            if (currentNode.getNext() != null) builder.append(", ");
            currentNode = currentNode.getNext();
        }

        builder.append("]");
        return builder.toString();
    }

    // Classe interna Node
    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getNext() {
            return next;
        }
    }
}
