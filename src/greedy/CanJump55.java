package greedy;

public class CanJump55 {
    public static boolean canJump(int[] nums) {
        int nLen = nums.length;
        if (nLen < 2)
            return true;
        int minJump = 0;
        for (int i = nums.length-2; i >= 1; i--) {
            if (nums[i] > minJump)
                minJump = 0;
            else
                minJump++;

        }
        return nums[0] > minJump;
    }
}
