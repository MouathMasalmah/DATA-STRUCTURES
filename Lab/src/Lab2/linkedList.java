package Lab2;

public class linkedList<T extends Comparable<T>> {
	private Node<T> head;

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public void insert(T data) {
		Node<T> newNode = new Node<>(data);
		Node<T> curr = getHead(), prf = null;
		for (; curr != null && curr.getData().compareTo(data) < 0; prf = curr, curr = prf.getNext())
			;
		if (curr == null && prf == null) {
			setHead(newNode);
		} else if (prf == null) {
			newNode.setNext(getHead());
			setHead(newNode);
		} else if (curr == null) {
			prf.setNext(newNode);
		} else {
			newNode.setNext(curr);
			prf.setNext(newNode);

		}
	}

	public String traverse() {
		String Result = "Head --> ";
		Node<T> curr = getHead();
		while (curr != null) {
			Result += curr + " --> ";
			curr = curr.getNext();
		}
		Result += " Null ";
		return Result;
	}

	public boolean find(T data) {
		if (getHead() != null) {
			Node<T> curr = head;
			while (curr != null && curr.getData().compareTo(data) <= 0) {
				if (curr.getData().compareTo(data) == 0) {
					return true;
				}
				curr = curr.getNext();
			}
		}
		return false;
	}

	public boolean delete(T data) {
		if (head == null) {
			return false;
		} else {
			Node<T> curr = head, prev = null;
			for (; curr != null && curr.getData().compareTo(data) < 0; prev = curr, curr = curr.getNext())
				;
			if (curr != null && curr.getData().compareTo(data) == 0) {
				if (prev == null) {
					head = curr.getNext();
				} else if (curr.getNext() == null) {
					prev.setNext(null);
				} else {
					prev.setNext(curr.getNext());
				}
				return true;
			}
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

}