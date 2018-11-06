package heap;

import lombok.extern.java.Log;

import java.util.Arrays;
import java.util.List;

/**
 * @author qisy01
 * @create 18-11-3
 * @since 1.0.0
 */
@Log
public class HeapMain {
    public static void main(String[] args) {
//        Heap heap = new Heap();
//        heap.insert(5);
//        heap.insert(6);
//        heap.insert(3);
//        heap.insert(1);
//        log.info("array is " + Arrays.toString(heap.getHeap().toArray()));
//        heap.deleteMax();
//        log.info("array is " + Arrays.toString(heap.getHeap().toArray()));
        List<Integer> list = Arrays.asList(new Integer[]{19, 2, 5, 16, 55, 21});
        Heap heap = new Heap(list);
        log.info("heap is " + Arrays.toString(heap.getHeap().toArray()));
    }
}
