import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.List;


class Edge implements Comparable<Edge> {
	public int row;
	public int column;
	public Graph g;

	public Edge (int row, int column, Graph g) {
		this.row = row;
		this.column = column;
		this.g = g;
	}

	public int compareTo(Edge that) {
        int value1 = Double.compare(g.getWeight(row,column), that.g.getWeight(that.row,that.column));
        if (value1 == 0) {
            int value2 = this.row - that.row;
            if (value2 == 0) {
                return this.column - that.column;
            } else {
                return value2;
            }
        } else {
            return value1;
        }
    }

}
