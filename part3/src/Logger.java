import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Logger.java
  * author: Peter Mikitsh pam3961
**/
class Logger {

	private int n;
  private static DecimalFormat df = new DecimalFormat("0.00");

  /* Constructor. */
	public Logger(String[] args) {
		checkErrorConditions(args);
	}

  /* Fail early by checking error conditions from Project specifications. */
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
      if (n < 1) {
        throw new IllegalArgumentException("Number of vertices must be greater than 0");
      }

    } catch (IllegalArgumentException e) {

      System.out.println(e.getMessage());
      System.exit(0);

    }
  }

  /* Prints graph vertex names with associated cartesian points. */
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

  /* Prints an adjacency matrix, with row and column labels. */
	public void logAdjacencyMatrix(double[][] adjMtrx, boolean[][] mst, boolean mstOnly) {
    if (n <= 10) {
      if (mstOnly)
        System.out.println("Minimum Spanning Tree:");
      System.out.printf("Adjacency matrix of graph weights:\n\n\t");

      int i;
      for (i = 0; i < adjMtrx.length; i++) {
        System.out.printf("  %d\t", i);
      }
      System.out.println("\n");

      for (i = 0; i < adjMtrx.length; i++) {
        System.out.printf("%d\t", i);
        for (int j = 0; j < adjMtrx.length; j++) {
          double distance = adjMtrx[i][j];
          if (mstOnly && !mst[i][j]) distance = 0;
          System.out.printf("%s\t", df.format(distance));
        }
        System.out.println("\n");
      }
    }
  }


  /* Prints MST Weight. */
  public void logMSTWeight(double weight) {
    if (n <= 10) {
      System.out.printf("\nTotal weight of mst: %s\n\n", df.format(weight));
    }
  }

  /* Prints the optimal solution distance and path traversal. */
	public void logOptimalPath(double distance, LinkedHashMap<Integer,Integer> path) {
    System.out.printf("\nDistance using mst: %s for path ", df.format(distance));
     for (Integer i : path.keySet())
       System.out.printf("%d ", i);
    System.out.printf("0\n");
  }

  /* Prints the runtime. */
	public void logRuntime(long time) {
    System.out.printf("Runtime for Mst TSP   : %s milliseconds\n", time);
  }

  /* Prints ordered list of edges from greedy tour. */
  public void logEdgeTour(LinkedHashMap<Integer,Integer> tour) {
    if (n <= 10) {
      System.out.println("Pre-order traversal: ");
      for (Map.Entry<Integer,Integer> entry : tour.entrySet()) {
        System.out.printf("Parent of %d is %d\n", entry.getKey(), entry.getValue());
      }
    }
  }

}