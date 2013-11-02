import java.util.ArrayList;
import java.util.List;

/** BinaryHeap Implementation.
  * All public methods terminate with a valid heap.
  * author: peter mikitsh pam3961
**/
class BinaryHeap {

  List<Integer> heap;

  /* Placeholder element added for array indexing by one. */
  public BinaryHeap() {
    heap = new ArrayList<Integer>();
    heap.add(-1);
  }

  /* i and j: indices. */
  private void swap(int parent, int child) {
    int tmp = heap.get(parent);
    heap.set(parent, heap.get(child));
    heap.set(child, tmp);
  }

  /* k: index. */
  private void swim(int i) {
    int child = i;
    int parent = child/2;
    while (child > 1 && heap.get(child) > heap.get(parent)) {
      swap(parent, child);
      child /= 2;
    }
  }

  /* k: index. */ 
  private void sink(int k) {
    int parent = k;
    int n = heap.size();
    while (2 * parent <= n) {
      int child = 2 * parent;
      if (child < n && heap.get(child) < heap.get(child+1))
        child++;
      if (heap.get(parent) >= heap.get(child))
        break;
      swap(parent, child);
      parent = child;
    }
  }

  /* Inserts the key in the heap. */
  public void insert(int key) {
    heap.add(key);
    swim(heap.size()-1);
  }

  /* Removes the root and returns it. */
  public int remove() {
    int key = heap.get(1);
    swap(1, heap.size()-1);
    heap.remove(heap.size()-1);
    sink(1);
    return key;
  }

}