package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSort<T extends Comparable> implements Sort<T> {
    @Override
    public List<T> sort(List<T> elements) {
        if (elements == null || elements.size() <= 1)
            return elements;
        quickSort(elements, 0, elements.size());
        return elements;
    }

    private void quickSort(List<T> elements, int start, int end) {
        if (end-start <= 1)
            return;
        T sentry = elements.get(end-1);
        int cursor = start - 1;
        for (int i = start; i < end; i++) {
            if (elements.get(i).compareTo(sentry) < 0) {
                cursor += 1;
                T tmp = elements.get(cursor);
                elements.set(cursor, elements.get(i));
                elements.set(i, tmp);
            }
        }
        T tmp = elements.get(++cursor);
        elements.set(cursor, elements.get(end-1));
        elements.set(end-1, tmp);
        quickSort(elements, start, cursor);
        quickSort(elements, cursor, end);
    }
}
