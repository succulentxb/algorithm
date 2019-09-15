package greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CanJump55Test {

    @Test
    void canJump() {
        int[] t1 = new int[]{1, 1};
        assert CanJump55.canJump(t1);

        int[] t2 = new int[]{0, 1};
        assert !CanJump55.canJump(t2);

        int[] t3 = new int[]{2,3,1,1,4};
        assert CanJump55.canJump(t3);

        int[] t4 = new int[]{3,2,1,0,4};
        assert !CanJump55.canJump(t4);
    }
}