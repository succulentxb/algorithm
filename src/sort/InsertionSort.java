package sort;

import java.util.List;

public class InsertionSort<T extends Comparable> implements Sort<T> {
    @Override
    public List<T> sort(List<T> elements) {
        if (elements == null || elements.size() == 0)
            return elements;

        for (int i = 0; i < elements.size(); i++) {
            T tmp = elements.get(i);
            for (int j = i; j >= 0; j--) {
                if (j == 0) {
                    elements.set(j, tmp);
                    break;
                }
                if (tmp.compareTo(elements.get(j-1)) < 0)
                    elements.set(j, elements.get(j-1));
                else {
                    elements.set(j, tmp);
                    break;
                }
            }
        }
        return elements;
    }
}
