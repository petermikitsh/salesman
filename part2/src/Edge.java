import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.List;


class Edge implements Comparable<Edge> {
	public int row;
	public int column;
	public double weight;

	public Edge (int row, int column, double weight) {
		this.row = row;
		this.column = column;
		this.weight = weight;
	}

	public int compareTo(Edge that) {
        int value1 = Double.compare(this.weight, that.weight);
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
