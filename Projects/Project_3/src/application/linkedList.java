package application;

public class linkedList<T extends Comparable<T>> {
	private Node<T> head;
	private Node<T> tail; // Tail node for efficient append operations

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
		if (this.tail == null) { // If the list was empty, head is also the tail
			this.tail = head;
		}
	}

	public Node<T> getTail() {
		return tail;
	}

	public void setTail(Node<T> tail) {
		this.tail = tail;
	}

	public void insert(T data) {
		Node<T> newNode = new Node<>(data);
		if (head == null) { // If the list is empty
			setHead(newNode);
			tail = newNode; // Head and tail are the same
		} else {
			Node<T> curr = getHead(), prf = null;
			for (; curr != null && curr.getData().compareTo(data) < 0; prf = curr, curr = prf.getNext())
				;
			if (curr == null && prf == null) {
				setHead(newNode);
				tail = newNode;
			} else if (prf == null) {
				newNode.setNext(getHead());
				setHead(newNode);
			} else if (curr == null) {
				prf.setNext(newNode);
				tail = newNode; // Update tail as the new node is now the last node
			} else {
				newNode.setNext(curr);
				prf.setNext(newNode);
			}
		}
	}

	public T lastNodeData() {
		return tail.getData();
	}

	public LinkedListQueue<T> traverse() {
		LinkedListQueue<T> dataQueue = new LinkedListQueue<>();
		Node<T> curr = head;
		while (curr != null) {
			dataQueue.enqueue(curr.getData());
			curr = curr.getNext();
		}
		return dataQueue;
	}

	public Node<T> find(T data) {
		if (getHead() != null) {
			Node<T> curr = head;
			while (curr != null && curr.getData().compareTo(data) <= 0) {
				if (curr.getData().equals(data) == true) {
					return curr;
				}
				curr = curr.getNext();
			}
		}
		return null;
	}

	public boolean delete(T data) {
		if (head == null) {
			System.out.println(1);
			return false; // List is empty
		}

		Node<T> curr = head, prev = null;
		for (; curr != null && curr.getData().compareTo(data) <= 0 && curr.getData().equals(data) == false; prev = curr, curr = curr.getNext())
			;

		if (curr != null && curr.getData().equals(data) == true) {
			System.out.println("yes hoho");
			if (prev == null) {
				System.out.println(2);
				head = curr.getNext(); // Deleting the head
				if (head == null) {
					tail = null; // List is now empty, adjust tail
				}
			} else {
				System.out.println(3);
				prev.setNext(curr.getNext());
				if (curr.getNext() == null) {
					tail = prev; // Deleted the tail, update tail
				}
			}
			return true;
		}
		return false;
	}

	public boolean RecFind(T data) {
		if (data == null)
			return false;
		return RecFind(data, getHead());
	}

	private boolean RecFind(T data, Node<T> curr) {
		if (data == null || curr == null)
			return false;
		else if (curr.getData().compareTo(data) == 0)
			return true;

		return RecFind(data, curr.getNext());

	}

	public int[] ArrayOfLinkedList() {
		if (head != null) {

			int ListLength = 0;
			Node next = head;
			for (; next != null; ListLength++, next = next.getNext())
				;// to Know how many number in the list

			// Store the values in an array
			int[] reverseArray = new int[ListLength];
			next = head;
			int i = 0;
			for (; next != null; reverseArray[i++] = (int) next.getData(), next = next.getNext())
				;
			return reverseArray;
		}
		return null;
	}

	public void reverse() {
		if (head != null) {

			int ListLength = 0;
			Node next = head;
			for (; next != null; ListLength++, next = next.getNext())
				;// to Know how many number in the list

			// Store the values in an array
			int[] reverseArray = new int[ListLength];
			next = head;
			int i = 0;
			for (; next != null; reverseArray[i++] = (int) next.getData(), next = next.getNext())
				;

			// Assign the values from the array in reverse order
			next = head;
			i = ListLength - 1;
			for (; next != null; next.setData(reverseArray[i--]), next = next.getNext())
				;

		}
	}

	public void reverseRec() {
		if (head != null) {
			int ListLength = 0;
			Node next = head;
			for (; next != null; ListLength++, next = next.getNext())
				;// to Know how many number in the list
			int[] reverseArray = new int[ListLength];
			next = head;
			int i = 0;
			for (; next != null; reverseArray[i++] = (int) next.getData(), next = next.getNext())
				;
			reverseRec(reverseArray, head, ListLength - 1);
		}
	}

	public void reverseRec(int[] reverseArray, Node next, int i) {
		if (next != null) {
			next.setData(reverseArray[i--]);
			reverseRec(reverseArray, next.getNext(), i);
		}
	}

	public void Reverse2() {// i do it becouse i dont know if that the exactlysolution
		Node<T> prev = null;
		Node<T> curr = head;
		Node<T> next = null;
		for (; curr != null; next = curr.getNext(), curr.setNext(prev), prev = curr, curr = next)
			;
		head = prev;
	}

	public void ReverseRec2() {
		if (head != null) {
			ReverseRec2(null, head);
		}
	}

	private void ReverseRec2(Node<T> prev, Node<T> curr) {
		Node<T> next = null;
		if (curr != null) {
			next = curr.getNext();
			curr.setNext(prev);
			prev = curr;
			ReverseRec2(prev, next);
		} else
			head = prev;
	}

	public int length() {
		int count = 0;
		Node<T> curr = head;
		while (curr != null) {
			count++;
			curr = curr.getNext();
		}
		return count;
	}
}