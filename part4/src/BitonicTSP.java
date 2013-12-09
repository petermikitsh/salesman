import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/** MstTSP.java
  * Approximate solution for Traveling Salesman Problem (TSP)
  * using a Minimum Spanning Tree.
  * author: Peter Mikitsh pam3961
**/
class BitonicTSP {

  private static Map<Integer,Integer> vUnsorted;
  private static Map<Integer,Integer> vSorted;
  private static Graph g;
  private static Logger logger;
  private static int n;
  private static int seed;
  private static Bitonic b;

  /* - Initialize data structures for use.
     - Times the execution of the greedy algorithm.
     - Passes the results to the logger.
  */
	public static void main(String[] args) {

    logger = new Logger(args);
    n = (int) Integer.parseInt(args[0]);
    seed = (int) Integer.parseInt(args[1]);
    vUnsorted = new LinkedHashMap<Integer,Integer>(n);
    g = new Graph(n);
    b = new Bitonic(n, g);
    
    long startTime = System.currentTimeMillis();
    generateVertices();
    sortVertices();
    generateAdjacencyMatrix();
    Prim.prim(g);
    g.preorder();
    b.bitonic();
    long endTime = System.currentTimeMillis();

    logger.logCoordinates(vUnsorted, false);
    logger.logAdjacencyMatrix(g.getMatrix(), true);
    logger.logCoordinates(vSorted, true);
    logger.logMSTWeight(g.cost());
    logger.logOptimalPath(g.distance(), g.traversal());
    logger.logRuntime(endTime - startTime);
    
	}

  /* Generate Cartesian points for vertices, per Part 1 specifications. */
  private static void generateVertices() {
    
    Random randomX = new Random(seed);
    Random randomY = new Random(seed*2);

    while (vUnsorted.size() < n) {

      int x = randomX.nextInt(n);
      int y = randomY.nextInt(n);

      if (vUnsorted.containsKey(x))
        continue;
      else
        vUnsorted.put(x, y);
      
    }
  }

  private static void sortVertices() {
    vSorted = new TreeMap<Integer,Integer>(vUnsorted);
  }

  /* Fills in the Graph's adjacency matrix with appropriate weights. */
  private static void generateAdjacencyMatrix() {

    List<Map.Entry<Integer, Integer>> entries =
      new ArrayList<Map.Entry<Integer, Integer>>(vUnsorted.entrySet());

    for (int x = 0; x < entries.size(); x++) {
      for (int y = 0; y < entries.size(); y++) {
        double weight = calculateVertexDistance(entries.get(x), entries.get(y));
        g.addEdgeWeight(x, y, weight);
      }
    }
  }

  /* Use the distance formula to find the distance between to Cartesian points (vertices) */
  private static Double calculateVertexDistance(Map.Entry<Integer,Integer> v1, Map.Entry<Integer,Integer> v2) {
    
    int x1 = v1.getKey();
    int x2 = v2.getKey();
    int y1 = v1.getValue();
    int y2 = v2.getValue();

    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) ; 
  }

}