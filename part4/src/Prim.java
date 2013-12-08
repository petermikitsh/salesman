/** Prim's Algorithm.
  * author: peter mikitsh pam3961
**/

class Prim {

  /* Fringe implemented as a minimum, binary heap. Minimum spanning tree stored
   * in graph as boolean values.
   */
  public static void prim(Graph g) {
    BinaryHeap fringe = new BinaryHeap(g.getMatrix().length);
    for (Key key : g.neighbors(0)) {
      fringe.addOrUpdate(key);
    }
    while (!fringe.empty()) {
      Key edge = fringe.remove();
      g.addToMst(edge);
      for (Key neighbor : g.neighbors(edge.name())) {
        if (g.notInMST(neighbor.name())) {
          fringe.addOrUpdate(neighbor);
        }
      }
    }
  }

}