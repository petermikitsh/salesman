import java.util.ArrayList;
import java.util.Collections;

public class Permutation {

  protected static ArrayList<Integer> nextPermutation(ArrayList<Integer> permutation) {

      int i = findAscendingPairIndex(permutation);
      if (i == -1) 
        return null;

      Collections.swap(permutation, i, findRightIndex(permutation, i));
      Collections.sort(permutation.subList(i+1, permutation.size()));

      return permutation;
  }

  private static int findAscendingPairIndex(ArrayList<Integer> list) {

    int i;

    for (i = list.size() - 2; i >= 0; i--) {
      if (list.get(i) < list.get(i+1)) {
        break;
      }
    }

    return i;
  }

  private static int findRightIndex(ArrayList<Integer> permutation, int index) {

    int smallIndex = permutation.size()-1;
    int smallValue = permutation.size();

    for (int currIndex = index + 1; currIndex < permutation.size(); currIndex++) {
      if ( permutation.get(currIndex) > permutation.get(index) && permutation.get(currIndex) <= smallValue  ) {
        smallIndex = currIndex;
        smallValue = permutation.get(currIndex);
      }
    }

    return smallIndex;
  }

}