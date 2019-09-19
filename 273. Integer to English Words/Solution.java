class Solution {
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        HashMap<Integer, String> level = new HashMap<>();
        level.put(2, "Thousand");
        level.put(3, "Million");
        level.put(4, "Billion");
        
        HashMap<Integer, String> number = new HashMap<>();
        number.put(1, "One");
        number.put(2, "Two");
        number.put(3, "Three");
        number.put(4, "Four");
        number.put(5, "Five");
        number.put(6, "Six");
        number.put(7, "Seven");
        number.put(8, "Eight");
        number.put(9, "Nine");
        number.put(10, "Ten");
        number.put(11, "Eleven");
        number.put(12, "Twelve");
        number.put(13, "Thirteen");
        number.put(14, "Fourteen");
        number.put(15, "Fifteen");
        number.put(16, "Sixteen");
        number.put(17, "Seventeen");
        number.put(18, "Eighteen");
        number.put(19, "Nineteen");
        number.put(20, "Twenty");
        number.put(30, "Thirty");
        number.put(40, "Forty");
        number.put(50, "Fifty");
        number.put(60, "Sixty");
        number.put(70, "Seventy");
        number.put(80, "Eighty");
        number.put(90, "Ninety");
        
        List<String> output = new ArrayList<>();
        
        int q = num / 1000;
        int r = num % 1000;
        int l = 1;
        while (q != 0) {
            output.add(getSubNumber(r, l, level, number).toString());
            
            r = q % 1000;
            q = q / 1000;
            l++;
        }
        
        output.add(getSubNumber(r, l, level, number).toString());
        
        int size = output.size();
        StringBuffer result = new StringBuffer();
        for (int i = size - 1; i >= 0; i--) {
            result.append(output.get(i));
        }
        
        String s = result.toString();
        return s.substring(0, s.length() - 1);
    }
    
    private StringBuffer getSubNumber(int r, int l, HashMap<Integer, String> level, HashMap<Integer, String> number) {
        String levelString = level.get(l);
        levelString = levelString != null ? levelString : "";
        if (r == 0) {
            levelString = "";
        }
        int sq = r/10;
        int sr = r%10;
        String units = number.get(sr);
        int u = sr;
        units = units != null ? units : "";
        sr = sq%10;
        sq = sq/10;
        String tens = number.get(sr*10);
        if (sr == 1) {
            tens = number.get(sr*10 + u);
            units = "";
        }
        tens = tens != null ? tens : "";
        String hundreds = number.get(sq);
        hundreds = hundreds != null ? hundreds + " Hundred" : "";
        StringBuffer buf = new StringBuffer();
        buf.append(hundreds);
        if (hundreds.length() > 0) {
            buf.append(" ");
        }
        buf.append(tens);
        if (tens.length() > 0) {
            buf.append(" ");
        }
        buf.append(units);
        if (units.length() > 0) {
            buf.append(" ");
        }
        buf.append(levelString);
        if (levelString.length() > 0) {
            buf.append(" ");
        }
        return buf;
    }
}