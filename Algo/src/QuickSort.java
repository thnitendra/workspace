public class QuickSort<T extends Comparable<T>> {
	private T[] arr;

	public T[] sort(T[] arr) {
		this.arr = arr;
		sort(0, arr.length - 1);
		return arr;
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
			//if(arr[i] < arr[e]) {
			if(arr[i] != null && arr[i].compareTo(arr[e]) < 0) {
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
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
}
