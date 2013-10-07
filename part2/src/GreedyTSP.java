import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class GreedyTSP {

  private static LinkedHashMap<Integer,Integer> vertices;
  private static Graph gInitial;
  private static Graph gGreedy;
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
    gGreedy = new Graph(n);
    gGreedy.initEmptyWeights();
    
    long startTime = System.currentTimeMillis();
    generateVertices();
    generateAdjacencyMatrix();
    generateEdges();
    greedyAlgorithm();
    long endTime = System.currentTimeMillis();

    logger.logCoordinates(vertices);
    logger.logAdjacencyMatrix(gInitial.getMatrix());
    logger.logAdjacencyMatrix(gGreedy.getMatrix());
    logger.logEdgeTour(gGreedy.getEdges());
    logger.logOptimalPath(gGreedy.calculateDistance(), gGreedy.dfsTraversal());
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

  /* Store the edges in an array, to make my life simpler. */
  private static void generateEdges() {
    for (int row = 0; row <= n-1; row++) {
      for (int column = 0; column <= row-1; column++) {
        gInitial.addEdge(row, column);
      }
    }
  }

  /* Performs a greedy, approximately optimal tour. */
  public static void greedyAlgorithm() {

    List<Edge> edges = Quicksort.quicksort(gInitial.getEdges());
    DisjointSet set = new DisjointSet(n);

    for (Edge e : edges) {
      // Break if enough edges have been picked.
      if (gGreedy.getEdgeCount() >= n) {
        break;
      } else {
        int r1 = set.find(e.row);
        int r2 = set.find(e.column);
        // Skip if adding this edge will exceed the degree count for vertices -or-
        // if this is not the last edge to add, and adding it will introduce a cycle.
        if (gGreedy.tooManyDegrees(e.row, e.column) ||
            !gGreedy.lastEdge() && DisjointSet.introduceCycle(r1, r2)) {
            continue;
        } else {
        // Union the subtrees and update the greedy graph.
          set.union(r1, r2);
          gGreedy.addEdge(e);
          gGreedy.addEdgeWeight(e.row, e.column, gInitial.getWeight(e.row, e.column));
        }
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