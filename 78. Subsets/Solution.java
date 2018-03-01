import java.util.ArrayList;
import java.util.List;

class Solution {
    ArrayList<List<Integer>> result = new ArrayList<>();
    
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        ArrayList<Integer> empty = new ArrayList<>();
        
        result.add(empty);
        
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; i+j <= nums.length; j++) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(nums[j]);
                recurSubsets(list, i-1, j+1, nums);
            }
        }
        
        return result;
    }
    
    private void recurSubsets(ArrayList<Integer> list, int remElsInSubset, int index, int[] nums) {
        if (remElsInSubset == 0) {
            result.add(list);
            return;
        }
        for (int j = index; remElsInSubset+j <= nums.length; j++) {
        	ArrayList<Integer> cList = (ArrayList<Integer>) list.clone();
            cList.add(nums[j]);
            recurSubsets(cList, remElsInSubset-1, j+1, nums);
        }
    }
}