package Lab0;

public class MyList<T extends Comparable<T>> implements Listable<T> {
	T[] List;
	int count;

	public MyList(int i) {
		List = (T[]) new Comparable[i];

	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public void add(T data) {
		if (count >= List.length) {
			resize();

		}
		List[count++] = data;
		
	}

	@Override
	public boolean delete(T o) {
		int index = find(o);
		if (index != -1) {
			for (int i = index + 1; i < count; i++) {
				List[i-1] = List[i];
				count--;
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean delete(int index) {

		if (index > -1 && index < count) {
			for (int i = index + 1; i < count; i++) {
				List[i-1] = List[i];
				count--;
				return true;
			}
		}

		return false;
	}

	@Override
	public int find(T o) {

		for (int i = 0; i < count; i++) {
			if (List[i].compareTo(o) == 0) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public T get(int i) {
		if (List[i] == null) {
			return null;
		}
		return List[i];
	}

	@Override
	public void traverse() {
		System.out.println("index" + "/t" + "Delete" + "/n" + "======================");
		for (int i = 0; i < count; i++) {
			System.out.println(i + "/t" + List[i] + "/n");
		}

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		count = 0;
	}

	private void resize() {
		T[] NewList = (T[]) new Comparable[List.length * 2];
		System.arraycopy(List, 0, NewList, 0, List.length);
		List = NewList;
	}

}