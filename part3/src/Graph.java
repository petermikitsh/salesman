import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/** Graph data structure.
	* Uses an adjacency matrix to store edge weights,
	* author: Peter Mikitsh pam3961
*/
class Graph {

	private double[][] adjMatrix;
	private boolean[][] mst;
	private LinkedHashMap<Integer,Integer> traversal;

	/* Initializes the Graph with an adjacency matrix of size n. */
	public Graph(int n) {
		adjMatrix = new double[n][n];
		mst = new boolean[n][n];
		traversal = new LinkedHashMap<Integer,Integer>();
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

	public void addToMst(Key key) {
		mst[key.name()][key.to()] = true;
		mst[key.to()][key.name()] = true;
	}

	public boolean hasNode(int node) {
		boolean exists = false;
		for (int i = 0; i < mst.length; i++) {
			if (mst[node][i])
				exists = true;
		}
		return exists;
	}

	public double cost() {
		double cost = 0;
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < i; j++) {
				if (mst[i][j])
					cost += adjMatrix[i][j];
			}
		}
		return cost;
	}

	public boolean[][] mst() {
		return mst;
	}

	public LinkedHashMap<Integer,Integer> preorder() {
		preorder(0, -1);
		return traversal;
	}

	private void preorder(int id, int parent) {
		traversal.put(id,parent);
		for (Integer neighbor : mstneighbors(id)) {
			preorder(neighbor, id);
		}
	}

	private List<Integer> mstneighbors(int id) {
		List<Integer> neighbors = new ArrayList<Integer>();
		for (int i = 1; i < adjMatrix.length; i++) {
			if (mst[id][i] && !traversal.containsKey(i)) {
				neighbors.add(i);
			}
		}
		return neighbors;
	}


	public double mstweight() {	
		double weight = 0;
		for (int i = 0; i < traversal.size()-2; i++) {
			weight += adjMatrix[i][i+1];
		}
		return weight;
	}

	public double distance() {
		double weight = 0;
		List<Integer> list = new ArrayList(traversal.keySet());
		for (int i = 0; i < list.size()-1; i++) {
			weight += adjMatrix[list.get(i)][list.get(i+1)];
		}
		weight += adjMatrix[list.get(0)][list.get(list.size()-1)];
		return weight;
	}

	public LinkedHashMap<Integer,Integer> traversal() {
		return traversal;
	}

}