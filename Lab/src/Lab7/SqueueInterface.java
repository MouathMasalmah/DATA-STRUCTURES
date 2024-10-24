package Lab7;

public interface SqueueInterface<T extends Comparable<T>> {
	public T dequeue();

	public T getFront();

	public void enqueue(T data);

	public void clear();

	public boolean isEmpty();
	
	public String print();
}
