package greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JumpII45Test {

    @Test
    void jump() {
        JumpII45 t = new JumpII45();

        int[] t1 = new int[]{2,3,1,1,4};
        System.out.println(t.jump(t1));

        int[] t2 = new int[10000];
        for (int i = 0; i < 10000; i++)
            t2[i] = 1;
        System.out.println(t.jump(t2));
    }
}