package sort;

import java.util.ArrayList;
import java.util.List;

public class SortTestUtil {

    public static List<Integer> getExpected() {
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            expected.add(i);
        return expected;
    }

    public static List<Integer> genRandArr() {
        List<Integer> expected = getExpected();
        List<Integer> inputs = expected.subList(0, expected.size());
        for (int i = 0; i < inputs.size(); i++) {
            Integer tmp = inputs.get(i);
            int rand = randInt(i, inputs.size());
            inputs.set(i, inputs.get(rand));
            inputs.set(rand, tmp);
        }
        return inputs;
    }

    private static int randInt(int start, int end) {
        if (end <= start)
            return 0;
        return (int)(Math.random()*(end-start)) + start;
    }
}
