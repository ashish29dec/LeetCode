public class Solution {
    
    private void addAllCombinations(int a, int b, int c, ArrayList<List<Integer>> result) {

        if (a == 0 && b == 0) {
            ArrayList<Integer> combination = new ArrayList<>();    
            combination.add(a);
            combination.add(b);
            combination.add(c);
            result.add(combination);
        } else if (a == b) {
            ArrayList<Integer> combination = new ArrayList<>();    
            combination.add(a);
            combination.add(b);
            combination.add(c);
            result.add(combination);

            combination = new ArrayList<>();
            combination.add(a);
            combination.add(c);
            combination.add(b);
            result.add(combination);
            
            combination = new ArrayList<>();
            combination.add(c);
            combination.add(a);
            combination.add(b);
            result.add(combination);
        } else if (b == c) {
            ArrayList<Integer> combination = new ArrayList<>();    
            combination.add(a);
            combination.add(b);
            combination.add(c);
            result.add(combination);

            combination = new ArrayList<>();
            combination.add(b);
            combination.add(a);
            combination.add(c);
            result.add(combination);
            
            combination = new ArrayList<>();
            combination.add(b);
            combination.add(c);
            combination.add(a);
            result.add(combination);
        } else {
            for (int i = 1; i <= 3; i++) {
                int temp = a;
                a = b;
                b = c;
                c = temp;
                int d = b;
                int e = c;
                for (int j = 1; j <= 2; j++) {
                    ArrayList<Integer> combination = new ArrayList<>();    
                    combination.add(a);
                    combination.add(d);
                    combination.add(e);
                    result.add(combination);
                    
                    int t = d;
                    d = e;
                    e = t;
                }
            }
        }
    }
    
    public List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        
        Arrays.sort(nums);
        
        HashSet<String> unique = new HashSet<>();
        
        int length = nums.length;
        for (int i = 0; i < length - 2; i++) {
            int start = i + 1;
            int end = length - 1;
            
            int remaining = 0 - nums[i];
            while(end > start) {
                int sum = nums[start] + nums[end];
                if (sum == remaining) {
                    // TODO: Add different combinations in list
                    String combination = nums[i] + "," + nums[start] + "," + nums[end];
                    if (!unique.contains(combination)) {
                        unique.add(combination);
                        ArrayList<Integer> list = new ArrayList<>();    
                        list.add(nums[i]);
                        list.add(nums[start]);
                        list.add(nums[end]);
                        result.add(list);
                        // addAllCombinations(nums[i], nums[start], nums[end], result);
                    }
                    start++;
                    end--;
                } else if (sum > remaining) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        
        return result;
    }
}