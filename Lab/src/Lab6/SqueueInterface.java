package Lab6;

public interface SqueueInterface<T extends Comparable<T>> {
	public T dequeue();

	public T getFront();

	public void equeue(T data);

	public void clear();

	public boolean isEmpty();
	
	public String print();
}
