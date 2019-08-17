class Solution {
    class Pattern {
        int Ai;
        int Ak;
        boolean isAkSet;
    }
    
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        
        if (nums.length == 3) {
            return (nums[0] < nums[1] && nums[1] > nums[2] && nums[0] < nums[2]);
        }

        ArrayList<Pattern> patterns = new ArrayList<>();
        Pattern p = new Pattern();
        p.Ai = nums[0];
        patterns.add(p);
        
        for (int i = 1; i < nums.length; i++) {
            int p_len = patterns.size();
            int num = nums[i];
            for (int j = 0; j < p_len; j++) {
                Pattern p1 = (Pattern) patterns.get(j);
                if (j < p_len - 1) {
                    if (num > p1.Ak) {
                        p1.Ak = num;
                    } else if (num > p1.Ai && num < p1.Ak) {
                        return true;
                    }
                } else {
                    if (!p1.isAkSet) {
                        if (num < p1.Ai) {
                            p1.Ai = num;
                        } else if (num > p1.Ai) {
                            p1.Ak = num;
                            p1.isAkSet = true;
                        }
                    } else {
                        if (num > p1.Ak) {
                            p1.Ak = num;
                        } else if (num < p1.Ai) {
                            Pattern p2 = new Pattern();
                            p2.Ai = num;
                            patterns.add(p2);
                        } else if (num > p1.Ai && num < p1.Ak) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}