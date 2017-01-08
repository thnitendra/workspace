package nit.algo;

import nit.algo.MergeSort;
import org.junit.Test;

public class MergeSortTest {
	MergeSort<Integer> ms = new MergeSort<>();

	@Test
	public void sort() {
		Integer[] in = {5, 7, 2, 12, 9, 3, 4, 6, 10, 1};
		Integer[] out = ms.sort(in);
		System.out.println(out);
	}
}
