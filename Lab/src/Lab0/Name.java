package Lab0;

public class Name implements Comparable<Name> {
	String NAME;
	String Gender;
	int freq;

	public Name() {

	}

	public Name(String name, String gender, int freq) {
		NAME = name;
		Gender = gender;
		this.freq = freq;
	}

	public String getName() {
		return NAME;
	}

	public void setName(String name) {
		NAME = name;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	@Override
	public String toString() {
		return "Name [Name=" + NAME + ", Gender=" + Gender + ", freq=" + freq + "]";
	}

	@Override
	public int compareTo(Name o) {

		if (this.getName().equals(o.getName()) && this.getGender().equals(o.getGender()) == true) {
			return 0;
		}
		return -1;
	}

}