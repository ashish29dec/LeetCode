import java.util.Hashtable;

class Solution {
    public int numRabbits(int[] answers) {
        if (answers == null || answers.length == 0) {
            return 0;
        }
        
        int length = answers.length;
        if (length == 1) {
            return (answers[0] + 1);
        }
        
        Hashtable<Integer, Integer> uniques = new Hashtable();
        int total = 0;
        for (int i = 0; i < length; i++) {
            int num = answers[i];
            Integer numRabbits = (Integer) uniques.get(num);
            if (numRabbits == null || numRabbits == 0) {
                total += num + 1;
                uniques.put(num, num);
            } else {
                uniques.put(num, numRabbits - 1);
            }
        }
        
        return total;
    }
}