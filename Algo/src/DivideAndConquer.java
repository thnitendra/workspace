public class DivideAndConquer {

	interface Callbacks {
		// How to devide?
		boolean divide();
		
		// Just a recursion call
		void conquer();
		
		// Merge logic
		void combine();
	}
	
	interface Initialization {
		boolean condition();
		void exedcute();
	}
	
	interface Termination {
		boolean condition();
		void exedcute();
	}
}
