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
    long startTime = System.currentTimeMillis();

    generateVertices();
    generateAdjacencyMatrix();
    generateEdges();
    greedyAlgorithm();

    logger.logCoordinates(vertices);
    logger.logAdjacencyMatrix(gInitial.getMatrix());

    logger.logRuntime(System.currentTimeMillis() - startTime);
    
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
        gInitial.addEdgeWeight(y, x, weight);
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

  private static Double calculateVertexDistance(Map.Entry<Integer,Integer> v1, Map.Entry<Integer,Integer> v2) {
    
    int x1 = v1.getKey();
    int x2 = v2.getKey();
    int y1 = v1.getValue();
    int y2 = v2.getValue();

    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) ; 
  }


  private static double calculateDistance(ArrayList<Integer> list) {

    ArrayList<Integer> trip = addVertices(list);

    double result = 0;
    for (int i = 0; i < trip.size() - 1; i++) {
      result += gInitial.getMatrix()[trip.get(i)][trip.get(i+1)];
    }
    return result;

  }

  protected static ArrayList<Integer> addVertices(ArrayList<Integer> trip) {

    ArrayList<Integer> list = new ArrayList<Integer>(trip);

    list.add(0,0);
    list.add(0);

    return list;
  }

}