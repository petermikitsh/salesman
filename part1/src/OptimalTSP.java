import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class OptimalTSP {

  private static LinkedHashMap<Integer,Integer> vertices;
  private static double[][] adjMtrx;
  private static ArrayList<ArrayList<Integer>> permutations;
  private static DecimalFormat df = new DecimalFormat("0.00");

	public static void main(String[] args) {

    try {
      checkErrorConditions(args);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      System.exit(0);
    }

    long startTime = System.currentTimeMillis();

    int n = Integer.parseInt(args[0]);
    int seed = Integer.parseInt(args[1]);

    generateVertices(n, seed);
    generateAdjacencyMatrix();
    generatePermutations(n);

    if (n <= 10) {
      printCoordinates();
      printAdjacencyMatrix();
    }

    findAndPrintPermutations(n);

    long endTime = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    printRuntime(totalTime);
    
	}

  private static void checkErrorConditions(String[] args) throws IllegalArgumentException {

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

    Integer n = Integer.parseInt(args[0]);
    if (n < 1 || n > 13) {
      throw new IllegalArgumentException("Number of vertices must be between 1 and 13");
    }
  }

  private static void generateVertices(int n, int seed) {
    
    Random randomX = new Random(seed);
    Random randomY = new Random(seed*2);
    vertices = new LinkedHashMap<Integer,Integer>(n);

    while (vertices.size() < n) {
      int x = randomX.nextInt(n);
      int y = randomY.nextInt(n);

      if (vertices.containsKey(x)) {
        continue;
      } else {
        vertices.put(x, y);
      }
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

    return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2)) ; 
  }

  private static void printAdjacencyMatrix() {

    System.out.printf("Adjacency matrix of graph weights:\n\n\t");

    int i;
    for (i = 0; i < adjMtrx.length; i++) {
      System.out.printf("  %d\t", i);
    }
    System.out.println("\n");

    for (i = 0; i < adjMtrx.length; i++) {
      System.out.printf("%d\t", i);
      for (double distance : adjMtrx[i]) {
        System.out.printf("%s\t", df.format(distance));
      }
      System.out.println("\n");
    }
  }

  private static void printCoordinates() {

    System.out.println("X-Y Coordinates:");

    int index = 0;
    for (Map.Entry<Integer, Integer> entry : vertices.entrySet()) {
      System.out.format("v%d: (%d,%d) ", index, entry.getKey(), entry.getValue());
      index++;
    }

    System.out.println("\n");
  }

  private static void generatePermutations(int n) {

    ArrayList<Integer> ascending = new ArrayList<Integer>();
    for (int i = 1; i < n; i++) {
      ascending.add(i);
    }
    permutations = Permutation.makePermutations(ascending);

  }

  private static void findAndPrintPermutations(int n) {

    ArrayList<Integer> bestPermutation = new ArrayList<Integer>();
    double bestDistance = Double.MAX_VALUE;

    for (ArrayList<Integer> permutation : permutations) {
      double currDistance = calculateDistance(permutation);

      if (n <= 5)
        System.out.printf("Path: %s  distance = %s\n", printPermutation(permutation), df.format(currDistance));

      if (currDistance < bestDistance) {
        bestDistance = currDistance;
        bestPermutation = permutation;
      }
    }

    System.out.printf("\nOptimal distance: %s for path %s\n", df.format(bestDistance), printPermutation(bestPermutation));

  }

  private static String printPermutation(ArrayList<Integer> array) {
    String result = "";
    for (Integer vertex : array) {
      result += String.format("%d ", vertex);
    }
    return result;
  }

  private static double calculateDistance(ArrayList<Integer> trip) {

    double result = 0;
    for (int i = 0; i < trip.size() - 1; i++) {
      result += adjMtrx[trip.get(i)][trip.get(i+1)];
    }
    return result;

  }

  private static void printRuntime(long time) {
    System.out.printf("Runtime for optimal TSP   : %s milliseconds\n", time);
  }

}