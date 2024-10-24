package Lab10;


public class LinkedList <T extends Comparable<T>>{

    private Node<T> head;

    public void insert (T data){
        Node<T> newNode = new Node<>(data);
        Node <T> current = head;
        Node <T> previous = null;

        for(; current != null && current.compare(data) < 0; previous = current, current = current.getNext());

        if(previous == null && current == null){    // case 0 : empty list
            head = newNode;
        } else if(current == null) {    // case 1 : insert at the end
            previous.setNext(newNode);
        } else if(previous == null){    // case 2 : insert at the beginning
            newNode.setNext(head);
            head = newNode;
        } else {    // case 3 : insert in the middle
            newNode.setNext(current);
            previous.setNext(newNode);
        }
    }

    public void insertRecursive(T data){
        insertRecursive(data, head, null);
    }
    //helper method
    private Node<T> insertRecursive(T data, Node<T> current, Node<T> previous) {
        if(current == null){
            Node<T> newNode = new Node<>(data);
            if(previous == null){    // case 0 : empty list
                head = newNode;
            } else {    // case 1 : insert at the end
                previous.setNext(newNode);
            }
            return newNode;
        }

        if(current.compare(data) > 0){
            Node<T> newNode = new Node<>(data);
            newNode.setNext(current);
            if(previous == null){
                head = newNode;
            } else {
                previous.setNext(newNode);
            }
            return newNode;
        }
        return insertRecursive(data, current.getNext(), current);
    }

    public boolean find(T data) {
        Node<T> current = head;
        while(current != null && current.compare(data) <= 0){
            if(current.compare(data) == 0)
                return true;
            current = current.getNext();
        }
        return false;
    }

    public boolean findRecursive(T data) {
        if(head == null)
            return false;
        return findRecursive(data, head);
    }

    //helper method
    private boolean findRecursive(T data, Node<T> current) {
        if(current == null)
            return false;
        if(current.compare(data) == 0)
            return true;
        return findRecursive(data, current.getNext());
    }

    public boolean delete(T data){
        Node<T> current = head;
        Node<T> previous = null;
        while(current != null){
            if(current.compare(data) == 0){
                if(previous == null){    // case 1 : delete at the beginning
                    head = current.getNext();
                } else {     // case 2 : delete in the middle or at the end
                    previous.setNext(current.getNext());
                }
                return true;
            }
            if(current.compare(data) > 0) // case 3 : data not found
                return false;
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    public void traverse(){
        Node<T> current = head;
        System.out.print("Head --> ");
        while(current != null){
            System.out.print(current + " --> ");
            current = current.getNext();
        }
        System.out.println("null");
    }

    public void reverse(){
        Node<T> current = head;
        Node<T> previous = null;
        Node<T> next = null;

        while(current != null){
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        head = previous;
    }

    public void reverseRecursive(){
        head = reverseRecursive(head, null);
    }

    //helper method
    private Node<T> reverseRecursive(Node<T> curr, Node<T> prev) {
        if(curr == null)
            return prev;
        Node<T> next = curr.getNext();
        curr.setNext(prev);
        return reverseRecursive(next, curr);
    }

    public boolean isEmpty(){
        return head == null;
    }

}