package sort;

import java.util.List;

public interface Sort<T extends Comparable> {
    public List<T> sort(List<T> elements);
}
