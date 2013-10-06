import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

	public void logOptimalPath(double distance, ArrayList<Integer> path) {
    System.out.printf("\nOptimal distance: %s for path \n",
      df.format(distance));
  }

	public void logRuntime(long time) {
    System.out.printf("Runtime for optimal TSP   : %s milliseconds\n", time);
  }

}