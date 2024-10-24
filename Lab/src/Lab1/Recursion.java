package Lab1;

import java.util.Arrays;

public class Recursion {
	public static void main(String[] args) {
		printN(10);
		System.out.println(reverse("I like Java"));
		System.out.println(count("hello", 'L'));
		System.out.println(SumNumberDigits(555));
		System.out.println(reverse("I like Java and Java like me"));
		int[] array = { 5, 10, 15, 20, 21, 23, 25, 27, 30, 35 };

		System.out.println(binarySearch(array, 10));
	}

	public static void printN(int n) {
		printN(0, n);
	}

	private static void printN(int i, int n) {
		if (i <= n) {
			System.out.println(i + " ");
			printN(i + 1, n);
		}
	}

	public static String reverse(String s) {
		if (s == null || s.lastIndexOf(" ") == -1) {
			return s;
		}
		int i = s.lastIndexOf(" ");
		String Last = s.substring(i + 1);
		String LastReverse = reverse(Last, 0, Last.length() - 1);
		return LastReverse + " " + reverse(s.substring(0, i));
	}

	private static String reverse(String s, int i, int j) {
		if (s == null || s.length() <= 1) {
			return s;
		} else if (i >= j) {
			return s;
		}
		char[] chars = s.toCharArray();
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
		return reverse(String.valueOf(chars), i + 1, j - 1);
	}

	public static int count(String s, char c) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		return count(s.toUpperCase(), c, 0);
	}

	private static int count(String s, char c, int i) {
		if (i >= s.length()) {
			return 0;
		} else if (s.charAt(i) == c) {
			return 1 + count(s, c, i + 1);
		}
		return count(s, c, i + 1);
	}

	public static int SumNumberDigits(int n) {
		if (10 > n) {
			return n;
		}
		int reminder = n % 10;
		reminder += SumNumberDigits(n / 10);
		if (reminder > 9) {
			n = reminder;
			reminder = n % 10;
			return reminder + SumNumberDigits(n / 10);
		}
		return reminder;
	}

	public static int binarySearch(int[] array, int Num) {
		Arrays.sort(array);
		return binarySearch(array, Num, 0, array.length);

	}

	private static int binarySearch(int[] array, int Num, int First, int Final) {
		try {

			if (First <= Final) {
				int Mid = First + (Final - First) / 2;

				if (array[Mid] == Num) {
					return Mid;
				} else if (array[Mid] < Num) {

					return binarySearch(array, Num, Mid + 1, Final);
				} else {

					return binarySearch(array, Num, First, Mid - 1);
				}
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(-1);
		}
		return -1;
	}

}
