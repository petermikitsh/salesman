import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.List;

/* Edge data structure. Implements Comparable for sorting edges,
   as defined by the Project 2 specification.

   author: Peter Mikitsh pam3961
*/
class Edge implements Comparable<Edge> {
	public int row;
	public int column;
	public Graph g;

	/* Constructor. */
	public Edge (int row, int column, Graph g) {
		this.row = row;
		this.column = column;
		this.g = g;
	}

	/* Compare by weight, then column, then row. */
	public int compareTo(Edge that) {
        int value1 = Double.compare(g.getWeight(row,column), that.g.getWeight(that.row,that.column));
        if (value1 == 0) {
            int value2 = this.column - that.column;
            if (value2 == 0) {
                return this.row - that.row;
            } else {
                return value2;
            }
        } else {
            return value1;
        }
    }

    public double getWeight() {
    	return g.getWeight(row, column);
    }

}
