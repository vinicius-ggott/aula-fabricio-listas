public class UniBHList<T extends Comparable<T>> {
    // Hold the reference to the first node of this List.
    private Node<T> firstNode;
    private int totalElements;

    public void insertAtBeginning(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.setNext(firstNode);
        firstNode = newNode;
        totalElements++;
    }

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

    public Node<T> removeAtBeginning() {
        Node<T> aux = firstNode;
        firstNode = firstNode.getNext();
        totalElements--;
        return aux;
    }

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

    public Node<T> removeAtEnd() {
        if (firstNode == null) {
            return null;
        }
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

    public void organize() {
        if (firstNode == null || firstNode.getNext() == null) {
            return;
        }

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

    public boolean isSorted() {
        if (firstNode == null || firstNode.getNext() == null) {
            return true;
        }

        Node<T> currentNode = firstNode;
        while (currentNode.getNext() != null) {
            if (currentNode.getValue().compareTo(currentNode.getNext().getValue()) > 0) {
                return false;
            }
            currentNode = currentNode.getNext();
        }
        return true;
    }

    @Override
    public String toString() {
        if(this.totalElements == 0) {
            return "[ ]";
        }

        Node<T> currentNode = firstNode;
        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < totalElements; i++) {
            builder.append(currentNode.getValue());
            builder.append(", ");

            currentNode = currentNode.getNext();

        }

        builder.append("]");

        return  builder.toString();
    }

    // Node class definition
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