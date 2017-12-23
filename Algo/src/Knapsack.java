/**
 * https://en.wikipedia.org/wiki/Knapsack_problem
 * https://dzone.com/articles/knapsack-problem
 */
public class Knapsack {
    // Items Value
    int val[];// = {10, 40, 30, 50};
    // Items Weight
    int wt[];// = {5, 4, 6, 3};

    private int[][] V;

    public Knapsack(int count) {
        val = new int[count];
        wt = new int[count];
    }

    private int _index;
    public void addItem(int wt, int val) {
        this.wt[_index] = wt;
        this.val[_index] = val;
        ++_index;
    }

    /**
     *
     * @param W Available weight capacity
     * @return Best value that can be picked
     */
    public int knapsack(int W) {
        int N = wt.length; // Get the total number of items. Could be wt.length or val.length. Doesn't matter
        V = new int[N + 1][W + 1]; //Create a matrix. Items are in rows and weight at in columns +1 on each side

        //What if the knapsack's capacity is 0 - Set all columns at row 0 to be 0
        for (int col = 0; col <= W; col++) {
            V[0][col] = 0;
        }
        //What if there are no items at home.  Fill the first row with 0
        for (int row = 0; row <= N; row++) {
            V[row][0] = 0;
        }
        for (int item=1;item<=N;item++){
            //Let's fill the values row by row
            for (int weight=1;weight<=W;weight++){
                //Is the current items weight less than or equal to running weight
                if (wt[item-1]<=weight){
                    //Given a weight, check if the value of the current item + value of the item that we could afford with the remaining weight
                    //is greater than the value without the current item itself
                    V[item][weight]=Math.max (val[item-1]+V[item-1][weight-wt[item-1]], V[item-1][weight]);
                }
                else {
                    //If the current item's weight is more than the running weight, just carry forward the value without the current item
                    V[item][weight]=V[item-1][weight];
                }
            }
        }
        return V[N][W];
    }

    public void printAnalysis() {
        //Printing the matrix
        System.out.format("%5d%5d%5d%5d%5d%5d%5d%5d%5d%5d%5d", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println();
        System.out.println("----------------------------------------------------------");
        for (int[] rows : V) {
            for (int col : rows) {
                System.out.format("%5d", col);
            }
            System.out.println();
        }
    }
}