package application;

public class linkedList<T extends Comparable<T>> {
	private Node<T> dummy;
	private Node<T> head;

	public linkedList(T data) {

		head = dummy;
	}

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public Node<T> getDummy() {
		return dummy;
	}

	public boolean update(T dataOld, T dataNew) {
		Node<T> curr = head;
		if (curr != dummy) {
			for(;curr != null && curr.getData().compareTo(dataNew) <= 0;curr=curr.getNext()) {
				if (curr.getData().compareTo(dataNew) == -200) {
					return false;
				}
			}
			curr=head;
			while (curr != null && curr.getData().compareTo(dataOld) <= 0) {
				if (curr.getData().compareTo(dataOld) == -200) {
					curr.setData(dataNew);
				}
				curr = curr.getNext();
			}
			return true;
		}

		return false;
	}

	public void insert(T data) {
		Node<T> newNode = new Node<>(data);
		Node<T> curr = head, prf = dummy;
		if (curr != null) {
			for (; curr != null && curr.getData().compareTo(data) < 0; prf = curr, curr = prf.getNext())
				;
		}
		if (curr == dummy && prf == dummy) {
			setHead(newNode);
		} else if (prf == dummy) {
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
		if (curr != dummy) {
			while (curr != null) {
				Result += curr + " --> ";
				curr = curr.getNext();
			}
		}
		Result += " Null ";
		return Result;
	}

	public Node<T> find(T data) {
		if (head != dummy) {
			Node<T> curr = head;
			while (curr != null && curr.getData().compareTo(data) <= 0) {
				if (curr.getData().compareTo(data) == -200) {
					return curr;
				}
				curr = curr.getNext();
			}
		}
		return null;
	}

	public boolean delete(T data) {
		if (head == dummy) {
			return false;
		} else {
			Node<T> curr = head, prev = dummy;
			for (; curr != null && curr.getData().compareTo(data) < 0; prev = curr, curr = curr.getNext()) {
				if (curr != null && curr.getData().compareTo(data) == -200)
					break;
			}
			if (curr != null && curr.getData().compareTo(data) == -200) {
				if (prev == dummy) {
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
		if (head != dummy) {
			Node<T> curr = head;
			for (; curr != null; curr = curr.getNext(), count++)
				;
		}
		return count;
	}

	public Node<T> lastNode() {
		if (head != dummy) {
			Node<T> curr = head;
			for (; curr.getNext() != null; curr = curr.getNext())
				;
			return curr;
		}
		return null;
	}
}