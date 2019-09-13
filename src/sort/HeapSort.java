package sort;

import java.util.ArrayList;
import java.util.List;

public class HeapSort<T extends Comparable> implements Sort<T> {
    @Override
    public List<T> sort(List<T> elements) {
        return null;
    }

}

class MinHeap<T extends Comparable> {
    private List<T> eleList;
    public MinHeap(List<T> elements) {

    }

    private void heapify(List<T> elements) {
        this.eleList = new ArrayList<>();
        for (T e: elements) {
            this.insert(e);
        }
    }

    public T extractMin() {
        T lastE = this.eleList.remove(this.eleList.size()-1);
        T min = this.eleList.get(0);
        this.eleList.set(0, lastE);
        int currIdx = 0;
        return null;
    }

    public void insert(T e) {
        this.eleList.add(e);
        if (this.eleList.size() <= 1)
            return;
        int currIdx = this.eleList.size()-1;
        while (currIdx > 0 && e.compareTo(this.father(currIdx)) < 0) {
            this.eleList.set(currIdx, this.father(currIdx));
            currIdx = this.fatherIdx(currIdx);
        }
        this.eleList.set(currIdx, e);
    }

    private int lChildIdx(int i) {
        return (i+1)*2-1;
    }

    private int rChildIdx(int i) {
        return (i+1)*2;
    }

    private int fatherIdx(int i) {
        return (i+1)/2-1;
    }

    private T lChild(int i) {
        return this.eleList.get(this.lChildIdx(i));
    }

    private T rChild(int i) {
        return this.eleList.get(this.rChildIdx(i));
    }

    private T father(int i) {
        return this.eleList.get(this.fatherIdx(i));
    }
}