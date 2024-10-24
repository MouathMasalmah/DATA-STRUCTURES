package application;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateCount implements Comparable<DateCount> {
	private String dateOfDead;
	private int count = 1;

	public DateCount(String dateofDead) {
		this.dateOfDead = dateofDead;
	}

	public String getDateofDead() {
		return dateOfDead;
	}

	public void setDateofDead(String dateofDead) {
		this.dateOfDead = dateofDead;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void addCount() {
		count++;
	}

	public void deleteCount() {
		count--;
	}

	@Override
	public String toString() {
		return "DateCount [dateofDead=" + dateOfDead + ", count=" + count + "]\n";
	}

	@Override
	public int compareTo(DateCount o) {
		if (this.dateOfDead.equalsIgnoreCase("N/A")) {
			return 0;
		} else {
			try {
				String[] Split1 = this.dateOfDead.split("/");
				String[] Split2 = o.getDateofDead().split("/");
				int day1 = Integer.parseInt(Split1[1]);
				int month1 = Integer.parseInt(Split1[0]) - 1;
				int year1 = Integer.parseInt(Split1[2]);

				int day2 = Integer.parseInt(Split2[1]);
				int month2 = Integer.parseInt(Split2[0]) - 1;
				int year2 = Integer.parseInt(Split2[2]);

				Calendar dateObject1 = new GregorianCalendar(year1, month1, day1);
				Calendar dateObject2 = new GregorianCalendar(year2, month2, day2);

				if (dateObject1.equals(dateObject2)) {
					return 0;

				} else {
					return -1;
				}
			} catch (Exception e) {
				System.out.println("Error");
			}
		}
		return -1;
	}

}
