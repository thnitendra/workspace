package nit.algo;

public class MaxSubArray {
	
	private int[] arr = //{-1, 2, 4, -6, 8};
		  {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
	
	public static void main(String[] args) {		
		MaxSubArray msa = new MaxSubArray();
		Util.print(msa.arr);
		System.out.println(msa.findMaxSubarry());		
	}
	
	private class SubArrayInfo implements Comparable<SubArrayInfo>{
		int start, end, sum;
		
		private SubArrayInfo(int s, int e, int sum) {
			assert s <= e && e < arr.length;
			this.start = s;
			this.end = e;
			this.sum = sum;
		}
		public SubArrayInfo(int s, int e) {
			assert s <= e && e < arr.length;
			this.start = s;
			this.end = e;
			do {
				this.sum += arr[s];
			} while (++s <= e);
		}
		
		private SubArrayInfo maxHead() {
			int s = start, sum = arr[s];		
			SubArrayInfo res = new SubArrayInfo(s, s, sum);			
			while(++s <= end) {
				if( (sum += arr[s]) > res.sum) {
					res.end = s;
					res.sum = sum;
				}
			};			
			return res;
		}
		
		private SubArrayInfo maxTail() {
			int e = end, sum = arr[e];		
			SubArrayInfo res = new SubArrayInfo(e, e, sum);			
			while(--e >= start) {
				if( (sum += arr[e]) > res.sum) {
					res.start = e;
					res.sum = sum;
				}
			};			
			return res;
		}

		@Override
		public String toString() {
			return "SubArray [sum=" + sum + ", start=" + start + ", end=" + end + "]";
		}
		
		@Override
		public int compareTo(SubArrayInfo o) {
			return this.sum - o.sum;
		}
	}
	
	private SubArrayInfo findMaxSubarry() {
		int start = 0;
		int end = arr.length - 1;
		int mid = Util.mid(start, end);
		return maxSubArry(start, mid, end);
	}
	
	private SubArrayInfo maxSubArry(int start, int mid, int end) {
		assert start <= mid && mid <= end && end < arr.length ;
		
		if(start == end) {
			return new SubArrayInfo(start, start);
		} else {
			SubArrayInfo left = maxSubArry(start, Util.mid(start, mid), mid);
			SubArrayInfo right = maxSubArry(mid + 1, Util.mid(mid + 1, end), end);
			SubArrayInfo cross = maxCross(start, mid, end);
			
			SubArrayInfo res = (left.compareTo(right) > 1 && left.compareTo(cross) > 1) ? left :
				   (right.compareTo(left) > 1 && right.compareTo(cross) > 1) ? right : cross;
			
			//System.out.println(">>>>> " + start + ", " + mid + ", " + end);
			//System.out.println("max>> " + res);
			return res;			
		}		
	}
	
	private SubArrayInfo maxCross(int s, int m, int e) {		
		SubArrayInfo left = new SubArrayInfo(s, m);
		SubArrayInfo maxLeft = left.maxTail();
		
		SubArrayInfo right = new SubArrayInfo(m + 1, e);		
		SubArrayInfo maxRight = right.maxHead();		
				
		SubArrayInfo maxCross = new SubArrayInfo(maxLeft.start, maxRight.end, maxLeft.sum + maxRight.sum);		
		
		return (maxLeft.compareTo(maxCross) > 1) ? maxLeft :
				(maxRight.compareTo(maxCross) > 1) ? maxRight : maxCross;
		
	}	
}
