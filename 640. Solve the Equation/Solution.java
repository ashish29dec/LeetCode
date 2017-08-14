public class Solution {
    
    public float[] parseEquation(String equation, int start, int end) {
        float[] result = new float[2];

        float x = 0f;
        float v = 0f;
        boolean posSign = true;
        
        String curNumber = "";
        
        for (int i = start; i < end; i++) {
            char c = equation.charAt(i);
            if ((c >= '0' && c <= '9') || (c == '.')) {
                curNumber += c;
            } else if (c == 'x') {
                float numX = 0f;
                if (curNumber.length() == 0) {
                    numX = 1f;
                } else {
                    numX = Float.parseFloat(curNumber);
                }
                if (posSign) {
                    x += numX;
                } else {
                    x -= numX;
                }
                curNumber = "";
            } else if (c == '+') {
                float value = 0;
                if (curNumber.length() != 0) {
                    value = Float.parseFloat(curNumber);
                }
                if (posSign) {
                    v += value;
                } else {
                    v -= value;
                }
                posSign = true;
                curNumber = "";
            } else {
                float value = 0;
                if (curNumber.length() != 0) {
                    value = Float.parseFloat(curNumber);
                }
                if (posSign) {
                    v += value;
                } else {
                    v -= value;
                }
                posSign = false;
                curNumber = "";
            }
        }
        
        if (curNumber.length() != 0) {
            float value = Float.parseFloat(curNumber);
            if (posSign) {
                v += value;
            } else {
                v -= value;
            }
        }
        
        result[0] = x;
        result[1] = v;
        
        return result;
    }
    
    public String solveEquation(String equation) {
        if (equation == null || equation.length() == 0) {
            return "No solution";
        }
        
        if (equation.indexOf('=') == -1) {
            return "No solution";
        }
        
        int equalTo = equation.indexOf('=');
        
        float[] lhs = parseEquation(equation, 0, equalTo);
        float[] rhs = parseEquation(equation, equalTo + 1, equation.length());
        
        float lx = lhs[0];
        float lv = lhs[1];
        
        float rx = rhs[0];
        float rv = rhs[1];
        
        if (lx == rx && lv == rv) {
            return "Infinite solutions";
        }
        if (lx == rx) {
            return "No solution";
        }
        float x = lx - rx;
        float v = rv - lv;
        if (rx > lx) {
            x = rx - lx;
            v = lv - rv;
        }
        if (x > 1) {
            float divisor = x;
            x = 1f;
            v = (float) (v / divisor);
        }
        
        if ((int)v%1 == 0) {
            int val = (int) v;
            return ("x=" + val);
        }
        return ("x=" + v);
    }
}