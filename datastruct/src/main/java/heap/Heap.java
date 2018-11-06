package heap;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qisy01
 * @create 18-11-3
 * @since 1.0.0
 */
public class Heap {
    @Getter
    private final List<Integer> heap;

    public Heap() {
        heap = new ArrayList<>();
    }

    public Heap(List<Integer> heap) {
        this.heap = heap;
        floyed();
    }

    /**
     * floyed排序
     */
    private void floyed() {
        //对每一个元素都进行一次下沉，由于它下级的已经下沉过了，所以不会从上到下无限的下沉
        for (int i = heap.size() - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    public Integer getMax() {
        return heap.get(0);
    }

    public void deleteMax() {
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        percolateDown(0);
    }

    public void insert(Integer inserter) {
        heap.add(inserter);
        percolateUp(heap.size() - 1);
    }

    private void percolateUp(int i) {
        if (HeapHelper.isTop(i)) {
            return;
        }
        int parent = HeapHelper.getParent(i);
        if (i == bigger(i, parent)) {
            swap(i, parent);
            percolateUp(parent);
        }
    }

    /**
     * 下沉过程中要找出leftchild和rightchild中最大值
     * 所以直接三者比较
     *
     * @param i
     */
    private void percolateDown(int i) {
        int heapSize = heap.size();
        int leftChild = HeapHelper.leftChild(i);
        if (!HeapHelper.inHeap(heapSize, leftChild)) {
            return;
        }
        int rightChild = HeapHelper.rightChild(i);
        int bigest = heap.get(i) > heap.get(leftChild) ? i : leftChild;
        if (HeapHelper.inHeap(heapSize, rightChild)) {
            bigest = heap.get(bigest) > heap.get(rightChild) ? bigest : rightChild;
        }
        if (i == bigest) {
            return;
        }
        swap(i, bigest);
    }

    private void swap(int i, int j) {
        Integer temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private int bigger(int i, int j) {
        return (heap.get(i) > heap.get(j)) ? i : j;
    }
}
