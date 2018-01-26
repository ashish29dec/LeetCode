class Solution {
    public int distributeCandies(int[] candies) {
        if (candies == null || candies.length == 0) {
            return 0;
        }
        
        int[] numOfTypes = new int[200001];
        for (int i = 0; i < numOfTypes.length; i++) {
            numOfTypes[i] = 0;
        }
        
        for (int i = 0; i < candies.length; i++) {
            numOfTypes[candies[i] + 100000]++;
        }
        
        HashMap<Integer, Integer> numToTypesCountMap = new HashMap<>();
        int minNum = 11000;
        int maxNum = 0;
        for (int i = 0; i < numOfTypes.length; i++) {
            if (numOfTypes[i] > 0) {
                int num = numOfTypes[i];
                int type = i;
                if (minNum > num) {
                    minNum = num;
                }
                if (maxNum < num) {
                    maxNum = num;
                }
                Integer typesCount = numToTypesCountMap.get(num);
                if (typesCount == null) {
                    typesCount = 1;
                } else {
                    typesCount++;
                }
                numToTypesCountMap.put(num, typesCount);
            }
        }
        
        int remIterations = candies.length / 2;
        int currNum = minNum;
        int result = 0;
        while (remIterations > 0 && currNum <= maxNum) {
            Integer count = numToTypesCountMap.get(currNum);
            if (count != null) {
                if (count < remIterations) {
                    result += count;
                    remIterations -= count;
                    currNum++;
                } else {
                    result += remIterations;
                    break;
                }
            } else {
                currNum++;
            }
        }
        
        return result;
    }
}