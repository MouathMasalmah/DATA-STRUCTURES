package Lab10;

public class DHash<T extends Comparable<T>> {

	private HNode<T>[] hashTable;
	private int size;
	int collision;
	int number;

	public DHash(int n) {
		this.size = n * 2;
		hashTable = new HNode[this.size];
		for (int i = 0; i < this.size; i++) {
			hashTable[i] = new HNode<>(null);
		}
	}

	public void add(T data) {
		number++;
		int h1 = Math.abs((data.hashCode() % 100) % size);
		int h2 = Math.abs((data.hashCode() % 100 % 100) % size);
		int index = h1;
		if (hashTable[index].getFlag() == 'E') {
			hashTable[index].setData(data);
			hashTable[index].setFlag('F');
		} else {
			for (int i = 0; hashTable[(h1 + i * h2) % size].getFlag() == 'F'; i++) {
				index = (h1 + i * h2) % size;
				collision++;

			}
			hashTable[(h1) % size].setData(data);
			hashTable[(h1) % size].setFlag('F');
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
		if (avg != number)
			avg = collision / number;
		System.out.println("the avg of Linear Hashing = " + avg);
	}
}