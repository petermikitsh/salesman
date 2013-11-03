import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/** MstTSP.java
  * Approximate solution for Traveling Salesman Problem (TSP)
  * using a Minimum Spanning Tree.
  * author: Peter Mikitsh pam3961
**/
class MstTSP {

  private static LinkedHashMap<Integer,Integer> vertices;
  private static Graph gInitial;
  private static Graph gMST;
  private static Logger logger;
  private static int n;
  private static int seed;

  /* - Initialize data structures for use.
     - Times the execution of the greedy algorithm.
     - Passes the results to the logger.
  */
	public static void main(String[] args) {

    logger = new Logger(args);
    n = (int) Integer.parseInt(args[0]);
    seed = (int) Integer.parseInt(args[1]);
    vertices = new LinkedHashMap<Integer,Integer>(n);
    gInitial = new Graph(n);
    gMST = new Graph(n);
    
    long startTime = System.currentTimeMillis();
    generateVertices();
    generateAdjacencyMatrix();
    gMST = Prim.prim(gInitial);
    long endTime = System.currentTimeMillis();

    logger.logCoordinates(vertices);
    logger.logAdjacencyMatrix(gInitial.getMatrix());
    logger.logAdjacencyMatrixMST(gMST.getMatrix());
    logger.logEdgeTour();
    logger.logOptimalPath();
    logger.logRuntime(endTime - startTime);
    
	}

  /* Generate Cartesian points for vertices, per Part 1 specifications. */
  private static void generateVertices() {
    
    Random randomX = new Random(seed);
    Random randomY = new Random(seed*2);

    while (vertices.size() < n) {

      int x = randomX.nextInt(n);
      int y = randomY.nextInt(n);

      if (vertices.containsKey(x))
        continue;
      else
        vertices.put(x, y);
      
    }
  }

  /* Fills in the Graph's adjacency matrix with appropriate weights. */
  private static void generateAdjacencyMatrix() {

    List<Map.Entry<Integer, Integer>> entries =
      new ArrayList<Map.Entry<Integer, Integer>>(vertices.entrySet());

    for (int x = 0; x < entries.size(); x++) {
      for (int y = 0; y < entries.size(); y++) {
        double weight = calculateVertexDistance(entries.get(x), entries.get(y));
        gInitial.addEdgeWeight(x, y, weight);
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