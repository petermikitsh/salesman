/* Bitonic.java
 * author: peter mikitsh pam3961
 */
class Bitonic {

  /* l[i,j]: Length of shortest path with endpoints i and j. */
  private double[][] l;

  /* n[i,j]: Neighbor of j in the bitonic i-j path.  */
  private int[][] n;

  /* Graph for weight calculations. */
  private Graph g;

  /* Number of nodes. */
  private int size;

  /* Initialize l and n-tables. */
  public Bitonic(int size, Graph g) {
    this.g = g;
    this.size = size;
    l = new double[size][size];
    n = new int[size][size];
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        n[row][col] = -1;
      }
    }
  }

  public void bitonic() {
    for (int j = 1; j < size; j++) {
      for (int i = 0; i < j; i++) {
        if (i == 0 && j == 1) {
          l[i][j] = g.dist(i,j);
          n[i][j] = i;
        } else {
          if (j > i+1) {
            l[i][j] = l[i][j-1] + g.dist(j-1,j);
            n[i][j] = j-1;
          } else {
            l[i][j] = Double.MAX_VALUE;
            for (int k = 0; k < i; k++) {
              double q = l[k][i] + g.dist(k,j);
              if (q < l[i][j]) {
                l[i][j] = q;
                n[i][j] = k;
              }
            }
          }
        }
      }
    }
  }

  public double[][] l() {
    return l;
  }

  public int[][] n() {
    return n;
  }

}