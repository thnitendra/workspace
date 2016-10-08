package nit.algo;

import java.lang.reflect.Array;

public class MergeSort<T extends Comparable<T>> {

	public static void main(String[] args) {
		Integer[] in = {5, 7, 2, 12, 9, 3, 4, 6, 10, 1};
		MergeSort<Integer> ms = new MergeSort<>();
		Integer[] out = ms.sort(in);
		System.out.println(out);
	}
	
	public T[] sort(T... in) {
		assert in != null;
		if(in.length <= 1) 
			return in;
		
		Util.print(0, in.length-1, in);
		sort(in, 0, in.length - 1);
		
		//T[] out = (T[]) Array.newInstance(in[0].getClass(), in.length);
		T[] out = in;
		return out;
	}
	
	private void sort(T[] in, int i, int j) {
		assert i <= j;
		if(i == j) {
			return;
		} else if(j - i == 1) {
			merge(in, i, i, j);
		} else {
			int mid = Util.mid(i, j);			
			
			Util.print(i, mid, j, in);			
			
			sort(in, i, mid);			
			sort(in, mid + 1, j);
			merge(in, i, mid, j);
			
			Util.print(i, j, in);			
		}
	}
		
	/**
	 * subarray1: indexFrom to mid
	 * subarray2: mid+1 to indexTo
	 * The above two subarrays must be sorted
	 * 
	 * @param in
	 * @param s start index
	 * @param mid mid index
	 * @param e end index
	 */
	private void merge(T[] in, int start, int mid, int end) {
		assert start<= in.length && start <= mid && mid <= end;
		//System.out.println("merging " + start + " " + mid + " " + end);
		int s = start, e = end, m = mid + 1;		
		T[] res = (T[]) Array.newInstance(in[0].getClass(), e - s + 1);
		int i = 0;
		
		boolean subarr1HasNext = s <= mid;
		boolean subarr2HasNext = m <= e;		
		while(subarr1HasNext || subarr2HasNext) {
			subarr1HasNext = s <= mid && s < in.length;
			subarr2HasNext = m <= e && m < in.length;
			int chk = 0;
			if (subarr1HasNext && subarr2HasNext) {
				chk = in[s].compareTo(in[m]);
			}
			if ((subarr1HasNext) && (!subarr2HasNext || chk < 0)) {			
				res[i++] = in[s++];
			} else if((subarr2HasNext) && (!subarr1HasNext || chk >= 0)) {
				res[i++] = in[m++];
			}			
		}
		
		i = 0;
		while(i < res.length) {
			in[start++] = res[i++];
		}
	}
	
}
