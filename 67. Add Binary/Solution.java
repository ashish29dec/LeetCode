class Solution {
    public String addBinary(String a, String b) {
        if (a == null) {
            a = "";
        }
        if (b == null) {
            b = "";
        }
        if (a.length() == 0 && b.length() == 0) {
            return "";
        }
        if (b.length() == 0) {
            return a;
        }
        if (a.length() == 0) {
            return b;
        }
        
        boolean done = false;
        int index = 0;
        StringBuffer result = new StringBuffer();
        char carryover = '0';
        while (!done) {
            int aIndex = a.length() - 1 - index;
            int bIndex = b.length() - 1 - index;
            
            char aCh = '0';
            char bCh = '0';
            if (aIndex < 0 && bIndex < 0) {
                done = true;
                continue;
            }
            
            if (aIndex >= 0) {
                aCh = a.charAt(aIndex);
            }
            if (bIndex >= 0) {
                bCh = b.charAt(bIndex);
            }
            if (aCh == '1' && bCh == '1') {
                result.insert(0, carryover);
                carryover = '1';
            } else if (((aCh == '1' && bCh == '0') || (aCh == '0' && bCh == '1')) && carryover == '1') {
                carryover = '1';
                result.insert(0, '0');
            } else if (((aCh == '1' && bCh == '0') || (aCh == '0' && bCh == '1')) && carryover == '0') {
                carryover = '0';
                result.insert(0, '1');
            } else {
                result.insert(0, carryover);
                carryover = '0';
            }
            index++;
        }
        
        if (carryover == '1') {
            result.insert(0, carryover);
        }
        
        return result.toString();
    }
}