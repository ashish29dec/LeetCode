import java.util.ArrayList;
import java.util.List;

class Solution {
    
    ArrayList<List<Integer>> result;
    
    public List<List<Integer>> combine(int n, int k) {
        if(n < 0 || k < 0) {
            return new ArrayList<>();
        }
        if (k > n) {
            return new ArrayList<>();
        }
        result = new ArrayList<>();
        
        for (int i = 1; i <= n-k+1; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(i);
            combinations(2, i+1, k, n, list);
        }
        
        return result;
    }
    
    public void combinations(int currDigitIndex, int currNumber, int k, int n, ArrayList<Integer> preCombination) {
        if (currDigitIndex > k) {
            result.add(preCombination);
            return;
        }
        for (int i = currNumber; i <= n-k+currDigitIndex; i++) {
            ArrayList<Integer> list = (ArrayList<Integer>) preCombination.clone();
            list.add(i);
            combinations(currDigitIndex+1, i+1, k, n, list);
        }
    }
}