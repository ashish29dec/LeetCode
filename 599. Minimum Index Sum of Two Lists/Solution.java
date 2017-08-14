public class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map= new HashMap<>();
        if (list1 == null || list1.length == 0) {
            return list1;
        }
        
        if (list2 == null || list2.length == 0) {
            return list2;
        }
        
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        
        ArrayList<String> list = new ArrayList<>();
        int minIndex = list1.length + list2.length;
        for (int i = 0; i < list2.length; i++) {
            if (map.get(list2[i]) != null) {
                int sum = i + map.get(list2[i]);
                if (sum < minIndex) {
                    list.clear();
                    list.add(list2[i]);
                    minIndex = sum;
                } else if (sum == minIndex) {
                    list.add(list2[i]);
                }
            }
        }
        
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        
        return arr;
    }
}