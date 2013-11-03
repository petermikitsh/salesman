import java.util.List;
import java.util.ArrayList;

/** Graph data structure.
	* Uses an adjacency matrix to store edge weights,
	* author: Peter Mikitsh pam3961
*/
class Graph {

	private double[][] adjMatrix;

	/* Initializes the Graph with an adjacency matrix of size n. */
	public Graph(int n) {
		adjMatrix = new double[n][n];
	}

	/* Updates both sides of the adjacency matrix to store weight state. */
	public void addEdgeWeight(int row, int col, double weight) {
		adjMatrix[row][col] = weight;
		adjMatrix[col][row] = weight;
	}

	public double[][] getMatrix() {
		return adjMatrix;
	}

	public List<Key> neighbors(int key) {
		List<Key> keys = new ArrayList<Key>();
		for (int i = 0; i < adjMatrix.length; i++) {
			if (adjMatrix[key][i] > 0) {
				keys.add(new Key(i, adjMatrix[key][i], key));
			}
		}
		return keys;
	}

	public void addEdge(Key key) {
		addEdgeWeight(key.name(), key.to(), key.weight());
	}

	public boolean hasNode(int node) {
		boolean exists = false;
		for (int i = 0; i < adjMatrix.length; i++) {
			if (adjMatrix[node][i] != 0)
				exists = true;
		}
		return exists;
	}

	public double cost() {
		double cost = 0;
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < i; j++) {
				cost += adjMatrix[i][j];
			}
		}
		return cost;
	}


}