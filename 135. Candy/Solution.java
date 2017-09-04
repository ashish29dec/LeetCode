class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        
        if (ratings.length == 1) {
            return 1;
        }
        
        int[] candies = new int[ratings.length];
        for (int i = 0; i < candies.length; i++) {
            candies[i] = 1;
        }
        
        ArrayList<Integer> stack = new ArrayList<>();
        
        if (ratings[0] > ratings[1]) {
            stack.add(0);
        } else if (ratings[0] < ratings[1]) {
            candies[1] = 2;
        }
        
        for (int i = 1; i < ratings.length - 1; i++) {
            if (ratings[i] > ratings[i+1]) {
                stack.add(i);
            } else {
                if (stack.size() > 0) {
                    // Pop stack items
                    while (stack.size() > 0) {
                        int index = stack.remove(stack.size() - 1);
                        candies[index] = Math.max(candies[index+1] + 1, candies[index]);
                    }
                }
                if (ratings[i] < ratings[i+1]) {
                    candies[i+1] = candies[i] + 1;
                }
            }
        }
        
        if (stack.size() > 0) {
            while(stack.size() > 0) {
                int index = stack.remove(stack.size() - 1);
                candies[index] = Math.max(candies[index], candies[index+1] + 1);
            }
        }
        
        int total = 0;
        for (int i = 0; i < candies.length; i++) {
            total += candies[i];
        }
        
        return total;
    }
}