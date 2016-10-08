package nit.rakuten.codility;

//you can also use imports, for example:
//import java.util.*;

//you can write to stdout for debugging purposes, e.g.
//System.out.println("this is a debug message");

class GF {
	
	final int divisor = 1000000007;	
	
	public int solution(int A, int B, int N) {		
		assert A >= 0 && A <= 2147483647
	        && B >= 0 && B <= 2147483647
	        && N >= 0 && N <= 1000000000;
	    
		return _solution1(A, B, N);
		//return _solution2(A, B, N);
		
	}
  
	public int _solution1(int A, int B, int N) {		
		int i = GF(A, B, N);
		return i % divisor;
	}
  
	private int GF(int A, int B, int N) {
	    return N == 0 ? A :
			    N == 1 ? B :
			    N > 1 ? GF(A, B, N - 1) + GF(A, B, N - 2) :
			-1;		
	}  

	public int _solution2(int A, int B, int N) {
		int i = N == 0 ? A :
			    N == 1 ? B :
			    N > 1 ? ( GF(A, B, N - 1) % divisor ) 
			    		+ ( GF(A, B, N - 2) % divisor ):
			    	-1;
			    
	    return i % divisor;
	}
}