package nit.algo;

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
		for(int i = 0; i < in.length; ++i) {
			System.out.print(in[i] + " ");
		}
		System.out.println();
	}

	public static void print(int[] in) {
		for(int i = 0; i < in.length; ++i) {
			System.out.print(in[i] + "   ");
		}
		System.out.println();
	}

	public static <T> void print(int s, int e, T... in) {
		for(int i = s; i <= e; ++i) {
			System.out.print(in[i] + " ");
		}
		System.out.println();
	}

	public static void printArr(int s, int e, int[] in) {
		for(int i = s; i <= e; ++i) {
			System.out.print(in[i] + " ");
		}
		System.out.println();
	}

	public static <T> void print(int s, int m, int e, T... in) {
		for(int i = s; i <= m; ++i) {
			System.out.print(in[i] + " ");
		}
		System.out.print("   ");
		for(int i = m + 1; i <= e; ++i) {
			System.out.print(in[i] + " ");
		}
		System.out.println();
	}
}
