/* Disjoint Set ADT
   Roots of sets are negative integers; with the absolute value representing
   the height of the tree. If the value is positive, then the node's parent is
   located at the index value.
*/
class DisjointSet {

    private int[] array;

    public DisjointSet(int n) {
    	array = new int[n];
    	for (int i = 0; i < array.length; i++)
    		array[i] = -1;
    }

    /* Finds the root and performs path compression. */
    public int find(int x) {
    	if (array[x] < 0) {
    		return x;
    	} else {
    		array[x] = find(array[x]);
    		return array[x];
    	}
    }

    /* Joins two arrays */
    public void union (int r1, int r2) {
    	if (array[r2] < array[r1]) {
    		array[r1] = r2;  
    	} else {
    		if (array[r1] == array[r2])
    			array[r1]--;
    		array[r2] = r1;
    	}
    }

    /* return true if a cycle will form as a result of the union; false otherwise */
    public static boolean introduceCycle(int r1, int r2) {
    	return r1 == r2 && r1 > 0 && r2 > 0;
    }

}