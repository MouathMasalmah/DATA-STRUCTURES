package Lab7;

public class Location implements Comparable<Location> {
	private LinkedStack<Martyr> martyrList = new LinkedStack<>();
	private String locationName;

	public Location() {

	}

	public Location(String locationName) {
		this.locationName = locationName;
	}

	public LinkedStack<Martyr> getMartyrList() {
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
		martyrList.push(martyr);

		return true;
	}

	public Node deleteMartyer(String martyerName) {

		return martyrList.pop();

	}

	public int numberOfMartyr() {
		int count = 0;
		LinkedStack<Martyr> tempStack = new LinkedStack<>();
		while (!martyrList.isEmpty()) {
			tempStack.push(martyrList.pop().getData());
			count++;
		}
		while (!tempStack.isEmpty()) {
			martyrList.push(tempStack.pop().getData());
		}
		return count;
	}

}
