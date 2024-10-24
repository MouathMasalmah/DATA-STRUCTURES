package Lab4;

public class CursorArray<T extends Comparable<T>> {

	private CNode<T>[] cursorArray;

	public CursorArray(int capacity) {
		cursorArray = new CNode[capacity];
		initialization();
	}

	public void initialization() {
		for (int i = 0; i <= cursorArray.length - 2; i++)
			cursorArray[i] = new CNode<T>(null, i + 1);

		cursorArray[cursorArray.length - 1] = new CNode<T>(null, 0);
	}

	public int malloc() {
		int p = cursorArray[0].getNext();
		cursorArray[0].setNext(cursorArray[p].getNext());

		return p;
	}

	public void free(int p) {
		cursorArray[p] = new CNode<>(null, cursorArray[0].getNext());
		cursorArray[0].setNext(p);
	}

	public boolean isEmpty(int indexOfList) {
		return cursorArray[indexOfList].getNext() == 0;
	}

	public boolean isLast(int pos) {
		return cursorArray[pos].getNext() == 0;
	}

	public int createList() {
		int index = malloc();

		if (index != 0) {
			cursorArray[index] = new CNode<>(null, 0);
		} else {
			System.out.println("CursorArray is full!");
		}

		return index;
	}

	public void insertAtHead(T data, int indexOfList) {
		int pos = malloc();

		if (pos != 0) {
			cursorArray[pos] = new CNode<T>(data, cursorArray[indexOfList].getNext());
			cursorArray[indexOfList].setNext(pos);
		} else {
			System.out.println("CursorArray is full!");
		}
	}

	public T getFirst(int indexOfList) {
		if (isEmpty(indexOfList))
			return null;

		return cursorArray[cursorArray[indexOfList].getNext()].getData();
	}

	public int find(T value, int indexOfList) {
		while (!isEmpty(indexOfList)) {
			indexOfList = cursorArray[indexOfList].getNext();
			if (cursorArray[indexOfList].getData().compareTo(value) == 0)
				return indexOfList;
		}
		return -1;
	}

	public CNode<T> delete(T value, int indexOfList) {
		if (isEmpty(indexOfList))
			return null;
		while (!isEmpty(indexOfList)) {
			if (cursorArray.length > 2 && cursorArray[cursorArray[indexOfList].getNext()].getData().compareTo(value) == 0) {
				int targetIndex = cursorArray[indexOfList].getNext();
				CNode<T> targetNode = cursorArray[targetIndex];
				cursorArray[indexOfList].setNext(targetNode.getNext());
				free(targetIndex);
			}
			indexOfList = cursorArray[indexOfList].getNext();
		}
		return null;
	}

	public void traverse() {
		System.out.println("Cursor Array:");
		for (int i = 0; i < cursorArray.length; i++) {
			System.out.println(i + "\t" + cursorArray[i].getData() + "\t" + cursorArray[i].getNext());
		}
	}

	public String traverse(int indexOfList) {
		String Result ="List at " + indexOfList + ": \n";
		Result+= "index\tvalue\tnext\n" ;
		while (!isEmpty(indexOfList)) {
			indexOfList = cursorArray[indexOfList].getNext();
			Result+=indexOfList + "\t" + cursorArray[indexOfList].getData() + "\t"
					+ cursorArray[indexOfList].getNext()+"\n";
		}
		return Result;
	}

	public void insertAtTail(T data, int indexOfList) {
		int pos = malloc();

		if (pos != 0) {
			CNode<T> ptr = cursorArray[cursorArray[indexOfList].getNext()];
			while (ptr.getNext() != 0) {
				ptr = cursorArray[ptr.getNext()];
			}

			System.out.println(ptr);

			cursorArray[pos] = new CNode<T>(data, 0);
			ptr.setNext(pos);
		} else {
			System.out.println("CursorArray is full!");
		}
	}

	public CNode<T> deleteAtFirst(int indexOfList) {
		if (isEmpty(indexOfList))
			return null;
		int targetIndex = cursorArray[indexOfList].getNext();
		CNode<T> target = cursorArray[targetIndex];
		if (targetIndex == 0)
			return null;
		cursorArray[indexOfList].setNext(cursorArray[targetIndex].getNext());
		free(targetIndex);
		return target;
	}

	public CNode<T> deleteLast(int indexOfList) {
		if (isEmpty(indexOfList))
			return null;
		CNode<T> ptr = cursorArray[indexOfList];
		while (!isLast(ptr.getNext())) {
			ptr = cursorArray[ptr.getNext()];
		}

		CNode<T> target = cursorArray[ptr.getNext()];
		int targetIndex = ptr.getNext();

		if (targetIndex == 0)
			return null;
		ptr.setNext(0);
		free(targetIndex);
		return target;
	}

	public int getLength(int indexOfList) {
		if (isEmpty(indexOfList))
			return 0;
		int length = 0;

		CNode<T> ptr = cursorArray[indexOfList];
		while (ptr.getNext() != 0) {
			length++;
			ptr = cursorArray[ptr.getNext()];
		}

		return length;
	}

	public int getLengthRecursive(int indexOfList) {
		if (isEmpty(indexOfList))
			return 0;

		return getLengthRecursive(cursorArray[indexOfList].getNext(), 0);
	}

	private int getLengthRecursive(int ptr_index, int length) {
		if (ptr_index == 0)
			return length;
		return getLengthRecursive(cursorArray[ptr_index].getNext(), length + 1);

	}

	public void removeList(int indexOfList) {
		while (indexOfList != 0) {
			int temp_next = cursorArray[indexOfList].getNext();

			free(indexOfList);
			indexOfList = temp_next;

		}
	}

	public void removeListRecursuive(int indexOfList) {
		if (indexOfList == 0)
			return;
		removeListRecursuive(cursorArray[indexOfList].getNext());
		free(indexOfList);
	}

	public void merge2Lists(int l1, int l2) {
		if (isEmpty(l1)) {
			cursorArray[l1].setNext(cursorArray[l2].getNext());
			free(l2);
		} else if (isEmpty(l2)) {
			free(l2);
		} else {
			CNode<T> ptr = cursorArray[l1];
			while (ptr.getNext() != 0) {
				ptr = cursorArray[ptr.getNext()];
			}

			ptr.setNext(cursorArray[l2].getNext());
			free(l2);
		}
	}
}
