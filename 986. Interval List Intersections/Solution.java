class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || A.length == 0) {
            return A;
        }
        if (B == null || B.length == 0) {
            return B;
        }
        
        int indA = 0;
        int indB = 0;
        
        List<List<Integer>> output = new ArrayList<>();
        
        while (indA < A.length && indB < B.length) {
            int[] a = A[indA];
            int[] b = B[indB];
            
            List<Integer> o = new ArrayList<>();
            if (a[0] >= b[0] && a[0] <= b[1]) {
                o.add(a[0]);
                if (a[1] > b[1]) {
                    o.add(b[1]);
                    indB++;
                } else {
                    o.add(a[1]);
                    indA++;
                }
                output.add(o);
            } else if (b[0] >= a[0] && b[0] <= a[1]) {
                o.add(b[0]);
                if (b[1] > a[1]) {
                    indA++;
                    o.add(a[1]);
                } else {
                    indB++;
                    o.add(b[1]);
                }
                output.add(o);
            } else if (a[0] > b[1]) {
                indB++;
            } else if (b[0] > a[1]) {
                indA++;
            }
        }
        
        int[][] out = new int[output.size()][2];
        for (int i = 0; i < output.size(); i++) {
            List<Integer> l = output.get(i);
            int[] o = new int[2];
            o[0] = l.get(0);
            o[1] = l.get(1);
            out[i] = o;
        }
        return out;
    }
}