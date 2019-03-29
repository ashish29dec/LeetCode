import java.util.Hashtable;

class Solution {
    
    class Node{
        int difficulty;
        int profit;
        
        Node(int difficulty, int profit) {
            this.difficulty = difficulty;
            this.profit = profit;
        }
    }
    
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        if (worker == null || worker.length == 0) {
            return 0;
        }
        
        if (difficulty == null || profit == null || difficulty.length == 0 || profit.length == 0) {
            return 0;
        }
        
        Hashtable<Integer, Integer> diffProfit = new Hashtable();
        for(int k = 0; k < difficulty.length; k++) {
            if(diffProfit.get(difficulty[k]) == null) {
                diffProfit.put(difficulty[k], profit[k]);
            } else if (diffProfit.get(difficulty[k]) < profit[k]) {
                diffProfit.put(difficulty[k], profit[k]);
            }
        }
        
        Arrays.sort(worker);
        Arrays.sort(difficulty);
        
        for (int l = 0; l < difficulty.length; l++) {
            profit[l] = diffProfit.get(difficulty[l]);
        }
        
        if (worker[worker.length - 1] < difficulty[0]) {
            return 0;
        }
        
        Node[] profits = new Node[difficulty.length];
        int index = -1;
        for (int i = 0; i < difficulty.length; i++) {
            if (difficulty[i] > worker[worker.length - 1]) {
                break;
            }
            if (profit[i] >= 0) {
                if (index < 0 || (index >= 0 && profit[i] > profits[index].profit)) {
                    profits[++index] = new Node(difficulty[i], profit[i]);
                }
            }
        }
        if (index == -1) {
            return 0;
        }
        
        int totalProfit = 0;
        for (int j = worker.length - 1; j >= 0 && index >= 0; j--) {
            if (worker[j] < profits[0].difficulty) {
                break;
            }
            
            if (worker[j] >= profits[index].difficulty) {
                totalProfit += profits[index].profit;
            } else {
                index--;
                j++;
            }
        }
        
        return totalProfit;
    }
}