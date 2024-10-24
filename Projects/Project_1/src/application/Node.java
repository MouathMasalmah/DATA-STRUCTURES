package application;

public class Node<T extends Comparable<T>> implements Comparable<T> {
	private T data;
	private Node<T> next;

	public Node() {

	}

	public Node(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> newNode) {
		this.next = newNode;
	}

	@Override
	public String toString() {
		return data + " ";
	}

	@Override
	public int compareTo(T o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
