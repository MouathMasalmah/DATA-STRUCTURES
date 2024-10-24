package application;



public abstract class OpenAddressignHash<T extends Comparable<T>> {
	
	private HNode<T>[] table;
	private int m, size, collisions;
	
	@SuppressWarnings("unchecked")
	protected OpenAddressignHash(int dataSize) {
		m = dataSize * 2;
		for(; !isPrime(++m); );
		table = new HNode[m];
		for (int i = 0; i < m; i++)
			table[i] = new HNode<>(null);
	}
	
	public HNode<T>[] getTable() {
		return table;
	}

	public void setTable(HNode<T>[] table) {
		this.table = table;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setCollisions(int collisions) {
		this.collisions = collisions;
	}

	public int getCollisions() {
		return collisions;
	}

	public abstract void add(T data);
	
	public abstract HNode<T> find(T data);
	
	public HNode<T> delete(T data) {
		HNode<T> deleted = find(data);
		if (deleted != null) deleted.setFlag(Flag.DELETED);
		--size;
		return deleted;
	}
	
	private boolean isPrime(int n) {
		for (int i = 2; i * i <= n; i++)
			if (n % i == 0) return false;
		return true;
	}
	
	public void traverse() {
		for (int i = 0; i < m; i++) 
			if (table[i].getData() != null)
				System.out.print(i + "" + table[i] + " - ");
	}
	
	@SuppressWarnings("unchecked")
	protected void rehash() {
		int tempM = m;
		HNode<T>[] tempTable = table;
		
		m *= 2;
		for(; !isPrime(++m); );
		table = new HNode[m];
		for (int i = 0; i < m; i++)
			table[i] = new HNode<>(null);
		size = 0;

		for (int i = 0; i < tempM; i++) 
			if (tempTable[i].getFlag() == Flag.FULL)
				add(tempTable[i].getData());
	}

}