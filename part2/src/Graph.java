import java.util.List;
import java.util.ArrayList;

class Graph {

	private double[][] adjMatrix;
	private boolean[] visited;
	private List<Edge> edges;

	public Graph(int n) {
		adjMatrix = new double[n][n];
		edges = new ArrayList<Edge>();
	}

	public void addEdgeWeight(int row, int col, double weight) {
		adjMatrix[row][col] = weight;
		adjMatrix[col][row] = weight;
	}

	public void addEdge(int row, int col) {
		edges.add(new Edge(row, col, this));
	}

	public void addEdge(Edge e) {
		edges.add(e);
	}

	public double getWeight(int row, int col) {
		return adjMatrix[row][col];
	}

	public double[][] getMatrix() {
		return adjMatrix;
	}

	public int getEdgeCount() {
		return edges.size();
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void initEmptyWeights() {
		for (int row = 0; row < adjMatrix.length; row++) {
			for (int col = 0; col < adjMatrix.length; col++) {
				adjMatrix[row][col] = 0.0;
			}
		}
	}

	/* Helper function (simpler for clients). */
	public boolean tooManyDegrees(int r1, int r2) {
		return tooManyDegrees(r1) || tooManyDegrees(r2);
	}

	/* Returns true iff a vertex will have too many edges. */
	private boolean tooManyDegrees(int vertex) {
		int count = 0;
		for (double edge : adjMatrix[vertex]) {
			if (edge > 0)
				count++;
		}
		return count >= 2;
	}

	/* Determines if the last edge is being added to the graph */
	public boolean notLastEdge() {
		return getEdgeCount() + 1 < adjMatrix.length;
	}

	/* Helper DFS function (client interface) that:
		- initializes the visited matrix
		- starts the traversal at node 0
	*/
	public List<Integer> dfsTraversal() {
		visited = new boolean[adjMatrix.length];
		for (int i = 0; i < adjMatrix.length; i++)
			visited[i] = false;
		List<Integer> traversal = new ArrayList<Integer>();
		traversal.add(0);
		dfs(0, traversal);
		traversal.add(0);
		return traversal;
	}

	/* Performs a depth-first search beginning at node. */
	private List<Integer> dfs(int node, List<Integer> traversal) {
		for (int i = 1; i < adjMatrix.length; i++) {
			if (adjMatrix[node][i] > 0 && !visited[i]) {
				traversal.add(i);
				visited[i] = true;
				dfs(i, traversal);
			}
		}
		return traversal;
	}


	/* Helper function DFS */
	public List<Integer> neighbors(int node) {
		List<Integer> l = new ArrayList<Integer>();

		return l;
	}

	/* Find total distance of edge list */
	public double calculateDistance() {

	    double result = 0;
	    for (Edge e : edges) {
	      result += e.getWeight();
	    }
	    return result;

  	}

}