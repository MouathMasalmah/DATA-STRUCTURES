package Lab2;
public class Main<T extends Comparable<T>> {

	public static void main(String[] args) {
		linkedList<Integer> List = new linkedList<>();
		List.insert(55);
		List.insert(50);
		List.insert(20);
		List.insert(60);
		List.insert(65);
		List.insert(99);
		List.traverse();
		System.out.println("\n"+List.delete(55));
		System.out.println(List.RecFind(55));
		List.traverse();
		List.reverse() ;
		List.traverse();
		List.reverse() ;
		List.traverse();
	}

}
