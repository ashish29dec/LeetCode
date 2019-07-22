class Solution {
    public HashMap<Integer, ArrayList<Integer>>[] parseDictionary(List<String> dict) {
        int size = dict.size();
        HashMap[] parsedDict = new HashMap[26];
        for (int i = 0; i < size; i++) {
            String word = dict.get(i);
            int len = word.length();
            for (int j = 0; j < len; j++) {
                char c = word.charAt(j);
                HashMap existing = parsedDict[c - 'a'];
                if (existing == null) {
                    existing = new HashMap();
                }
                parsedDict[c - 'a'] = existing;
                ArrayList<Integer> indexes = (ArrayList<Integer>) existing.get(j);
                if (indexes == null) {
                    indexes = new ArrayList();
                }

                indexes.add(i);
                existing.put(j, indexes);
            }
        }
        
        return parsedDict;
    }
    
    public int findMinLengthDict(HashSet<Integer> set, HashMap<Integer, Integer> map) {
        Iterator<Integer> iterator = set.iterator();
        int minIndex = -1;
        while (iterator.hasNext()) {
            int index = iterator.next();
            if (minIndex == -1) {
                minIndex = index;
            } else if (map.get(index) < map.get(minIndex)) {
                minIndex = index;
            }
        }
        return minIndex;
    }
    
    public String replaceWords(List<String> dict, String sentence) {
        if (dict == null || dict.size() == 0) {
            return sentence;
        }
        
        if (sentence == null || sentence.length() <= 1) {
            return sentence;
        }
        
        // Create indexToLength map
        int size = dict.size();
        HashMap<Integer, Integer> indexToLenMap = new HashMap();
        for (int i = 0; i < size; i++) {
            indexToLenMap.put(i, dict.get(i).length());
        }
        // Parse dictionary
        HashMap[] parsedDict = parseDictionary(dict);
        
        StringBuffer output = new StringBuffer();
        // Parse words
        int start = 0;
        int end = -1;
        do {
            end = sentence.indexOf(' ', start);
            String word = null;
            if (end == -1) {
                word = sentence.substring(start);
            } else {
                word = sentence.substring(start, end);
            }
            HashSet matchedIndexes = new HashSet();
            
            int len = word.length();
            int j = 0;
            for(; j < len; j++) {
                char c = word.charAt(j);
                HashMap parsedMap = parsedDict[c - 'a'];
                if (parsedMap == null) {
                    output.append(word);
                    output.append(" ");
                    break;
                }
                ArrayList indexes = (ArrayList) parsedMap.get(j);
                if (indexes == null) {
                    output.append(word);
                    output.append(" ");
                    break;
                }
                
                int k = 0;
                if (j == 0) {
                    for (; k < indexes.size(); k++) {
                        int index = (Integer) indexes.get(k);
                        if (indexToLenMap.get(index) == 1) {
                            output.append(dict.get(index));
                            output.append(" ");
                            break;
                        }
                        matchedIndexes.add(indexes.get(k));
                    }
                } else {
                    HashSet<Integer> copy = new HashSet();
                    for (; k < indexes.size(); k++) {
                        int ind = (Integer) indexes.get(k);
                        if (matchedIndexes.contains(ind)) {
                            copy.add(ind);
                            if (indexToLenMap.get(ind) == j + 1) {
                                output.append(dict.get(ind));
                                output.append(" ");
                                break;
                            }
                        } else {
                            matchedIndexes.remove(ind);
                        }
                    }
                    matchedIndexes = copy;
                }
                if (k != indexes.size()) {
                    break;
                }
            }
            
            if (j == len) {
                // Whole word match
                output.append(word);
                output.append(" ");
            }
            
            start = end + 1;
        } while(end != -1);
        
        String out = output.toString();
        out = out.substring(0, out.length() - 1);
        return out;
    }
}