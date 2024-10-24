package Lab9;

public class OHash <T extends Comparable<T>>{

    private HNode<T>[] hashTable;
    private int size;

    public OHash (int n){
        this.size = n*2;
        hashTable = new HNode[this.size];
        for(int i = 0; i < this.size; i++){
            hashTable[i] = new HNode<>(null);
        }
    }

    public void add(T data){
        int index = Math.abs(data.hashCode() % size);
        int firstIndex = index;
        if (hashTable[index].getFlag() == 'E') {
            hashTable[index].setData(data);
            hashTable[index].setFlag('F');
        } else {
            while (hashTable[(index + 1) % size].getFlag() == 'F') {
                index = (index + 1) % size;
                if(index == firstIndex){
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
}