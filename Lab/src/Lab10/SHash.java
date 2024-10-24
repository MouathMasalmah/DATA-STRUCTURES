package Lab10;


public class SHash <T extends Comparable<T>>{

    private LinkedList<T>[] hashTable;
    private int size;

    SHash (int size){
        this.size = size;
        hashTable = new LinkedList[size];
        for(int i = 0; i < size; i++){
            hashTable[i] = new LinkedList<>();
        }
    }

    public void add(T data){
        int index = Math.abs(data.hashCode() % size);
        hashTable[index].insert(data);
    }

    public void delete(T data){
        int index = Math.abs(data.hashCode() % size);
        hashTable[index].delete(data);
    }

    public boolean find(T data){
        int index = Math.abs(data.hashCode() % size);
        return hashTable[index].find(data);
    }

    public LinkedList<T>[] getHashTable() {
        return hashTable;
    }

    public int getSize() {
        return size;
    }
}
