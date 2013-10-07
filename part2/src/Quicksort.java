import java.util.List;
import java.util.ArrayList;

/* Quicksort Algorithm.

   author: Peter Mikitsh pam3961
*/
class Quicksort {

	/* Return arrays of size <= 1 (sorted). Else, pivot at the middle of the
	   list. Copy smaller elements in less; larger elements into greater.
	   Recursive sort left and right halves and merge results into a single
	   list.
	*/
	public static List<Edge> quicksort(List<Edge> list) {

		if (list.size() <= 1) {
			return list;
		}

		int middle = (int) Math.ceil((double) list.size() / 2);
    	Edge pivot = list.remove(middle);

		List<Edge> less = new ArrayList<Edge>();
		List<Edge> greater = new ArrayList<Edge>();

		for (Edge e : list) {
			if (e.compareTo(pivot) <= 0) {
				less.add(e);
			} else {
				greater.add(e);
			}
		}

		return concatenate(quicksort(less), pivot, quicksort(greater), list);
	}

	/* Uses java.util functionality to merge lists. */
	public static List<Edge> concatenate(List<Edge> l1, Edge l2,
										 	List<Edge> l3, List<Edge> l) {
		l.clear();
		l.addAll(l1);
		l.add(l2);
		l.addAll(l3);
		return l;
	}


}