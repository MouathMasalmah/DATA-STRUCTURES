package application;

import java.util.Comparator;

public class Martyr implements Comparable<Martyr> {
	private String name;
	private int age;
	private String location;
	private String district;
	private String gender;

	public Martyr() {

	}

	public Martyr(String name, int age, String location, String district, String gender) {

		this.name = name;
		this.age = age;
		this.location = location;
		this.district = district;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		if (age == -1) {
			return "Name=" + name +", Age= Un_Noun" + ", location=" + location
					+ ", District=" + district + ", Gender=" + gender + "\n";
		}
		return "Name=" + name +", Age=" + age + ", location=" + location + ", District="
				+ district + ", Gender=" + gender + "\n";
	}

	@Override
	public int compareTo(Martyr o) {
		int compareDistrict =this.district.compareToIgnoreCase(o.getDistrict());
		if(compareDistrict != 0) {
			return compareDistrict;
		}
		return this.name.compareToIgnoreCase(o.getName());
	}

}
