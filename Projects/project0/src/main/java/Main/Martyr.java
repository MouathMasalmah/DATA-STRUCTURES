package Main;

import java.text.CollationElementIterator;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Martyr implements Comparable<Martyr> {
	private String Name, EventLocation, DateDeath, Gender;
	private int Age;

	public Martyr() {

	}

	public Martyr(String name, int age, String eventLocation, String dateDeath, String gender) {
		Name = name;
		EventLocation = eventLocation;
		DateDeath = dateDeath;
		Gender = gender;
		Age = age;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEventLocation() {
		return EventLocation;
	}

	public void setEventLocation(String eventLocation) {
		EventLocation = eventLocation;
	}

	public String getDateDeath() {
		return DateDeath;
	}

	public void setDateDeath(String dateDeath) {
		DateDeath = dateDeath;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	@Override
	public String toString() {
		return "Martyr [Name=" + Name + ", EventLocation=" + EventLocation + ", DateDeath=" + DateDeath + ", Gender="
				+ Gender + ", Age=" + Age + "]";
	}

	@Override
	public int compareTo(Martyr o) {

		if (this.getName().equals(o.getName()) == true) {
			return 0;
		}
		return -1;
	}
	public int compareTo(String DateDeath) {
		String []Split1 = this.getDateDeath().split("/");
		String []Split2 = DateDeath.split("/");
		int day1 = Integer.parseInt(Split1[0]);
		int month1 = Integer.parseInt(Split1[1]) - 1;
		int year1 = Integer.parseInt(Split1[2]);

		int day2 = Integer.parseInt(Split2[0]);
		int month2 = Integer.parseInt(Split2[1]) - 1;
		int year2 = Integer.parseInt(Split2[2]);

		Calendar dateObject1 = new GregorianCalendar(year1, month1, day1);
		Calendar dateObject2 = new GregorianCalendar(year2, month2, day2);



		if (dateObject1.equals(dateObject2)) {
			return 0;
		}
		return -1;
	}

	public int compareTo(char Gender) {

		if (this.Gender.charAt(0) == Gender) {
			return 0;
		}
		return -1;
	}
}
