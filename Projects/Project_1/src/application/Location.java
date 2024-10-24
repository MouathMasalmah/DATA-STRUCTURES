package application;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Location implements Comparable<Location> {
	private Martyr dumMartyr = new Martyr("dummy", "", 0, "", "", "");
	private linkedList<Martyr> martyrList = new linkedList<>(dumMartyr);
	private String locationName;

	public Location() {

	}

	public Location(String locationName) {
		this.locationName = locationName;
	}

	public linkedList<Martyr> getMartyrList() {
		return martyrList;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Override
	public String toString() {
		return locationName;
	}

	@Override
	public int compareTo(Location o) {
		if (this.locationName.equalsIgnoreCase(o.getLocationName()) == false) {
			return -1;
		}
		return -200;

	}

	public boolean insertMartyr(Martyr martyr) {
		Node<Martyr> newMatryr = martyrList.find(martyr);
		if (newMatryr == null) {
			martyrList.insert(martyr);
			return true;
		}
		return false;
	}

	public int totalNumberOfMartyr() {
		return martyrList.length();
	}

	public int totalNumberByGenderMale() {
		int totalMartyrMale = 0;

		Node<Martyr> curr = martyrList.getHead();
		for (; curr != martyrList.getDummy() && curr != null; curr = curr.getNext()) {
			if (curr.getData().getGender().equalsIgnoreCase("M")) {
				totalMartyrMale++;
			}
		}
		return totalMartyrMale ;
	}
	public int totalNumberByGenderFamale() {
		
		int totalMartyrFamale = 0;

		Node<Martyr> curr = martyrList.getHead();
		for (; curr != martyrList.getDummy() && curr != null; curr = curr.getNext()) {
			if (curr.getData().getGender().equalsIgnoreCase("F")) {
				totalMartyrFamale++;
			}
		}
		return totalMartyrFamale ;
	}

	public int averageAge() {
		int sumAllAge = 0;
		int length = 0;
		Node<Martyr> curr = martyrList.getHead();
		for (; curr != martyrList.getDummy() && curr != null; curr = curr.getNext()) {
			if (curr.getData().getAge() != -1) {
				length++;
				sumAllAge += curr.getData().getAge();
			}
		}
		if (length == 0) {
			return 0;
		}
		int average = sumAllAge / length;
		return average;
	}

	public String youngAndOldMartyr() {
		String result = "The youngest: " + martyrList.getHead().getData().getName() + "\n" + "The oldest: "
				+ martyrList.lastNode().getData().getName();
		return result;
	}

	public String searchByPartOfName(String partName) {
		Node<Martyr> curr = martyrList.getHead();
		String result = null;
		String martyrName = null;
		if (curr != null && curr.equals(martyrList.getDummy()) == false) {
			for (; curr != null; curr = curr.getNext()) {
				martyrName = curr.getData().getName();
				if (martyrName.toLowerCase().contains(partName.toLowerCase()) == true) {
					if (result != null) {
						result += curr.getData().toString();
					} else {
						result = curr.getData().toString();
					}
				}
			}
		}
		return result;
	}

	public boolean deleteMartyer(String martyerName) {
		Node<Martyr> searchMartyr = findMartyerByName(martyerName);
		if(searchMartyr != null) {
		return martyrList.delete(searchMartyr.getData());
		}
		return false;
	}

	public Node<Martyr> findMartyerByName(String martyerName) {
		Node<Martyr> searchMartyr = martyrList.getHead();
		for (; searchMartyr != null && searchMartyr != martyrList.getDummy(); searchMartyr = searchMartyr.getNext()) {
			if (searchMartyr.getData().getName().equalsIgnoreCase(martyerName) == true) {
				return searchMartyr;
			}
		}
		return null;
	}

	public int totalNumberOfMartyrByDate(String martyerDate) {
		int sum = 0;
		
		Node<Martyr> searchMartyr = martyrList.getHead();
		for (; searchMartyr != null && searchMartyr != martyrList.getDummy(); searchMartyr = searchMartyr.getNext()) {
			if(martyerDate.equals("N/A") == false) {
			String []Split1 = martyerDate.split("/");
			String []Split2 = searchMartyr.getData().getDate().split("/");
			int day1 = Integer.parseInt(Split1[1]);
			int month1 = Integer.parseInt(Split1[0]) - 1;
			int year1 = Integer.parseInt(Split1[2]);

			int day2 = Integer.parseInt(Split2[1]);
			int month2 = Integer.parseInt(Split2[0]) - 1;
			int year2 = Integer.parseInt(Split2[2]);

			Calendar dateObject1 = new GregorianCalendar(year1, month1, day1);
			Calendar dateObject2 = new GregorianCalendar(year2, month2, day2);



			if (dateObject1.equals(dateObject2)) {
				sum++;
			}
			}else {
				if(martyerDate.compareTo(searchMartyr.getData().getDate()) == 0) {
					sum++;
				}
			}
		}
		return sum;
	}

	public void updateMartyrLocation() {
		Node<Martyr> curr = martyrList.getHead();
		for(;curr != null && curr != martyrList.getDummy();curr=curr.getNext()) {
			curr.getData().setLocation(locationName);
		}
	}
}
