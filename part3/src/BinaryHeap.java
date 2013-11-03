import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/** BinaryHeap Implementation.
  * All public methods terminate with a valid heap.
  * author: peter mikitsh pam3961
**/
class BinaryHeap {

  /* Underlying heap data structure: a list. */
  private List<Key> heap;

  /* index: the node name.
     value: location in the heap. -1 denotes not in heap. */
  private int[] loc;

  /* Placeholder element added for array indexing by one.
     n: number of nodes in graph.
  */
  public BinaryHeap(int n) {
    heap = new ArrayList<Key>(n);
    heap.add(new Key(-1, -1, -1));
    loc = new int[n];
    for (int i = 0; i < n; i++) {
      loc[i] = -1;
    }
  }

  /* parent and child: heap indices. */
  private void swap(int parent, int child) {

    
    Key p = heap.get(parent);
    Key c = heap.get(child);

    /* parent and child swap key locations in heap */
    Key tmp = heap.get(parent);
    heap.set(parent, heap.get(child));
    heap.set(child, tmp);

    int parentName = heap.get(parent).name();
    int childName = heap.get(child).name();

    /* parent and child swap indices */
    int temp = loc[parentName];
    loc[parentName] = loc[childName];
    loc[childName] = temp;
  }

  /* k: index. Bottom, up. */
  private void swim(int i) {
    int child = i;
    while (child > 1 && heap.get(child).compareTo(heap.get(child/2)) > 0) {
      swap(child/2, child);
      child = child/2;
    }
  }

  /* k: index. Top, down. */ 
  private void sink(int k) {
    int parent = k;
    int n = heap.size() - 1;
    while ((2 * parent) < n) {
      int child = (2 * parent);
      if (child < n && heap.get(child).compareTo(heap.get(child+1)) < 0)
        child++;
      if (heap.get(parent).compareTo(heap.get(child)) >= 0)
        break;
      swap(parent, child);
      parent = child;
    }
  }

  /* Inserts the key in the heap. */
  private void add(Key key) {
    heap.add(key);
    int n = heap.size() - 1;
    loc[key.name()] = n;
    swim(n);
  }

  /* Removes the root and returns it. */
  public Key remove() {
    int n = heap.size() - 1;
    Key key = heap.get(1);
    swap(1, n);
    heap.remove(n);
    loc[key.name()] = -1;
    sink(1);
    return key;
  }

  public boolean empty() {
    return heap.size() == 1;
  }

  /* Update a node iff it has a lower priority. */
  private void update(Key key) {
    if (key.compareTo(heap.get(loc[key.name()])) > 0) {
      heap.set(loc[key.name()], key);
      swim(loc[key.name()]);
    }
  }

  /* To simplify client use of the heap. */
  public void addOrUpdate(Key key) {
    if (loc[key.name()] == -1) {
      add(key);
    } else {
      update(key);
    }
  }

  public String toString() {
    String string = "[";
    for (Key k : heap) {
      string += k.toString();
    }
    string += "]";
    return string;
  }

}