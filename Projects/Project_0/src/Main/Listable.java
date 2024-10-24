//Mouath Masalmah,1220179 , mamoun nawahda
package Main;

interface Listable<T extends Comparable<T>> {
	public void add(T o);

	public boolean delete(T o);

	public boolean delete(int i);

	public int find(T o);

	public T get(int i);

	public boolean isEmpty();

	public void clear();

}
