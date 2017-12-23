import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Given n dice each with m faces, numbered from 1 to m, find the number of ways to get sum X.
 * X is the summation of values on each face when all the dice are thrown.
 *
 * Note: Player will make one move only. What are possible ways he can get X
 * @link http://www.geeksforgeeks.org/dice-throw-problem/
 *
 * {@link DiceOne#roll(int, int, int)} solves this problem using recursion
 * {@link DiceOne#findWays(int, int, int)} solves this problem using Dynamic Programming
 *
 * As explained in the hyperlink, solving this using recursion is expensive because same set of inputs will occur multiple times.
 * The {@link Check#print()} can be used to see how duplicacy occurs for a given input when this problem is solved using recursion.
 */
public class DiceOne {
	public static void main(String[] args) {

		// Using recursion
		System.out.println(roll(6, 5, 8));
		//Check.print();

		// Using Dynamic Programming
		System.out.println(findWays(6, 5, 8));
	}

    private static long roll(int diceFace, int diceCount, int sum) {
		assert diceCount <= sum;
		Check.update(diceCount, sum);
		if (diceCount == 1) {
			return 1;
		} else {
			if(sum == diceCount) {
				//return 1 + roll(diceFace, diceCount - 1, sum - 1);
				return 1;
			} else {
				return roll(diceFace, diceCount - 1, sum - 1) + roll(diceFace, diceCount, sum - 1);
			}
		}
	}

	static class Check {
		static Map<Integer, Map<Integer, Integer>> checker = new LinkedHashMap<>();
		public static void update(int a, int b) {
			Map<Integer, Integer> tempA = checker.get(a);
			if (tempA != null) {
				Integer tempB = tempA.get(b);
				tempB = tempB != null ? Integer.valueOf(tempB.intValue() + 1) : 1;
				tempA.put(b, tempB);
			} else {
				tempA = new LinkedHashMap<>();
				tempA.put(b, 1);
				checker.put(a, tempA);
			}
		}
		public static void print() {
			for (Integer i: checker.keySet()) {
				Map<Integer, Integer> kv = checker.get(i);
				for(Integer j : kv.keySet()) {
					System.out.println(i + "  " + j + " = " + kv.get(j));
				}
			}
		}
	}

	// The main function that returns number of ways to get sum 'x'
	// with 'n' dice and 'm' with m faces.
	static int findWays(int m, int n, int x)
	{
		// Create a table to store results of subproblems.  One extra
		// row and column are used for simpilicity (Number of dice
		// is directly used as row index and sum is directly used
		// as column index).  The entries in 0th row and 0th column
		// are never used.
		int table[][] = new int[n + 1][x + 1];

		// Table entries for only one dice
		for (int j = 1; j <= m && j <= x; j++)
			table[1][j] = 1;

		// Fill rest of the entries in table using recursive relation
		// i: number of dice, j: sum
		for (int i = 2; i <= n; i++)
			for (int j = 1; j <= x; j++)
				for (int k = 1; k <= m && k < j; k++)
					table[i][j] += table[i-1][j-k];

		/* Uncomment these lines to see content of table
		for (int i = 0; i <= n; i++)
		{
		  for (int j = 0; j <= x; j++)
			cout << table[i][j] << " ";
		  cout << endl;
		} */
			return table[n][x];
		}

}
 
 

