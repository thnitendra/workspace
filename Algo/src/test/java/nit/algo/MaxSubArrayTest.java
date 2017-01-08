package nit.algo;

import org.junit.Test;

public class MaxSubArrayTest {
    MaxSubArray msa = new MaxSubArray();
    @Test
    public void findMaxSubArray() {
        int[] arr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        Util.print(arr);
        MaxSubArray.SubArrayInfo sinfo = msa.findMaxSubarry(arr);
        System.out.println(sinfo);
        Util.printArr(sinfo.start, sinfo.end, arr);
    }

}
