package Lab3;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DLinkedList<Integer> list = new DLinkedList<>();
		list.insert(50);
		list.insert(60);
		list.insert(70);
		list.insert(70);
		list.traverse();
		list.delete(90);
		list.insert(90);
		list.insert(90);
		list.insert(90);
		list.traverse();
		list.traverse();
		list.delete(530);
		list.insert(100);
		list.traverse();
		list.RemoveDuplicateRec();
		list.traverse();
		list.ReverseRec();
		list.traverse();
		System.out.println(list.find(90));
		
	}

}