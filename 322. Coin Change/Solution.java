class Solution {
    public int coinChange(int[] coins, int amount) {
        
        if (amount <= 0) {
            return 0;
        }
        
        if (coins == null || coins.length == 0) {
            return -1;
        }
        
        int[] amounts = new int[amount+1];
        for (int i = 0; i < amounts.length; i++) {
            amounts[i] = -1;
        }
        amounts[0] = 0;
        for (int i = 0; i < amounts.length; i++) {
            if (amounts[i] == -1) {
                continue;
            }
            for (int j = 0; j < coins.length; j++) {
                int newTotal = i + coins[j];
                if (newTotal >= 0 && newTotal < amounts.length && (amounts[newTotal] == -1 || amounts[newTotal] > amounts[i] + 1)) {
                 amounts[newTotal] = amounts[i] + 1;   
                }
            }
        }
        
        return amounts[amount];
    }
}