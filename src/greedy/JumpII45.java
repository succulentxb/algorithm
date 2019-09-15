package greedy;

public class JumpII45 {
    public int jump(int[] nums) {
        int nLen = nums.length;
        if (nLen <= 1)
            return 0;
        int jumpNum = 0;
        int i = 0;
        while (i < nLen) {
            int maxPos = 0;
            int maxDest = 0;
            for (int j = i; j <= i+nums[i]; j++) {
                if (j >= nLen-1)
                    return ++jumpNum;
                int dest = j + nums[j];
                if (dest > maxDest) {
                    maxDest = dest;
                    maxPos = j;
                }
            }
            i = maxPos;
            jumpNum++;
        }
        return jumpNum;
    }
}
