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

  private static void generateEdges() {
    for (int row = 0; row <= n-1; row++) {
      for (int column = 0; column <= row-1; column++) {
        gInitial.addEdge(row, column);
      }
    }
  }

  public static void greedyAlgorithm() {

    List<Edge> edges = Quicksort.quicksort(gInitial.getEdges());
    DisjointSet set = new DisjointSet(n);

    for (Edge e : edges) {
      // break if enough edges have been picked
      if (gGreedy.getEdgeCount() >= n) {
        break;
      } else {
        int r1 = set.find(e.row);
        int r2 = set.find(e.column);
        // if adding this edge will exceed the degree count for vertices, skip this edge
        if (gGreedy.tooManyDegrees(e.row, e.column)) {
          continue;
        // if this is not the last edge to add, check if adding it will introduce a cycle
        } else if (gGreedy.notLastEdge() && DisjointSet.introduceCycle(r1, r2)) {
          continue;
        }
        else {
          set.union(r1, r2);
          gGreedy.addEdge(e);
          gGreedy.addEdgeWeight(e.row, e.column, gInitial.getWeight(e.row, e.column));
        }
      }
    }
  }
  
  private static Double calculateVertexDistance(Map.Entry<Integer,Integer> v1, Map.Entry<Integer,Integer> v2) {
    
    int x1 = v1.getKey();
    int x2 = v2.getKey();
    int y1 = v1.getValue();
    int y2 = v2.getValue();

    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) ; 
  }

}