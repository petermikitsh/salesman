import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class Logger {

	private int n;
  private static DecimalFormat df = new DecimalFormat("0.00");

	public Logger(String[] args) {
		checkErrorConditions(args);
	}

  private void checkErrorConditions(String[] args) {

    try {

      if (args.length != 2) {
        throw new IllegalArgumentException("Usage: java GreedyTSP n seed");
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

	public void logCoordinates(LinkedHashMap<Integer,Integer> vertices) {
		if (n <= 10) {

  		System.out.println("X-Y Coordinates:");

      int index = 0;
      for (Map.Entry<Integer, Integer> entry : vertices.entrySet()) {
        System.out.format("v%d: (%d,%d) ", index, entry.getKey(), entry.getValue());
        index++;
      }

      System.out.println("\n");

		}
	}

	public void logAdjacencyMatrix(double[][] adjMtrx) {
    if (n <= 10) {
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
  }

	public void logOptimalPath(double distance, List<Integer> path) {
    System.out.printf("\nDistance using greedy: %s for ", df.format(distance));
    for (Integer i : path)
      System.out.printf("%d ", i);
    System.out.println("");
  }

	public void logRuntime(long time) {
    System.out.printf("Runtime for greedy TSP   : %s milliseconds\n", time);
  }

  public void logEdgeTour(List<Edge> tour) {
    if (n <= 10) {
      System.out.println("Edges of tour from greedy graph:");
      for (Edge e : tour)
        System.out.printf("%d %d weight = %s\n", e.column, e.row, df.format(e.getWeight()));
      }
  }

}