package Lab0;

public class HomeWork1 {
	public static void main(String[] args) {
		int n = 3; // Number of disks (you can change it to 900 if you want)
		solve(n, "A", "B", "C");
	}

	public static void solve(int n, String A, String B, String C) {
		if (n > 0) {
			solve(n - 1, A, C, B);
			System.out.println(A + " to " + B);
			solve(n - 1, C, B, A);
		}
	}
}
