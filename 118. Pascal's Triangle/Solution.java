class Solution {
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0) {
            return new ArrayList<>();
        }
        
        if (numRows < 0){
            numRows = 1;
        }
        
        ArrayList<List<Integer>> result = new ArrayList<>();
        
        ArrayList<Integer> first = new ArrayList<>();
        first.add(1);
        result.add(first);
        
        if (numRows >= 2) {
            ArrayList<Integer> second = new ArrayList<>();
            second.add(1);
            second.add(1);
            result.add(second);
        }
        
        for (int i = 3; i <= numRows; i++) {
            ArrayList<Integer> last = (ArrayList<Integer>) result.get(result.size() - 1);
            ArrayList<Integer> cur = new ArrayList<>();
            cur.add(1);
            for (int j = 1; j < i - 1; j++) {
                cur.add(last.get(j-1) + last.get(j));
            }
            cur.add(1);
            result.add(cur);
        }
        
        return result;
    }
}