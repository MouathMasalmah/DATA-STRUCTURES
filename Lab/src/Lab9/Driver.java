package Lab9;

import Lab10.SHash;

public class Driver {
    public static void main(String[] args){
        SHash<Integer> hash = new SHash<>(10);
        hash.add(2);
        for (int i = 0; i < hash.getSize(); i++) {
            if (!hash.getHashTable()[i].isEmpty()) {
                System.out.println("Index " + i + ": " );
                hash.getHashTable()[i].traverse();
            }
        }
    }
}
