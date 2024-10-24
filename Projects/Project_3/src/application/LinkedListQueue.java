
package application;

public class LinkedListQueue<T extends Comparable<T>> {
	private Node<T> first;
	private Node<T> last;

	public Node<T> getLast() {
		return last;
	}

	public int size() {
	    int count = 0;
	    Node<T> current = first;
	    while (current != null) {
	        count++;
	        current = current.getNext();
	    }
	    return count;
	}


	public void setLast(Node<T> last) {
		this.last = last;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void clear() {
		first = null;
		last = null;
	}

	public void enqueue(T data) {
		Node<T> newNode = new Node<>(data);
		if (isEmpty()) {
			first = newNode;
		} else {
			last.setNext(newNode);
		}
		last = newNode;
	}

	public T getFront() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return null;
		} else {
			return first.getData();
		}
	}

	public T dequeue() {
		T front = getFront();
		if (!isEmpty()) {
			first = first.getNext();

		}
		if (first == null) {
			last = null;
		}
		return front;
	}

	@Override
	public String toString() {
		Node<T> current = first;
		String l = "";
		while (current != null) {
			l += current.getData() + ",";
			current = current.getNext();
		}
		return l;
	}

	public void traverse() {
		Node<T> current = first;
		String l = "";
		while (current != null) {
			l += current.getData() + ",";
			current = current.getNext();
		}
	}

}
