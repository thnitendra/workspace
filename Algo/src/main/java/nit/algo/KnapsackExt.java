package nit.algo;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * https://en.wikipedia.org/wiki/Knapsack_problem
 * https://dzone.com/articles/knapsack-problem
 *
 * This class provides a reusable, easy to use, object oriented way of using this algorithm
 * @see Knapsack for basic raw implementation of this logic
 */
public class KnapsackExt<T extends KnapsackExt.Item> {
    private T[] items;
    private Tracker[][] tracker;

    public KnapsackExt(T[] items) {
        //this.items = new Item[items.size()];
        this.items = items;
        int i = 0;
        for(T item : items) {
            this.items[i++] = item;
        }
    }

    public static interface Item {
        int weight();
        int value();
    }

    public class Tracker {
        int totalValue;
        Set<T> items = new HashSet<>();
        private void add(T item) {
            if(items.add(item)) {
                totalValue += item.value();
            }
        }
        private void addAll(Collection<T> items) {
            for (T item : items) {
                add(item);
            }
        }

        public Set<T> getItems() {
            return items;
        }
    }

    /**
     *
     * @param capacity Available weight capacity
     * @return Best value that can be picked
     */
    public Tracker knapsack(int capacity) {
        int N = items.length;
        //tracker = new Tracker[N + 1][W + 1];
        tracker = (Tracker[][]) Array.newInstance(Tracker.class, N + 1, capacity + 1);

        // if the knapsack's capacity is 0, then we cannnot pick any value items. So, set all columns at row 0 to be empty
        for (int col = 0; col <= capacity; col++) {
            tracker[0][col] = new Tracker();;
        }
        // if there are no items, then we have noting to pick. So, fill the first row with 0
        for (int row = 0; row <= N; row++) {
            tracker[row][0] = new Tracker();;
        }

        for (int i = 1; i <= N; i++){
            Item currItem = items[i -1];
            //Let's fill the values row by row
            for (int weight = 1; weight <= capacity; weight++){
                //Is the current items weight less than or equal to running weight
                if (currItem.weight() <= weight){
                    tracker[i][weight] = new Tracker();

                    int v1 = currItem.value();
                    int v2 = tracker[i-1][weight-currItem.weight()].totalValue;
                    int v3 = tracker[i-1][weight].totalValue;

                    //Given a weight, check if the value of the current item + value of the item that we could afford with the remaining weight
                    //is greater than the value without the current item itself
                    if (v1 + v2 > v3) {
                        tracker[i][weight].add(items[i - 1]);
                        tracker[i][weight].addAll(tracker[i-1][weight-currItem.weight()].items);
                    } else {
                        tracker[i][weight].addAll(tracker[i-1][weight].items);
                    }
                }
                else {
                    //If the current item's weight is more than the running weight, just carry forward the value without the current item
                    tracker[i][weight] = tracker[i - 1][weight];
                }
            }
        }
        return tracker[N][capacity];
    }

    public void printAnalysis() {
        //Printing the matrix
        System.out.format("%5d%5d%5d%5d%5d%5d%5d%5d%5d%5d%5d", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println();
        System.out.println("----------------------------------------------------------");
        for (Tracker[] rows : tracker) {
            for (Tracker col : rows) {
                System.out.format("%5d", col.totalValue);
            }
            System.out.println();
        }
    }
}