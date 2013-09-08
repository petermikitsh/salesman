import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

class OptimalTSP {

  private static LinkedHashMap<Integer,Integer> vertices;

	public static void main(String[] args) {

    try {
      checkErrorConditions(args);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

    int n = Integer.parseInt(args[0]);
    int seed = Integer.parseInt(args[1]);

    generateVertices(n, seed);

    if (n <= 10) {
      printCoordinates();
    }
    
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

  private static void printCoordinates() {

    System.out.println("X-Y Coordinates:");

    int index = 0;
    for (Map.Entry<Integer, Integer> entry : vertices.entrySet()) {
      System.out.format("v%d: (%d,%d) ", index, entry.getKey(), entry.getValue());
      index++;
    }

    System.out.println();
  }
}