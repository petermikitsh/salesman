import java.util.List;
import java.util.ArrayList;

class Graph {

	private double[][] adjMatrix;
	private ArrayList<Edge> edges;

	public Graph(int n) {
		adjMatrix = new double[n][n];
		edges = new ArrayList<Edge>();
	}

	public void addEdgeWeight(int row, int col, double weight) {
		adjMatrix[row][col] = weight;
	}

	public void addEdge(int row, int col) {
		edges.add(new Edge(row, col, this));
	}

	public double getWeight(int row, int col) {
		return adjMatrix[row][col];
	}

	public double[][] getMatrix() {
		return adjMatrix;
	}

}