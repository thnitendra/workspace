import java.util.*;

/**
 You run a small theater and each month, you have patrons mail in requests for pre-sale tickets. You need to process

 these ticket requests and either tell them where their party will sit or explain to the patron why you can&#39;t complete their

 order.


 You have a few rules that you need to follow when you fill the orders

 1. Fill as many orders as possible

 2. Put parties as close to the front as possible.

 3. If there are not enough seats available in the theater to handle a party, tell them &quot;Sorry, we can&#39;t handle your

 party.&quot;

 4. Each party must sit in a single row in a single section. If they won&#39;t fit, tell them &quot;Call to split party&quot;.



 Your program must parse a theater layout and a list of ticket requests and produce a list of tickets or explanations in the

 same order as the requests.



 The theater layout is made up of 1 or more rows. Each row is made up of 1 or more sections separated by a space.

 After the theater layout, there is one empty line, followed by 1 or more theater requests. The theater request is made up

 of a name followed by a space and the number of requested tickets.



 Sample input:

 6 6

 3 5 5 3

 4 6 6 4

 2 8 8 2

 6 6



 Smith 2

 Jones 5

 Davis 6

 Wilson 100

 Johnson 3

 Williams 4

 Brown 8

 Miller 12



 Your program must produce results to standard output in the same order as the requests, with the name of the person

 who requested the ticket and either the row and section of the ticket or the explanations &quot;Sorry, we can&#39;t handle your

 party&quot; or &quot;Call to split party.&quot;





 Sample output:



 Smith Row 1 Section 1

 Jones Row 2 Section 2

 Davis Row 1 Section 2

 Wilson Sorry, we can&#39;t handle your party.

 Johnson Row 2 Section 1

 Williams Row 1 Section 1

 Brown Row 4 Section 2

 Miller Call to split party.
 */

/*
https://en.wikipedia.org/wiki/Knapsack_problem
https://en.wikipedia.org/wiki/Subset_sum_problem
 */
public class TheatreSeating {

    private int[][] layout;
    private List<Order> orders = new LinkedList<>();

    private Map<Pos, LinkedHashSet<Order>> _byPos = new LinkedHashMap<>();
    private Map<Order, LinkedHashSet<Pos>> _byOrd = new LinkedHashMap<>();


    public TheatreSeating(int[][] layout) {
        this.layout = layout;
    }
    private static class Order implements Comparable<Order>, KnapsackExt.Item {
        private String name;
        private int count;
        private Pos pos;

        private Order(String name, int count) {
            this.name = name;
            this.count = count;
        }

        @Override
        public String toString() {
            return name + ":" + count;
        }

        @Override
        public int compareTo(Order o) {
            return this.count - o.count;
        }

        @Override
        public int weight() {
            return count;
        }

        @Override
        public int value() {
            return count;
        }
    }
    private class Pos {
        private int row, column;
        Pos(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pos pos = (Pos) o;

            if (row != pos.row) return false;
            return column == pos.column;

        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + column;
            return result;
        }

        @Override
        public String toString() {
            return "Row " + row + ", Section " + column;
        }
    }

    public void addOrder(String name, int count) {
        orders.add(new Order(name, count));
    }

    public void allotSeats() {
        this.layout = layout;
        checkPossibilities();

    }

    private void checkPossibilities() {
        // Step1: Find all possible allotments and store it under _byPos / _byOrd
        for (int i = 0; i < layout.length; ++i) {
            for (int j = 0; j < layout[i].length; ++j) {
                Pos pos = new Pos(i, j);
                for(Order order : orders) {
                    if (order.count <= layout[i][j]) {
                        _byPos.putIfAbsent(pos, new LinkedHashSet<>());
                        _byPos.get(pos).add(order);
                        _byOrd.putIfAbsent(order, new LinkedHashSet<>());
                        _byOrd.get(order).add(pos);
                    }
                }
            }
        }

        // Step2: Find the best fit for each position using knapsack logic
        Iterator<Map.Entry<Pos, LinkedHashSet<Order>>> itrByPos = _byPos.entrySet().iterator();
        while (itrByPos.hasNext()) {
            Map.Entry<Pos, LinkedHashSet<Order>> kv =  itrByPos.next();

            // ignore if an order has been processed during previous iterations
            Iterator<Order> itr2 = kv.getValue().iterator();
            while (itr2.hasNext()) {
                if(itr2.next().pos != null) {
                    itr2.remove();
                }
            }

            // apply knapsack on the possible orders for this position and find best fit for the current section capacity
            int capacity = layout[kv.getKey().row][kv.getKey().column];
            Order[] ordersArr = new Order[kv.getValue().size()];
            KnapsackExt k = new KnapsackExt(kv.getValue().toArray(ordersArr));
            KnapsackExt.Tracker tracker = k.knapsack(capacity);
            for(Order ord : (Set<Order>) tracker.items) {
                ord.pos = kv.getKey();
                _byOrd.remove(ord);
            }

            itrByPos.remove();
        }
    }

    public void printLayout() {
        for(int i = 0; i < layout.length; ++i) {
            Util.print(layout[i]);
        }
    }

    public void printOrders() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void printAllotments() {
        for (Order order : orders) {
            System.out.println(order + " -> " + order.pos);
        }
    }
}
