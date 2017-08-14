public class Solution {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            return new ArrayList<Integer>();
        }
        
        int[] result = new int[rowIndex+1];
        
        result[0] = 1;
        switch(rowIndex) {
            case 0:
                break;
            case 1:
                result[1] = 1;
                break;
            default:
                result[1] = 1;
                for (int i = 2; i <= rowIndex; i++) {
                    int mid = i/2;
                    int x = result[0];
                    for (int j = 1; j <= mid; j++) {
                        int y = result[j];
                        result[j] = x + y;
                        result[i-j] = result[j];
                        x = y;
                    }
                    result[i] = 1;
                }
                break;
        }
                
        ArrayList<Integer> list = new ArrayList<>();
        
        for (int i = 0; i <= rowIndex; i++) {
            list.add(result[i]);
        }
        
        return list;
    }
}