package nit.algo;

public class QuickSort {
	private int[] arr = {9, 3, 10, 12, 4, 2, 8, 6, 5};
	
	public static void main(String[] args) {
		QuickSort qs = new QuickSort();
		qs.print();
		//System.out.println(qs.pivot(3, 8));
		qs.sort();
		qs.print();
	}
	
	private void sort() {
		sort(0, arr.length - 1);
	}
	private void sort(int s, int e) {
		if(s >= e || s < 0 || e >= arr.length) {
			return;
		} else {
			int p = pivot(s, e);
			sort(s, p - 1);
			sort(p + 1, e);
		}
		
	}
	
	// pivot value is at index e. This method will move this to the right index and return the new index.
	// pivotIndex is such that (s to pivot - 1) < pivot < (pivot + 1 to e)
	private int pivot(int s, int e) {
		assert s <= e;
		int i = s, j = i - 1;
		for(; i < e; ++i) {
			if(arr[i] < arr[e]) {
				++j;
				swap(j, i);
			}
		}		
		if(++j < i) {
			swap(j, i);
		}
		
		return j;
	}
	
	private void swap(int i, int j) {
		if(i == j) {
			return;
		}
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public <T> void print(int s, int e) {
		for(int i = s; i <= e; ++i) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public <T> void print() {
		print(0, arr.length - 1);
	}
}
