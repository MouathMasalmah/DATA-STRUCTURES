package application;

public class District implements Comparable<District> {
	private Location dummLocation = new Location("dummy");
	private linkedList<Location> locationList = new linkedList<>(dummLocation);
	private DateCount dumDateCount = new DateCount("dummy");
	private linkedList<DateCount> dateCountList = new linkedList<>(dumDateCount);
	private String districtName;

	public District() {

	}

	public District(String districtName) {
		this.districtName = districtName;
	}

	public linkedList<Location> getList() {
		return locationList;
	}

	public linkedList<DateCount> getDateCountList() {
		return dateCountList;
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

	public boolean updateMartyrLocation(Location oldLocation, String newLocationName) {
		Node<Location> findOldLocation = locationList.find(oldLocation);
		if (findOldLocation != null) {
			Location newLocation = new Location(newLocationName);
			Node<Location> findNewLocation = locationList.find(newLocation);
			if (findNewLocation == null) {
				findOldLocation.getData().setLocationName(newLocationName);
				findOldLocation.getData().updateMartyrLocation();
				return true;
			}

		}
		return false;
	}

	public void updateMartyrDistrict() {
		Node<Location> currLocation=locationList.getHead();
		for(;currLocation!=null && currLocation!=locationList.getDummy();currLocation=currLocation.getNext()) {
			Node<Martyr>currMartyr = currLocation.getData().getMartyrList().getHead();
			for(;currMartyr!=null && currMartyr!=currLocation.getData().getMartyrList().getDummy();currMartyr=currMartyr.getNext()) {
				currMartyr.getData().setDistrict(districtName);
			}
		}
	}

	public boolean insertMartyr(Martyr newMartyr) {
		Location location = new Location(newMartyr.getLocation());
		Node<Location> searchLocation = this.locationList.find(location);
		if (searchLocation == null) {
			location.insertMartyr(newMartyr);
			this.locationList.insert(location);
			return true;
		} else {
			searchLocation.getData().insertMartyr(newMartyr);

		}
		return true;
	}

	public int totalNumberOfMartyrByDate(String martyerName) {
		Node<Location> curr = locationList.getHead();
		int sum = 0;
		for (; curr != null && curr != locationList.getDummy(); curr = curr.getNext()) {
			sum += curr.getData().totalNumberOfMartyrByDate(martyerName);
		}
		return sum;
	}

	public int totalNumberOfMartyr() {
		int totalMartyr = 0;

		Node<Location> curr = locationList.getHead();
		for (; curr != locationList.getDummy() && curr != null; curr = curr.getNext()) {
			totalMartyr += curr.getData().totalNumberOfMartyr();
		}
		return totalMartyr;
	}

	public int totalNumberByGenderMale() {
		int totalMartyrMale = 0;

		Node<Location> curr = locationList.getHead();
		for (; curr != locationList.getDummy() && curr != null; curr = curr.getNext()) {
			totalMartyrMale += curr.getData().totalNumberByGenderMale();
		}
		return totalMartyrMale;
	}

	public int totalNumberByGenderFamale() {

		int totalMartyrFamale = 0;

		Node<Location> curr = locationList.getHead();
		for (; curr != locationList.getDummy() && curr != null; curr = curr.getNext()) {
			totalMartyrFamale += curr.getData().totalNumberByGenderFamale();

		}
		return totalMartyrFamale;
	}

	public int averageAge() {
		int sumAllAverage = 0;
		int length = 0;
		Node<Location> curr = locationList.getHead();
		for (; curr != locationList.getDummy() && curr != null; curr = curr.getNext()) {
			length++;
			sumAllAverage += curr.getData().averageAge();
		}

		if (length == 0) {
			return 0;
		}
		int average = sumAllAverage / length;
		return average;
	}

	public String getTheMaximumDate() {
		dateCountList.setHead(dateCountList.getDummy());
		Node<Location> currLocation = locationList.getHead();
		int result = 0;
		for (; currLocation != null && currLocation != locationList.getDummy(); currLocation = currLocation.getNext()) {
			Node<Martyr> currMartyr = currLocation.getData().getMartyrList().getHead();
			for (; currMartyr != null
					&& currMartyr != currLocation.getData().getMartyrList().getDummy(); currMartyr = currMartyr
							.getNext()) {
				Node<DateCount> prefDate = dateCountList.getDummy();
				Node<DateCount> currDate = dateCountList.getHead();
				boolean found = false;
				for (; currDate != dateCountList.getDummy()
						&& currDate != null; prefDate = currDate, currDate = currDate.getNext()) {
					result = currDate.getData().getDateofDead().compareTo(currMartyr.getData().getDate());
					if (result == 0) {
						currDate.getData().addCount();
						found = true;
						break;
					}

				}
				if (!found) {
					DateCount dateCount = new DateCount(currMartyr.getData().getDate());
					Node<DateCount> newDate = new Node<>(dateCount);
					if (currDate == dateCountList.getHead()) {
						// Inserting at the head of the list
						newDate.setNext(dateCountList.getHead());
						dateCountList.setHead(newDate);
					} else {
						// Inserting elsewhere
						newDate.setNext(currDate);
						prefDate.setNext(newDate);
					}
				}
			}

		}
		Node<DateCount> currDate = dateCountList.getHead();
		Node<DateCount> max = currDate;
		for (; currDate != null && currDate != dateCountList.getDummy(); currDate = currDate.getNext()) {
			if (currDate.getData().getCount() > max.getData().getCount()) {
				max = currDate;
			}

		}
		if (max != null) {
			return "The Maximum Date is: " + max.getData().getDateofDead() + " ,The Number is: "
					+ max.getData().getCount();
		}
		return "we dont have a data";

	}
}
