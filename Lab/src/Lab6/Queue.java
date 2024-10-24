package Lab6;

import java.util.Random;

public class Queue {
	static Squeue<Integer> test = new Squeue<>();
	static Squeue<Integer> temp = new Squeue<>();

	public static void main(String[] args) {
		Random random = new Random();
		int randomNum = 0;
		for (int i = 0; i < 60; i++) {
			randomNum = random.nextInt(4);
			if (randomNum == 4 || randomNum ==0) {
				if (test.isEmpty()) {
					System.out.println("Min: " + i + " in: 0" + " Total: " + 0 + " out: " + 0);
				} else {
					test.dequeue();
					int res = print();
					System.out.println("Min: " + i + " in: 0" + " Total: " + res + " out: " + 1);
				}
			} else if (randomNum == 1) {
				if (test.isEmpty()) {
					test.equeue(1);
					System.out.println("Min: " + i + " in: 1" + " Total: " + 1 + " out: " + 0);
				} else {
					test.equeue(1);
					test.dequeue();
					int res = print();
					System.out.println("Min: " + i + " in: 1" + " Total: " + res + " out: " + 1);
				}
			} else {
				if (test.isEmpty()) {
					test.equeue(1);
					test.equeue(1);
					System.out.println("Min: " + i + " in: 2" + " Total: " + 2 + " out: " + 0);
				} else {
					test.equeue(1);
					test.equeue(1);
					test.dequeue();
					int res = print();
					test.dequeue();
					System.out.println("Min: " + i + " in: 2" + " Total: " + res + " out: " + 1);
				}
			}
		}
	}

	public static int print() {
		int result = 0;
		while (test.isEmpty() == false) {
			result += test.getFront();
			temp.equeue(test.dequeue());
		}
		while (temp.isEmpty() == false) {
			test.equeue(temp.dequeue());
		}
		return result;
	}
}
