public class Solution {
    
    private int isVerMoreThan0(String version) {
        int s = 0, e = 0;
        while(true) {
            e = version.indexOf('.', s);
            if (e == -1) {
                e = version.length();
            }
            
            int v = Integer.parseInt(version.substring(s, e));
            if (v > 0) {
                return 1;
            } else if (v < 0) {
                return -1;
            }
            
            if (e == version.length()) {
                break;
            }
            
            s = e + 1;
        }
        
        return 0;
    }
    
    public int compareVersion(String version1, String version2) {
        int dI1S = 0, dI1E = 0;
        int dI2S = 0, dI2E = 0;
        
        while(true) {
            dI1E = version1.indexOf('.', dI1S);
            dI2E = version2.indexOf('.', dI2S);
        
            if (dI1E == -1) {
                dI1E = version1.length();
            }
        
            if (dI2E == -1) {
                dI2E = version2.length();
            }
        
            int v1 = Integer.parseInt(version1.substring(dI1S, dI1E));
            int v2 = Integer.parseInt(version2.substring(dI2S, dI2E));
            
            if (v1 < v2) {
                return -1;
            } else if (v1 > v2) {
                return 1;
            }
        
            if (dI1E == version1.length() || dI2E == version2.length()) {
                break;
            }
        
            dI1S = dI1E + 1;
            dI2S = dI2E + 1;
        }
        
        if (dI1E == version1.length() && dI2E == version2.length()) {
            return 0;
        } else if (dI1E == version1.length()) {
            dI2S = dI2E + 1;
            int v = isVerMoreThan0(version2.substring(dI2S));
            if (v == -1) {
                return 1;
            } else if (v == 1) {
                return -1;
            } else {
                return 0;
            }
        } else {
            dI1S = dI1E + 1;
            return isVerMoreThan0(version1.substring(dI1S));
        }
    }
}