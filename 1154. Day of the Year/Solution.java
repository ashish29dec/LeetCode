class Solution {
    public int dayOfYear(String date) {
        int year = stringToInt(date.substring(0, 4));
        boolean isLeapYear = (year%4 == 0 && (year%100 != 0 || (year%100 == 0 && year%400 == 0)));
        int month = stringToInt(date.substring(5, 7));
        int day = stringToInt(date.substring(8));
        int result = 0;
        for (int i = 1; i <= month - 1; i++) {
            int days = 30;
            switch (i) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                    days = 31;
                    break;
                case 2:
                    days = isLeapYear ? 29 : 28;
                    break;
            }
            result += days;
        }
        return (result + day);
    }
    
    public int stringToInt(String str) {
        int length = str.length();
        int multiple = 1;
        int result = 0;
        for (int i = length - 1; i >= 0; i--) {
            int c = str.charAt(i) - '0';
            result += (c * multiple);
            multiple *= 10;
        }
        
        return result;
    }
}