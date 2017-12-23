
import org.junit.Test;

public class QuickSortTest {
    QuickSort<Integer> qs = new QuickSort<>();

    @Test
    public void sort() {
        Integer[] arr = {9, 3, 10, 12, 4, 2, 8, 6, 5};
        Util.print(arr);
        arr = qs.sort(arr);
        Util.print(arr);
    }

}
