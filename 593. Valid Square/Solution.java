class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if ((p1[0] == p2[0] && p1[1] == p2[1]) || (p1[0] == p3[0] && p1[1] == p3[1]) || (p1[0] == p4[0] && p1[1] == p4[1])) {
            return false;
        }
        
        if ((p2[0] == p3[0] && p2[1] == p3[1]) || (p2[0] == p4[0] && p2[1] == p4[1])) {
            return false;
        }
        
        if (p3[0] == p4[0] && p3[1] == p4[1]) {
            return false;
        }
        
        int[] a = p1;
        int[] possibleB = p2;
        int[] possibleC = p3;
        int[] b;
        int[] d;
        int[] c;
        double distBC, distCD, distAD, distBD;
        double distAB = Math.sqrt(Math.pow(possibleB[0]-a[0], 2) + Math.pow(possibleB[1]-a[1], 2));
        double distAC = Math.sqrt(Math.pow(possibleC[0]-a[0], 2) + Math.pow(possibleC[1]-a[1], 2));
        if (distAB == distAC) {
            // B is B & C is D
            b = possibleB;
            d = possibleC;
            int baY = Math.abs(b[1] - a[1]);
            int aDX = Math.abs(a[0] - d[0]);
            if (baY != aDX) {
                // B & D are not at 90 degrees to A
                return false;
            }
            // else, A, B & D points are confirmed
            c = p4;
            distAD = distAC;
            distBC = Math.sqrt(Math.pow(b[0]-c[0], 2) + Math.pow(b[1]-c[1], 2));
            distCD = Math.sqrt(Math.pow(d[0]-c[0], 2) + Math.pow(d[1]-c[1], 2));
        } else if (distAB > distAC) {
            // B is C and C is B
            b = possibleC;
            c = possibleB;
            distBC = Math.sqrt(Math.pow(b[0]-c[0], 2) + Math.pow(b[1]-c[1], 2));
            if (distBC != distAC) {
                // side AB and BC don't match
                return false;
            }
            // else, A, B & C points are confirmed
            d = p4;
            distAB = distAC;
            distCD = Math.sqrt(Math.pow(d[0]-c[0], 2) + Math.pow(d[1]-c[1], 2));
            distAD = Math.sqrt(Math.pow(d[0]-a[0], 2) + Math.pow(d[1]-a[1], 2));
        } else {
            // B & C are found
            b = possibleB;
            c = possibleC;;
            distBC = Math.sqrt(Math.pow(b[0]-c[0], 2) + Math.pow(b[1]-c[1], 2));
            if (distBC != distAB) {
                // sides AB and BC don't match
                return false;
            }
            // else, A, B & C points are confirmed
            d = p4;
            distCD = Math.sqrt(Math.pow(d[0]-c[0], 2) + Math.pow(d[1]-c[1], 2));
            distAD = Math.sqrt(Math.pow(d[0]-a[0], 2) + Math.pow(d[1]-a[1], 2));
        }
        
        if (distAB != distBC || distBC != distCD || distCD != distAD || distAD != distAB) {
            return false;
        }
        
        double distAC_B = Math.sqrt(Math.pow(distAB, 2) + Math.pow(distBC, 2));
        double distAC_D = Math.sqrt(Math.pow(distAD, 2) + Math.pow(distCD, 2));
        
        if (distAC_B != distAC_D) {
            return false;
        }
        
        distAC = Math.sqrt(Math.pow(c[0] - a[0], 2) + Math.pow(c[1] - a[1], 2));
        distBD = Math.sqrt(Math.pow(b[0] - d[0], 2) + Math.pow(b[1] - d[1], 2));
        
        if (distAC != distBD) {
        	return false;
        }
        
        return true;
    }
}