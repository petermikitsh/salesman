/** Prim's Algorithm.
  * author: peter mikitsh pam3961
**/

class Prim {

  public static Graph prim(Graph g0) {
    Graph g1 = new Graph(g0.getMatrix().length);
    BinaryHeap fringe = new BinaryHeap(g0.getMatrix().length);
    for (Key key : g0.neighbors(0)) {
      fringe.addOrUpdate(key);
    }
    while (!fringe.empty()) {
      Key edge = fringe.remove();
      g1.addEdge(edge);
      for (Key neighbor : g0.neighbors(edge.name())) {
        if (!g1.hasNode(neighbor.name()))
          fringe.addOrUpdate(neighbor);
      }
    }
    return g1;
  }

}