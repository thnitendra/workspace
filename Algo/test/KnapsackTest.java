
import org.junit.Test;

import java.util.LinkedHashSet;

/**
 * Created by nitendra.thakur on 2017/01/08.
 */
public class KnapsackTest {
    @Test
    public void getBestValue() {
        Knapsack k = new Knapsack(4);
        k.addItem(5, 10);
        k.addItem(4, 40);
        k.addItem(6, 30);
        k.addItem(3, 50);

        int w = 10;
        int bestValue = k.knapsack(w);
        System.out.println("The best Value that can be picked under weight " + w + " is " + bestValue);
        k.printAnalysis();
    }

    class Item implements  KnapsackExt.Item {
        private int wt, val;
        Item(int wt, int val) {
            this.wt = wt;
            this.val = val;
        }
        @Override
        public int weight() {
            return wt;
        }

        @Override
        public int value() {
            return val;
        }

        @Override
        public String toString() {
            return "wt:"+wt+",val:"+val;
        }
    }

    @Test
    public void getBestItems() {
        LinkedHashSet<KnapsackExt.Item> items = new LinkedHashSet<>(4);
        items.add(new Item(5, 10));
        items.add(new Item(4, 40));
        items.add(new Item(6, 30));
        items.add(new Item(3, 50));

        Item[] itemArr = new Item[4];
        KnapsackExt k = new KnapsackExt(items.toArray(itemArr));

        int w = 10;
        KnapsackExt.Tracker bestValue = k.knapsack(w);
        System.out.println("The best Value that can be picked under weight " + w + " is " + bestValue.totalValue);
        System.out.println("Items picked are " + bestValue.items);
        k.printAnalysis();
    }
}
