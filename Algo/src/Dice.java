import java.math.BigInteger;

/**
 * There is a 6-faced dice and every time you roll it, you move forward the same number of spaces that you rolled.
 * If the finishing point is “n” spaces away from the starting point, how many possible ways are there to arrive exactly at the finishing point?
 *
 * {@link Dice#roll(int, int)} present a generic solution for any given dice type and bock to move.
 *
 * if we assume that max dice face value is >= required blocks to move; the solution becomes very simple
 * {@link Dice#roll(int)} present this simple solution
 */
public class Dice {
	public static void main(String[] args) {
		roll(8, 6);
	}

	/**
	 * result[i] is the number of possible dice moves to reach at block n.
	 * result[i] = sum of last P results i.e, result[i-1], result[i-2] ... result[i-P],
	 * 			where P is the max available value on dice, usually 6.
	 *
	 * 	This logic works because
	 * 	in every move of result[i-1] we can add a move of dice face value ONE and we reach position n
	 * 	in every move of result[i-2] we can add a move of dice face value TWO and we still reach position n
	 * 	and so forth and so on
	 *
	 *
	 * @param blocks number of blocks to reach
	 * @param diceFace max dice face value, usually 6
	 */
	private static void roll(int blocks, int diceFace) {
		// At every index, the result for corresponding block is stored. The final result is based on previous results
		BigInteger[] result = new BigInteger[blocks + 1];

		// zero is a hypothetical block; only used to fill the first block result correctly
		result[0] = new BigInteger("1");

		System.out.println("Using a dice with max face value of " + diceFace + ", number of possible dice moves to reach N blocks: " );
		for (int i = 1; i <= blocks; i++) {
			result[i] = new BigInteger("0");
			for (int j = 1; j <= diceFace && i - j >= 0; j++) {
				result[i] = result[i].add(result[i - j]);
			}
			System.out.println(i + " blocks = " + result[i]);
		}
	}

	/**
	 * assuming the dice has a max face value of at least number of blocks to move.
	 * @link https://en.wikipedia.org/wiki/Composition_(combinatorics)
	 *
	 */
	private static long roll(int blocks) {
		if(blocks == 1) {
			return 1;
		} else {
			//This recusrion is a shorthand for writtig a loop to get (2)^n-1
			return (long) (2 * roll(blocks - 1));
		}
	}
}
 
 

