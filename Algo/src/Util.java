public final class Util {

	private Util() {
		throw new AssertionError("Operation not allowed.");
	}

	public static int mid(int indexFrom, int indexTo) {
		int i = indexTo - indexFrom;
		int j = i / 2;
		int mid = indexFrom + j;
		return mid;
	}

	public static <T> void print(T[] in) {
		for (int i = 0; i < in.length; ++i) {
			System.out.print(in[i] + " ");
		}
		System.out.println();
	}

	public static void print(int[] in) {
		for (int i = 0; i < in.length; ++i) {
			System.out.print(in[i] + "   ");
		}
		System.out.println();
	}

	public static <T> void print(int s, int e, T... in) {
		for (int i = s; i <= e; ++i) {
			System.out.print(in[i] + " ");
		}
		System.out.println();
	}

	public static void printArr(int s, int e, int[] in) {
		for (int i = s; i <= e; ++i) {
			System.out.print(in[i] + " ");
		}
		System.out.println();
	}

	public static <T> void print(int s, int m, int e, T... in) {
		for (int i = s; i <= m; ++i) {
			System.out.print(in[i] + " ");
		}
		System.out.print("   ");
		for (int i = m + 1; i <= e; ++i) {
			System.out.print(in[i] + " ");
		}
		System.out.println();
	}

	public static <T> void swap(T[] in, int i, int j) {
		T temp = in[i];
		in[i] = in[j];
		in[j] = temp;
	}

	/**
	 * swap without using temp variable
	 * 3  5
	 * 8  5
	 * 8  3
	 * 3  5
	 */
	public static <T extends Number> void swap(T[] in, int i, int j) {
		in[i] = (T) Integer.valueOf(in[i].intValue() + in[j].intValue());
		in[j] = (T) Integer.valueOf(in[i].intValue() - in[j].intValue());
		in[i] = (T) Integer.valueOf(in[i].intValue() - in[j].intValue());
	}

	public static Character[] box(char[] in) {
		Character[] out = new Character[in.length];
		int i = 0;
		for (char c : in) {
			out[i++] = new Character(c);
		}
		return out;
	}

	public static int factorial(int in) {
		int fact = 1;
		for(int i = 1; i <= in; ++i) {
			fact *= i;
		}
		return fact;
	}
}