package Lab10;

public class QHash<T extends Comparable<T>> {

	private HNode<T>[] hashTable;
	private int size;
	int collision;
	int number;

	public QHash(int n) {
		this.size = n * 2;
		hashTable = new HNode[this.size];
		for (int i = 0; i < this.size; i++) {
			hashTable[i] = new HNode<>(null);
		}
	}

	public void add(T data) {
		number++;
		int index = Math.abs(data.hashCode() % size);
		int firstIndex = index;
		if (hashTable[index].getFlag() == 'E') {
			hashTable[index].setData(data);
			hashTable[index].setFlag('F');
		} else {
			for (int i = 0; hashTable[(index + i * i) % size].getFlag() == 'F'; i++) {
				collision++;
				index = (index + i * i) % size;
				if (index == firstIndex) {
					System.out.println("Table is full");
					return;
				}
			}
			hashTable[(index) % size].setData(data);
			hashTable[(index) % size].setFlag('F');
		}
	}

	public HNode<T>[] getHashTable() {
		return hashTable;
	}

	public int getSize() {
		return size;
	}

	public void avgCollision() {
		int avg = 0;
		if (number != 0)
			avg = collision / number;
		System.out.println("the avg of Qur Hashing = " + avg);
	}
}