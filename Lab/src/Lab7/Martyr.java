package Lab7;

public class Martyr implements Comparable<Martyr> {
	private String name;
	private String date;
	private int age;
	private String location;
	private String district;
	private String gender;

	public Martyr() {

	}

	public Martyr(String name, String date, int age, String location, String district, String gender) {

		this.name = name;
		this.date = date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
			return "Martyr [Name=" + name + ", Date=" + date + ", Age= Un_Noun" + ", location=" + location
					+ ", District=" + district + ", Gender=" + gender + "]\n";
		}
		return "Martyr [Name=" + name + ", Date=" + date + ", Age=" + age + ", location=" + location + ", District="
				+ district + ", Gender=" + gender + "]\n";
	}

	@Override
	public int compareTo(Martyr o) {
		if(this.name.equalsIgnoreCase(o.getName()) == true) {
			return -200;
		}
		return this.age - o.age;
	}

}
