public class ZigzagIterator {

    int[] indexArr = null;
    List[] lists = null;
    List<Integer> v1;
    List<Integer> v2;
    int listToUse;
    long totalLength;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        indexArr = new int[2];
        indexArr[0] = 0;
        indexArr[1] = 0;
        
        lists = new List[2];
        
        listToUse = 0;
        
        ArrayList<Integer> empty = new ArrayList<>();
        lists[0] = v1 == null ? empty : v1;
        lists[1] = v2 == null ? empty : v2;
        
        for (int i = 0; i < lists.length; i++) {
            totalLength += lists[i].size();
        }
    }

    public int next() {
        for (int i = listToUse; i < lists.length; i++) {
            if (indexArr[i] < lists[i].size()) {
                indexArr[i]++;
                totalLength--;
                listToUse++;
                if (listToUse == lists.length) {
                    listToUse = 0;
                }
                return (Integer) lists[i].get(indexArr[i]-1);
            }
        }
        
        for (int j = 0; j < listToUse; j++) {
            if (indexArr[j] < lists[j].size()) {
                indexArr[j]++;
                totalLength--;
                if (listToUse == lists.length) {
                    listToUse = 0;
                }
                return (Integer) lists[j].get(indexArr[j]-1);
            }
        }
        return -1;
    }

    public boolean hasNext() {
         return totalLength > 0;
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */