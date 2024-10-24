package application;



public class QStack<T extends Comparable<T>> {
    private LinkedListQueue<T> queue1;
    private LinkedListQueue<T> queue2;

    public QStack() {
        queue1 = new LinkedListQueue<>();
        queue2 = new LinkedListQueue<>();
    }

    public void push(T data) {
        queue1.enqueue(data);
    }

    public T pop() {
        if (queue1.isEmpty()) {
            return null;
        }
        while (queue1.size() > 1) {
            queue2.enqueue(queue1.dequeue());
        }
        T data = queue1.dequeue();
        LinkedListQueue<T> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
        return data;
    }

    public T peek() {
        if (queue1.isEmpty()) {
            return null;
        }
        while (queue1.size() > 1) {
            queue2.enqueue(queue1.dequeue());
        }
        T data = queue1.dequeue();
        queue2.enqueue(data);
        LinkedListQueue<T> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
        return data;
    }

    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    public String toString() {
        return queue1.toString();
    }

    public void traverse() {
    	LinkedListQueue<T> temp = new LinkedListQueue<>();
        while (!queue1.isEmpty()) {
            T data = queue1.dequeue();
            System.out.print(data + " ");
            temp.enqueue(data);
        }
        System.out.println();
        queue1 = temp;
    }

	public void clear() {
		 queue1.clear();
		 queue2.clear();
	}
}
