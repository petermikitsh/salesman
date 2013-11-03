/** Prim's Algorithm.
  * author: peter mikitsh pam3961
**/

class Prim {

  public static void prim(Graph g) {
    BinaryHeap fringe = new BinaryHeap(g.getMatrix().length);
    for (Key key : g.neighbors(0)) {
      // System.out.println("Adding key to fringe: " + key);
      fringe.addOrUpdate(key);
      // System.out.println("Fringe: " + fringe);
    }
    // System.out.println("[SRT] Fringe: " + fringe);
    while (!fringe.empty()) {
      Key edge = fringe.remove();
      // System.out.println("[DEL] Fringe: " + fringe);
      
      g.addToMst(edge);
      for (Key neighbor : g.neighbors(edge.name())) {
        if (!g.hasNode(neighbor.name())) {
          fringe.addOrUpdate(neighbor);
          // System.out.println("[ADD] Fringe: " + fringe);
        }
      }
    }
  }

}