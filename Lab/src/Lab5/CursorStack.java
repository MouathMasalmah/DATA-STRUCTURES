package Lab5;

public class CursorStack<T extends Comparable<T>> implements Stackable<T> {
	private CursorArray<T> cursorArray;
	private int stackList;
	
	public CursorStack(int capacity) {
		cursorArray = new CursorArray<>(capacity);
		stackList = cursorArray.createList();
	}
	
	@Override
	public void push(T data) {
		cursorArray.insertAtHead(data, stackList);
	}

	@Override
	public T pop() {
		if (cursorArray.isEmpty(stackList)) {
			System.out.println("The stack is empty");
			return null;
		}
		return cursorArray.deleteAtFirst(stackList).getData();
	}

	@Override
	public T peek() {
		return cursorArray.getFirst(stackList);
	}

	@Override
	public void clear() {
		cursorArray.removeList(stackList);
		stackList = cursorArray.createList();
	}

	@Override
	public boolean isEmpty() {
		return cursorArray.isEmpty(stackList);
	} 

	public String traverse() {
		return cursorArray.traverse(stackList);
	}
	
}