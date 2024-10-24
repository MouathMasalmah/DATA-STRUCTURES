package Lab7;

public class Squeue<T extends Comparable<T>> implements SqueueInterface<T> {
	private LinkedStack<T> labSixStack = new LinkedStack<>();
	private LinkedStack<T> tempStack = new LinkedStack<>();

	@Override
	public void enqueue(T data) {
		while (labSixStack.isEmpty() == false) {
			tempStack.push(labSixStack.pop().getData());
		}
		tempStack.push(data);
		while (tempStack.isEmpty() == false) {
			labSixStack.push(tempStack.pop().getData());
		}

	}

	@Override
	public T dequeue() {
		if (labSixStack.isEmpty() == false) {
			return labSixStack.pop().getData();
		}
		return null;
	}

	@Override
	public T getFront() {
		Node<T> peek = labSixStack.peek();
		if (peek != null) {
			return peek.getData();
		}
		return null;
	}

	@Override
	public void clear() {
		labSixStack.clear();

	}

	@Override
	public boolean isEmpty() {
		return labSixStack.isEmpty();
	}

	@Override
	public String print() {
		String result="Head-> ";
		while (labSixStack.isEmpty() == false) {
			result+=labSixStack.peek()+" -> ";
			tempStack.push(labSixStack.pop().getData());
		}
		result+="Null";
		while (tempStack.isEmpty() == false) {
			labSixStack.push(tempStack.pop().getData());
		}
		return result;
	}

}
