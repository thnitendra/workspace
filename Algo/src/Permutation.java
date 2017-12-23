import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 * https://betterexplained.com/articles/easy-permutations-and-combinations/
 *
 * Created by nitendra.thakur on 2017/06/18.
 */
public class Permutation<T> {
    T[] input;
    int count;

    public Permutation(T[] in) {
        this.input = in;
    }

    public static void main(String[] args) {
        Permutation perm = new Permutation(Util.box("abcd".toCharArray()));
        perm.permute();
    }

    public void permute() {
        permute(input, 0, input.length - 1);
        assert check(input.length);
        System.out.println("Total = " + count);
    }

    private void permute(T[] in, int l, int r)
    {
        if (l == r) {
            Util.print(in);
            ++count;
        } else {
            for (int i = l; i <= r; i++)
            {
                /* If we do not make a copy, we will have to revert this swap before proceeding to next index. */
                in = Arrays.copyOf(in, in.length);
                Util.swap(in,l,i);

                permute(in, l+1, r);

                /* If we made copy; then this revert is not required */
                //Util.swap(in,l,i);
            }
        }
    }

    private boolean check(int pickCount) {
        int inFactorial = Util.factorial(input.length);
        int inMinusPickFactorial = Util.factorial(input.length - pickCount);
        return count == (inFactorial / inMinusPickFactorial);
    }
}
