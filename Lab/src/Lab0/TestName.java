package Lab0;

import java.util.Scanner;

public class TestName {
	MyList<Name> names = new MyList<Name>(10);

	public void read() {
		Scanner scan = new Scanner("Amarica List");
		String line = scan.nextLine();
		String[] S = line.split(",");
		while (scan.hasNext()) {
			String name = S[0];
			String gender = S[1];
			int freq = Integer.parseInt(S[2]);
			Name NAME = new Name(name,gender,freq);
			names.add(NAME);
		}
	}

}