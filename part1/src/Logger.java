import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

class Logger {

	private int n;
  private static DecimalFormat df = new DecimalFormat("0.00");

	public Logger(int n) {
		this.n = n;
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

	public void logPermutation(ArrayList<Integer> list, double distance) {
    if (n <= 5) {
      System.out.printf("Path: %s  distance = %s\n", permutationToString(list), df.format(distance));
    }
  }

  private String permutationToString(ArrayList<Integer> list) {
    ArrayList<Integer> array = OptimalTSP.addVertices(list);

    String result = "";
    for (Integer vertex : array) {
      result += String.format("%d ", vertex);
    }
    return result;
  }

	public void logOptimalPath(double distance, ArrayList<Integer> path) {
    System.out.printf("\nOptimal distance: %s for path %s\n",
      df.format(distance), permutationToString(path));
  }

	public void logRuntime(long time) {
    System.out.printf("Runtime for optimal TSP   : %s milliseconds\n", time);
  }
  
}