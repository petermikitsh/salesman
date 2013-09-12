import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class OptimalTSP {

  private static LinkedHashMap<Integer,Integer> vertices;
  private static double[][] adjMtrx;
  private static Logger logger;
  private static int n;
  private static int seed;

	public static void main(String[] args) {

    checkErrorConditions(args);    

    n = (int) Integer.parseInt(args[0]);
    seed = (int) Integer.parseInt(args[1]);
    logger = new Logger(n);
    vertices = new LinkedHashMap<Integer,Integer>(n);
    long startTime = System.currentTimeMillis();

    generateVertices();
    generateAdjacencyMatrix();

    logger.logCoordinates(vertices);
    logger.logAdjacencyMatrix(adjMtrx);

    generatePermutations();

    logger.logRuntime(System.currentTimeMillis() - startTime);
    
	}

  private static void checkErrorConditions(String[] args) {

    try {

      if (args.length != 2) {
        throw new IllegalArgumentException("Usage: java OptimalTSP n seed");
      }

      for (int i = 0; i < 2; i++) {
        try {
          Integer.parseInt(args[i]);
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Command line args must be integers");
        }
      }

      n = (int) Integer.parseInt(args[0]);
      if (n < 1 || n > 13) {
        throw new IllegalArgumentException("Number of vertices must be between 1 and 13");
      }

    } catch (IllegalArgumentException e) {

      System.out.println(e.getMessage());
      System.exit(0);

    }
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
    adjMtrx = new double[entries.size()][entries.size()];

    for (int x = 0; x < entries.size(); x++) {
      for (int y = 0; y < entries.size(); y++) {
        adjMtrx[x][y] = calculateVertexDistance(entries.get(x), entries.get(y));
        adjMtrx[y][x] = adjMtrx[x][y];
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


  private static void generatePermutations() {

    ArrayList<Integer> list = new ArrayList<Integer>();

    for (int i = 1; i < n; i++) {
      list.add(i);
    }

    ArrayList<Integer> shortestPath = new ArrayList<Integer>();
    double shortestDistance = Double.MAX_VALUE;

    while( list != null ) {

      double currDistance = calculateDistance(list);
      logger.logPermutation(list, currDistance);

      if (currDistance < shortestDistance) {
        shortestDistance = currDistance;
        shortestPath = new ArrayList<Integer>(list);
      }

      list = Permutation.nextPermutation(list);
    }

    logger.logOptimalPath(shortestDistance, shortestPath);
  }

  private static double calculateDistance(ArrayList<Integer> list) {

    ArrayList<Integer> trip = addVertices(list);

    double result = 0;
    for (int i = 0; i < trip.size() - 1; i++) {
      result += adjMtrx[trip.get(i)][trip.get(i+1)];
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