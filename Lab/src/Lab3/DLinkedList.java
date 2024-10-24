package Lab3;

public class DLinkedList<T extends Comparable<T>> {
	DNode<T> head;

	public void insert(T data) {
		DNode<T> newDNode = new DNode<>(data);
		DNode<T> curr = head;
		for (; curr != null && curr.getData().compareTo(data) < 0 && curr.getNext() != null; curr = curr.getNext())
			;
		if (curr == null) // case 0: empty list
			head = newDNode;
		else if (curr.getData().compareTo(data) >= 0 && curr.getPrev() == null) { // case
																					// 1:
																					// insert
																					// at
																					// first
			newDNode.setNext(curr);
			curr.setPrev(newDNode);
			head = newDNode;
		} else if (curr.getData().compareTo(data) < 0 && curr.getNext() == null) { // case
																					// 3:
																					// insert
																					// at
																					// last
			newDNode.setPrev(curr);
			curr.setNext(newDNode);
			;
		} else { // case 2: insert between
			newDNode.setNext(curr);
			newDNode.setPrev(curr.getPrev());
			curr.getPrev().setNext(newDNode);
			curr.setPrev(newDNode);
		}
	}

	public DNode<T> find(T data) {
		DNode<T> curr = head;
		if (curr != null && curr.getNext() != null && curr.getData().compareTo(curr.getNext().getData()) < 0) {
			while (curr != null && curr.getData().compareTo(data) <= 0) {
				if (curr.getData().compareTo(data) == 0) {
					return curr;
				}
				curr = curr.getNext();
			}
		} else  {
			while (curr != null && curr.getData().compareTo(data) >= 0) {
				if (curr.getData().compareTo(data) == 0) {
					return curr;
				}
				curr = curr.getNext();
			}
		}
		return null;
	}

	public DNode<T> delete(T data) {
		DNode<T> curr = head;
		while (curr != null && curr.getData().compareTo(data) <= 0) {
			if (curr.getData().compareTo(data) == 0)
				break;
			curr = curr.getNext();
		}

		if (curr != null && curr.getData().compareTo(data) == 0) { // found
			if (curr.getPrev() == null && curr.getNext() == null) // case 0:
																	// delete
																	// one item
				head = null;
			else if (curr.getPrev() == null) { // case 1: delete 1st item
				curr.getNext().setPrev(null);
				head = curr.getNext();
			} else if (curr.getNext() == null) { // case 3: delete last item
				curr.getPrev().setNext(null);
			} else { // case 2: delete between
				curr.getPrev().setNext(curr.getNext());
				curr.getNext().setPrev(curr.getPrev());
			}
			return curr;
		}
		return null;
	}

	public int length() {
		DNode<T> curr = head;
		int count = 0;
		while (curr != null) {
			count++;
			curr = curr.getNext();
		}
		return count;
	}

	public int lengthRec() {

		return lengthRec(head);
	}

	private int lengthRec(DNode<T> curr) {
		if (curr == null) {
			return 0;
		}

		return 1 + lengthRec(curr.getNext());
	}

	public String traverse() {
		String Resulte = "Head-->";
		DNode<T> curr = head;
		while (curr != null) {
			Resulte += curr + "-->";
			curr = curr.getNext();
		}
		return Resulte += "Null";
	}

	public void traverseReverce() {
		System.out.print("Null -> ");
		DNode<T> curr = head;
		while (curr != null && curr.getNext() != null) {
			curr = curr.getNext();
		}

		while (curr != null) {
			System.out.print(curr + " ");
			curr = curr.getPrev();
		}
		System.out.println("Head");
	}

	public void RemoveDuplicate() {
		DNode<T> curr = head;
		while (curr != null && curr.getNext() != null) {
			if (curr.getData().compareTo(curr.getNext().getData()) == 0) {
				delete(curr.getNext().getData());
			}
			curr = curr.getNext();
		}
	}

	public void RemoveDuplicateRec() {
		if (head != null) {
			RemoveDuplicateRec(head);
		}
	}

	private void RemoveDuplicateRec(DNode<T> curr) {
		if (curr != null && curr.getNext() != null) {
			if (curr.getData().compareTo(curr.getNext().getData()) == 0) {
				delete(curr.getNext().getData());
			}
			RemoveDuplicateRec(curr.getNext());
		}
	}

	public void ReverseRec() {
		if (head != null) {
			ReverseRec(head);

		}
	}

	private void ReverseRec(DNode<T> curr) {

		if (curr == null) {
			return;
		}
		DNode<T> next = curr.getNext();
		curr.setNext(curr.getPrev());
		curr.setPrev(next);

		if (next == null) {
			head = curr;
		}

		ReverseRec(next);
	}

}
