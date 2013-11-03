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
    heap = new ArrayList<Key>();
    heap.add(new Key(-1, -1, -1));
    loc = new int[n];
    for (int i = 0; i < n; i++) {
      loc[i] = -1;
    }
  }

  /* parent and child: heap indices. */
  private void swap(int parent, int child) {
    /* parent and child swap key locations in heap */
    Key tmp = heap.get(parent);
    heap.set(parent, heap.get(child));
    heap.set(child, tmp);

    // System.out.println("swap:: loc_init[]: " + Arrays.toString(loc));

    // System.out.println("swap:: parent: " + parent);
    // System.out.println("swap:: child: " + child);

    // System.out.println("swap:: loc[heap.get(parent).name()]: " + heap.get(parent).name());
    // System.out.println("swap:: loc[heap.get(child).name()]: " + heap.get(child).name());

    int parentName = heap.get(parent).name();
    int childName = heap.get(child).name();

    /* parent and child swap indices */
    int temp = loc[parentName];
    loc[parentName] = loc[childName];
    loc[childName] = temp;

    // System.out.println("swap:: loc[]: " + Arrays.toString(loc));
  }

  /* k: index. */
  private void swim(int i) {
    int child = i;
    int parent = child/2;
    while (child > 1 && heap.get(child).compareTo(heap.get(parent)) > 0) {
      swap(parent, child);
      child /= 2;
    }
  }

  /* k: index. */ 
  private void sink(int k) {
    // System.out.println("k: " + k);
    int parent = k;
    int n = heap.size() - 1;
    while ((2 * parent) < n) {
      int child = (2 * parent) - 1;
      // System.out.println("parent: " + parent);
      // System.out.println("child: " + child);
      // System.out.println("size:" + heap.size());
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
    loc[key.name()] = heap.size()-1;
    swim(heap.size()-1);
    // System.out.println("add:: loc[]: " + Arrays.toString(loc));
  }

  /* Removes the root and returns it. */
  public Key remove() {
    // System.out.println("Remove");
    int n = heap.size() - 1;
    Key key = heap.get(1);
    //System.out.println("remove:: loc[]: " + Arrays.toString(loc));
    // System.out.println("remove#swap1n:: n: " + n);
    swap(1, n);
    // System.out.println("remove#swap:: loc[]: " + Arrays.toString(loc));
    //loc[heap.get(n).name()] = n;
    heap.remove(n);
    loc[key.name()] = -1;
    sink(1);
    // System.out.println("remove#remove:: loc[]: " + Arrays.toString(loc));
    return key;
  }

  public boolean empty() {
    return heap.size() == 1;
  }

  /* Update a node iff it has a lower priority. */
  private void update(Key key) {
    // System.out.println("Update::Key: " + key.name());
    if (heap.get(loc[key.name()]).compareTo(key) > 0) {
      heap.set(loc[key.name()], key);
      swim(loc[key.name()]);
    }
    // System.out.println("update:: loc[]: " + Arrays.toString(loc));
  }

  public void addOrUpdate(Key key) {
    if (loc[key.name()] == -1) {
      add(key);
    } else {
      // System.out.println("addOrUpdate::loc[key.name()]: " + loc[key.name()]);
      // System.out.println("addOrUpdate:: loc[]: " + Arrays.toString(loc));
      update(key);
    }
  }

}