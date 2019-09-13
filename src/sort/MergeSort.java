package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort<T extends Comparable> implements Sort<T> {
    @Override
    public List<T> sort(List<T> elements) {
        if (elements == null || elements.size() <= 1)
            return elements;
        List<T> former = sort(elements.subList(0, elements.size()/2));
        List<T> latter = sort(elements.subList(elements.size()/2, 0));
        int eIdx = 0, fIdx =0, lIdx = 0;
        while (eIdx < elements.size()) {
            if (fIdx == former.size()) {
                elements.set(eIdx, latter.get(lIdx));
                lIdx++;
            } else if (lIdx == latter.size()) {
                elements.set(eIdx, former.get(fIdx));
                fIdx++;
            } else {
                if (former.get(fIdx).compareTo(latter.get(lIdx)) < 0) {
                    elements.set(eIdx, former.get(fIdx));
                    fIdx++;
                } else {
                    elements.set(eIdx, latter.get(lIdx));
                    lIdx++;
                }
            }
            eIdx++;
        }
        return elements;
    }
}
