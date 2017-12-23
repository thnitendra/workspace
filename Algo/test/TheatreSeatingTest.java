
import org.junit.Test;

public class TheatreSeatingTest {

    @Test
    public <T> void prntMatrix() {
        int[][] layout = {
                        { 6, 6 },
                        { 3, 5, 5, 3 },
                        { 4, 6, 6, 4 },
                        { 2, 8, 8, 2 },
                        { 6, 6 }
                      };
        TheatreSeating ts = new TheatreSeating(layout);
        ts.addOrder("Smith", 2);
        ts.addOrder("Jones", 5);
        ts.addOrder("Davis", 6);
        ts.addOrder("Wilson", 100);
        ts.addOrder("Johnson", 3);
        ts.addOrder("Williams", 4);
        ts.addOrder("Brown", 8);
        ts.addOrder("Miller", 12);

        ts.printLayout();
        ts.printOrders();
        System.out.println("------------------------------");
        ts.allotSeats();
        ts.printAllotments();

    }
}
