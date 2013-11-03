/** Simple test case for Binary Heap.
  * author: peter mikitsh pam3961
**/

public class BinaryHeapTest {

  public static void main(String[] args) {
    BinaryHeap h = new BinaryHeap(5);
    Key k = new Key(0, 1, 1);
    h.addOrUpdate(k);
    k = new Key(1, 2, 2);
    h.addOrUpdate(k);
    h.remove();
    h.remove();
  }
}