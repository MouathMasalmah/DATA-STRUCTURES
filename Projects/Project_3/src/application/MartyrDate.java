package application;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class MartyrDate implements Comparable<MartyrDate> {
	private String date;
	private AVLTree<Martyr> martyrList = new AVLTree<>();

	public MartyrDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public AVLTree<Martyr> getMartyrTree() {
		return martyrList;
	}

	public void setMartyrTree(AVLTree<Martyr> martyrList) {
		this.martyrList = martyrList;
	}

	public Martyr oldest() {
		martyrList.traverseInOrder();
		QStack<TNode<Martyr>> martyrs = martyrList.getNextStack();
		TNode<Martyr> oldestMartyr = null;
		if (!martyrs.isEmpty()) {
			oldestMartyr = martyrs.pop();
			TNode<Martyr> curr ;
			while (!martyrs.isEmpty()) {
				curr = martyrs.pop();
				if (curr.getData().getAge() > oldestMartyr.getData().getAge()) {
					oldestMartyr = curr;
					break;
				} else {
					return oldestMartyr.getData();
				}
			}
		}
		return oldestMartyr.getData();
	}

	public Martyr youngest() {
		martyrList.traverseInOrder();
		QStack<TNode<Martyr>> martyrs = martyrList.getNextStack();
		TNode<Martyr> youngestMartyr = null ;
		if (!martyrs.isEmpty()) {
			youngestMartyr = martyrs.pop();
			TNode<Martyr> curr;
			while (!martyrs.isEmpty()) {
				curr = martyrs.pop();
				if (curr.getData().getAge() < youngestMartyr.getData().getAge()) {
					youngestMartyr = curr;
					break;
				} else {
					return youngestMartyr.getData();
				}
			}
		}
		return youngestMartyr.getData();
	}

	public int avgAge() {
		int avgAge = 0;
		int numberOfMartyrs = 0;
		martyrList.traverseInOrder();
		QStack<TNode<Martyr>> martyrs = martyrList.getNextStack();
		TNode<Martyr> martyr;
		while (!martyrs.isEmpty()) {
			martyr = martyrs.pop();
			if (martyr.getData().getAge() >= 0) {
				avgAge += martyr.getData().getAge();
				numberOfMartyrs++;
			}
		}
		if (numberOfMartyrs != 0)
			return avgAge / numberOfMartyrs;
		return 0;
	}

	public Martyr searchByName(String partName) {
		martyrList.traverseInOrder();
		QStack<TNode<Martyr>> martyrs = martyrList.getNextStack();
		String martyrName = null;
		TNode<Martyr> curr ;
		while (!martyrs.isEmpty()) {
			curr = martyrs.pop();
			martyrName = curr.getData().getName();
			if (martyrName.toLowerCase().compareTo(partName.toLowerCase()) == 0) {
				return curr.getData();
			}
		}
		return null;

	}

	public boolean deleteMartyer(TNode<Martyr> martyerName) {
		martyrList.traverseInOrder();
		QStack<TNode<Martyr>> martyrs = martyrList.getNextStack();
		TNode<Martyr> searchMartyr = null ;
		TNode<Martyr> temp ;
		while (!martyrs.isEmpty()) {
			temp = martyrs.pop();
			if (temp.getData().getName().compareToIgnoreCase(martyerName.getData().getName()) == 0) {
				searchMartyr = temp;
				break;
			}
		}
		return martyrList.delete(searchMartyr.getData()) != null;

	}

	@Override
	public String toString() {
		return date;
	}

	@Override
	public int compareTo(MartyrDate o) {
		String[] Split1 = this.getDate().split("/");
		String[] Split2 = o.getDate().split("/");
		int day1 = Integer.parseInt(Split1[1]);
		int month1 = Integer.parseInt(Split1[0]) - 1;
		int year1 = Integer.parseInt(Split1[2]);

		int day2 = Integer.parseInt(Split2[1]);
		int month2 = Integer.parseInt(Split2[0]) - 1;
		int year2 = Integer.parseInt(Split2[2]);

		Calendar dateObject1 = new GregorianCalendar(year1, month1, day1);
		Calendar dateObject2 = new GregorianCalendar(year2, month2, day2);
		return dateObject1.compareTo(dateObject2);
	}

	@Override
	public int hashCode() {
		String[] Split1 = this.getDate().split("/");
		int day1 = Integer.parseInt(Split1[1]);
		int month1 = Integer.parseInt(Split1[0]) - 1;
		int year1 = Integer.parseInt(Split1[2]);
		Calendar dateObject = new GregorianCalendar(year1, month1, day1);
		
		return Objects.hash(dateObject.hashCode());
	}
}
