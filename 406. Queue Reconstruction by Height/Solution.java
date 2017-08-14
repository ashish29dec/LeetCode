public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) {
            return new int[0][0];
        }
        
        if (people.length == 1) {
            return people;
        }
        
        int[][] result = new int[people.length][2];
        for (int i = 0; i < result.length; i++) {
            result[i] = null;
        }
        
        Integer[][] persons = new Integer[people.length][2];
        for (int i = 0; i < people.length; i++) {
            for(int j = 0; j < 2; j++) {
                persons[i][j] = people[i][j];
            }
        }
        
        Arrays.sort(persons, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] a, Integer[] b) {
                if (a[0] < b[0]) {
                    return -1;
                } else if (a[0] > b[0]) {
                    return 1;
                } else if (a[1] < b[1]) {
                    return -1;
                } else if (a[1] > b[1]) {
                    return 1;
                }
                return 0;
            }
        });
        
        for (int i = 0; i < people.length; i++) {
            Integer[] person = persons[i];
            int k = person[1];
            int start = 0;
            while(k > 0) {
                if (result[start] == null || result[start][0] >= person[0]) {
                    k--;
                }
                start++;
            }
            while(result[start] != null) {
                start++;
            }
            result[start] = new int[] {person[0], person[1]};
        }
        
        return result;
    }
}