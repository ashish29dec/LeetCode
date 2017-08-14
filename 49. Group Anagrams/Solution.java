public class Solution {
    
    public List<List<String>> groupAnagrams(String[] strs) {        
        if (strs == null || strs.length == 0) {
            return null;
        }
        
        HashMap<String, List<String>> groupMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] str = strs[i].toCharArray();
            Arrays.sort(str);
            String string = String.valueOf(str);
            if (groupMap.get(string) != null) {
                ArrayList<String> anagrams = (ArrayList<String>) groupMap.get(string);
                anagrams.add(strs[i]);
            } else {
                ArrayList<String> anagrams = new ArrayList<>();
                anagrams.add(strs[i]);
                groupMap.put(string, anagrams);
            }
        }
        
        Iterator<String> keys = groupMap.keySet().iterator();
        ArrayList<List<String>> result = new ArrayList<>();
        while(keys.hasNext()) {
            String key = keys.next();
            result.add(groupMap.get(key));
        }
        
        return result;
    }
}