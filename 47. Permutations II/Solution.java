import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Solution {
    HashSet<String> unique = new HashSet<>();
    ArrayList<List<Integer>> result;
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        result = new ArrayList<>();
        if (nums.length == 1) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(nums[0]);
            result.add(list);
            return result;
        }
        
        for (int i = 0; i < nums.length; i++) {
            StringBuffer hash = new StringBuffer("");
            ArrayList<Integer> list = new ArrayList<>();
            shift(nums, 0);
            hash.append(nums[0]);
            list.add(nums[0]);
            recurPerm(nums, 1, hash, list);
        }
        
        return result;
    }
    
    private void recurPerm(int[] nums, int index, StringBuffer hash, ArrayList<Integer> list) {
        if (index == nums.length - 1) {
            // last element.
            // Check hash
            StringBuffer cHash = clone(hash);
            cHash.append(",");
            cHash.append(nums[index]);
            if (!unique.contains(cHash.toString())) {
                unique.add(cHash.toString());
                ArrayList<Integer> cList = (ArrayList<Integer>) list.clone();
                cList.add(nums[index]);
                result.add(cList);
            }
            return;
        }
        
        for (int i = index; i < nums.length; i++) {
            shift(nums, index);
            StringBuffer cHash = clone(hash);
            ArrayList<Integer> cList = (ArrayList<Integer>) list.clone();
            cHash.append(",");
            cHash.append(nums[index]);
            cList.add(nums[index]);
            recurPerm(nums, index+1, cHash, cList);
        }
    }
    
    private StringBuffer clone(StringBuffer buff) {
        return new StringBuffer(buff.toString());
    }
    
    private void shift(int[] nums, int index) {
        int len = nums.length;
        int last = nums[len-1];
        for (int i = len - 2; i >= index; i--) {
            nums[i+1] = nums[i];
        }
        nums[index] = last;
    }
}