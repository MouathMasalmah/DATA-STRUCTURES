package Lab6;

import java.util.Iterator;

public class LinkedStack<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> topNode;

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(topNode);
        topNode = newNode;
    }

    public Node<T> pop() {
        Node<T> toDel = topNode;
        if (topNode != null) {
            topNode = topNode.getNext();
        }
        return toDel;
    }

    public Node<T> peek() {
        return topNode;
    }

    public boolean isEmpty() {
        return topNode == null;
    }

    public void clear() {
        topNode = null;
    }

    public void display() {
        Node<T> current = topNode;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = topNode;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.getData();
                current = current.getNext();
                return data;
            }
        };
    }
}