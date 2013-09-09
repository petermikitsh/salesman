import java.util.ArrayList;

class Permutation {


  protected static ArrayList<ArrayList<Integer>> makePermutations(ArrayList<Integer> array) {
    return permutation(new ArrayList<Integer>(), array);
  }

  private static ArrayList<ArrayList<Integer>> permutation(ArrayList<Integer> prefix, ArrayList<Integer> suffix) {

    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

    int n = suffix.size();

    if (n == 0) {
      prefix.add(0, 0);
      prefix.add(0);
      result.add(prefix);
      return result;
    }

    else {
      for (int i = 0; i < n; i++) {
        ArrayList<Integer> prefixcopy = new ArrayList<Integer>(prefix);
        ArrayList<Integer> suffixcopy = new ArrayList<Integer>(suffix);
        prefixcopy.add(suffix.get(i));
        suffixcopy.remove(i);
        result.addAll(permutation(prefixcopy, suffixcopy));
      }
      return result;
    }
    
  }

}