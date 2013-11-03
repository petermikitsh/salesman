/** Key: A comparable graph edge.
  * author: peter mikitsh pam3961
**/
class Key implements Comparable<Key> {

  private int name;
  private double weight;
  private int to;

  public Key(int name, double weight, int to) {
    this.name = name;
    this.weight = weight;
    this.to = to;
  }

  public int name() {
    return name;
  }

  public double weight() {
    return weight;
  }

  public int to() {
    return to;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public void setTo(int to) {
    this.to = to;
  }

  public int compareTo(Key that) {
    return ((Double) this.weight).compareTo(that.weight());
  }

}