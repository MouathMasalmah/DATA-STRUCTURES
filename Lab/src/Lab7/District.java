package Lab7;

public class District implements Comparable<District> {
	private BSTreeNew<Location> locationList = new BSTreeNew<>();
	private String districtName;

	public District() {

	}

	public District(String districtName) {
		this.districtName = districtName;
	}

	public BSTreeNew<Location> getList() {
		return locationList;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@Override
	public String toString() {
		return districtName;
	}

	@Override
	public int compareTo(District o) {
		if (this.districtName.equalsIgnoreCase(o.getDistrictName()) == false) {
			return -1;
		}
		return 0;
	}

	public int numberOfMartyr() {
		int count = 0;
		locationList.traverseInOrder();
		LinkedStack<Location> temp = locationList.getNextStack();
		while (!temp.isEmpty()) {
			count += temp.pop().getData().numberOfMartyr();
		}
		return count;
	}

	public boolean insertMartyr(Martyr newMartyr) {
		Location location = new Location(newMartyr.getLocation());
		TNode<Location> searchLocation = this.locationList.find(location);
		if (searchLocation == null) {
			location.insertMartyr(newMartyr);
			this.locationList.insert(location);
			return false;
		}

		searchLocation.getData().getMartyrList().push(newMartyr);
		return true;
	}

}
